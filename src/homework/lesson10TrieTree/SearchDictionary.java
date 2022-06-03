package homework.lesson10TrieTree;

import java.io.*;
import java.util.*;

public class SearchDictionary {

    private final Node root = new Node(' ');
    private int counter;
    private List<String> words;
    private StringBuilder sb;

    public void addWord(String word) {
        if (word == null) throw new NullPointerException();
        word = word.toLowerCase();
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current.children[ch - 'a'] == null) {
                current.children[ch - 'a'] = new Node(ch);
            }
            current = current.children[ch - 'a'];
        }
        if (current.isLeaf) return;
        current.isLeaf = true;
        counter++;
    }


    public String delWord(String word) {
        word = word.toLowerCase();
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current != null)
                current = current.children[ch - 'a'];
            else throw new NoSuchElementException();
        }
        if (current.isLeaf) current.isLeaf = false;
        else throw new NoSuchElementException();
        counter--;
        return word;
    }


    public Iterable<String> query(String query) {
        query = query.toLowerCase();
        words = new LinkedList<>();
        if (query.contains("*")) {
            Node current = root;
            for (int i = 0; i < query.length() - 1; i++) {
                char ch = query.charAt(i);
                if (current.children[ch - 'a'] == null) return Collections.singleton("");
                //throw new NoSuchElementException();
                current = current.children[ch - 'a'];
            }
            sb = new StringBuilder(query);
            sb.deleteCharAt(query.length() - 1);
            findWords(current);
        } else if (hasWord(query)) words.add(query);
        else return Collections.singleton("");
        //throw new NoSuchElementException();
        return words;
    }

    private void findWords(Node current) {
        if (current.isLeaf) {
            words.add(sb.toString());
        }

        for (int i = 0; i < 26; i++) {
            if (current.children[i] != null) {
                sb.append(current.children[i].ch);
                findWords(current.children[i]);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }

    public int countWords() {
        return counter;
    }

    //returns true if the trie has the word
    public boolean hasWord(String word) {
        word = word.toLowerCase();
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current != null)
                current = current.children[ch - 'a'];
            else return false;
        }
        return current.isLeaf;
    }

    private class Node {
        private final char ch;
        private boolean isLeaf;
        private final Node[] children = new Node[26];

        public Node(char ch) {
            this.ch = ch;
        }
    }


    public static void main(String[] args) throws IOException {
        SearchDictionary sd = new SearchDictionary();

        String path = "src/homework/lesson10/test.txt";

        File file = new File(path);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            sd.addWord(sc.nextLine());
        }

        System.out.println("* : " + sd.query("*"));
        System.out.println("size = " + sd.countWords());
        //System.out.println("oleh : " + sd.query("oleha"));
        System.out.println("delete : " + sd.delWord("oleh"));
        sd.addWord("oleha");
        System.out.println("* : " + sd.query("*"));
        System.out.println("size = " + sd.countWords());
//        System.out.println("pin* : " + sd.query("pin*"));
//        System.out.println("bl* : " + sd.query("bl*"));
//        System.out.println("pin : " + sd.query("pin"));
//        System.out.println("create : " + sd.query("create"));
//        System.out.println("hasWord(comma) : " + sd.hasWord("comma"));
//        System.out.println("hasWord(sfdkj) : " + sd.hasWord("sfdkj"));
//        System.out.println("delete : " + sd.delWord("black"));
//        System.out.println("bl* : " + sd.query("bl*"));
//        System.out.println("size = " + sd.countWords());
//        System.out.println("delete : " + sd.delWord("sfdkj"));

    }
}

