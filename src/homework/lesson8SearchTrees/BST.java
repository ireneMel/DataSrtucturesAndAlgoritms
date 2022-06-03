package homework.lesson8SearchTrees;

import homework.lesson3DataStructures.LinkedQueue;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {

        private Key key;
        private Value value;
        private Node left, right;
        private int count = 1;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else if (cmp == 0) x.value = val;
        x.count = 1 + size(x.right) + size(x.left);
        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Key successor(Node x) {
        x = x.right;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    private Key predecessor(Node x){
        x = x.left;
        while(x.right != null){
            x = x.right;
        }
        return x.key;
    }

    public Node delete(Node x, Key key) {

        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null && x.left == null) x = null;
            else if (x.right != null) {
                x.key = successor(x);
                x.right = delete(x.right, x.key);
            } else  {
                x.key = predecessor(x);
                x.left = delete(x.left, x.key);
            }
        }
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }


    public Iterable<Key> iterator() {
        LinkedQueue<Key> q = new LinkedQueue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, LinkedQueue<Key> q) {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    public Node minNode() {
        return minNode(root);
    } //мінімальна нода в таблиці.


    public Node minNode(Node x) {
        if (x.left != null) return minNode(x.left);
        return x;
    } //мінімальна нода в таблиці.

    public Key min() {
        return min(root);
    } //мінімальний ключ в таблиці.


    public Key min(Node x) {
        if (x.left != null) return min(x.left);
        return x.key;
    } //мінімальний ключ в таблиці.


    public Key max() {
        return max(root);
    } //максимальний ключ в таблиці.

    public Key max(Node x) {
        if (x.right != null) return max(x.right);
        return x.key;
    } //максимальний ключ в таблиці.

    //максимальний ключ в таблиці, що менше або дорівнює заданому.
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t == null) return x;
        else return t;
    }

    //найменший ключ в таблиці, що більше або дорівнює заданому.
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);

        Node t = ceiling(x.left, key);
        if (t == null) return x;
        else return t;
    }

    //кількість ключів менших за key
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return rank(x.right, key) + 1 + size(x.left);
        return size(x.left);
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();

        bst.put(10, "a");
        bst.put(2, "j");
        bst.put(-31, "s");
        bst.put(17, "m");
        bst.put(100, "z");

        System.out.println("size = " + bst.size());

        Iterable<Integer> it = bst.iterator();

        System.out.println("-----------------------------------");
        System.out.println("Values: ");
        for(Integer i : it) {
            System.out.println(bst.get(i));
        }

        System.out.println("-----------------------------------");
        System.out.println("Keys: ");

        for(Integer i : it) {
            System.out.println(i);
        }

        System.out.println("-----------------------------------");
        System.out.println("min: " + bst.min() +"; max: " + bst.max());

        System.out.println("-----------------------------------");
        System.out.println("floor(14) = " + bst.floor(14));
        System.out.println("floor(-100) = " + bst.floor(-100));
        System.out.println("floor(300) = " + bst.floor(300));

        System.out.println("-----------------------------------");
        System.out.println("ceiling(14) = " + bst.ceiling(14));
        System.out.println("ceiling(-100) = " + bst.ceiling(-100));
        System.out.println("ceiling(300) = " + bst.ceiling(300));

        System.out.println("-----------------------------------");
        System.out.println("rank(333) = " + bst.rank(333));
        System.out.println("rank(-10000) = " + bst.rank(-1000));
        System.out.println("rank(15) = " + bst.rank(15));
        System.out.println("-----------------------------------");

        bst.delete(2);
        bst.deleteMin();
        bst.deleteMax();

        it = bst.iterator();
        for(Integer i : it) {
            System.out.println("value = "+bst.get(i) + "; key = "+ i);
        }
    }

}
