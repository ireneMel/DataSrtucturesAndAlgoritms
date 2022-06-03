package homework.lesson8SearchTrees;

import homework.lesson3DataStructures.LinkedQueue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class ST<Key extends Comparable<Key>, Value> {

    private int cnt = 0;
    private Node[] nodes;

    //is ordered, adds key in descending order
    ST() throws ClassNotFoundException {
        Class<?> c = Class.forName("homework.lesson8SearchTrees.Node");
        nodes = (Node[]) Array.newInstance(c, 1);

    }

    //is ordered, adds key in descending order
    ST(int capacity) {
        nodes = new Node[capacity];
        for (int i = 0; i < capacity; i++) {
            nodes[i] = new Node();
        }
    }

    //get the value using key
    public Value get(Key key) {
        for (int i = 0; i < cnt; i++)
            if (nodes[i].key.equals(key))
                return (Value) nodes[i].value;
        return null;
    }

    //put the value
    public void put(Key key, Value value) {
        if (key == null) return;
        int i = rank(key);
        if (isEmpty()){
            Node t = new Node();
            t.key = key;
            t.value = value;
            nodes[cnt++]= t;
            return;
        }
        if (i<cnt && nodes[i].key.equals(key))
            nodes[i].value=value;
        else{
            if (cnt==nodes.length) resize(2*nodes.length);
            for (int j=cnt;j>i;j--){
                nodes[j]=nodes[j-1];
            }
            nodes[i]=new Node();
            nodes[i].key=key;
            nodes[i].value=value;
            cnt++;
        }

    }

    public void delete(Key key) {
        int i = rank(key);

        // key not in table - do nothing.
        if (i == cnt || !nodes[i].key.equals(key)) {
            return;
        }

        // shift all elements to the left
        for (int j = i; j < cnt - 1; j++) {
            nodes[j].key = nodes[j + 1].key;
            nodes[j].value = nodes[j + 1].value;
        }

        // the last element now is to be deleted
        cnt--;
        nodes[cnt].key = null;
        nodes[cnt].value = null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    boolean isEmpty() {
        return cnt == 0;
    }

    int size() {
        return cnt;
    }

    Key min() {
        if (isEmpty()) throw new NoSuchElementException("Your ST is empty");
        return (Key) nodes[0].key;
    }

    Key max() {
        if (isEmpty()) throw new NoSuchElementException("Your ST is empty");
        return (Key) nodes[cnt - 1].key;
    }

    // the largest key less than or equal a given key
    Key floor(Key key) {
        if (isEmpty()) throw new NoSuchElementException("Your ST is empty");
        int i = rank(key);
        if (nodes[i].key.equals(key)) return (Key) nodes[i].key;
        if (i == 0) return null;
        else return (Key) nodes[i - 1].key;
    }

    // the smallest key greater than or equal a given key
    Key ceiling(Key key) {
        if (isEmpty()) throw new NoSuchElementException("Your ST is empty");
        int i = rank(key);
        if (i == cnt) return null;
        else return (Key) nodes[i].key;
    }

    //get the number of keys that are less than the passed key
    int rank(Key key) {
        int lo = 0, hi = cnt - 1;
        for (int i = 0; i < cnt; i++) {
            if(key.compareTo((Key) nodes[i].key) <= 0) break;
            lo++;
        }
//        while (lo <= hi) {
//            int mid = lo + (hi - lo) / 2;
//            int cmp = key.compareTo((Key) nodes[0].key); //first.key
//            if (cmp < 0) hi = mid - 1;
//            else if (cmp > 0) lo = mid + 1;
//            else return mid;
//        }
        return lo;

    }

    //finds the key at a given rank(at a given index in a sorted array)
    Key select(int k) {
        // same as when the key is null
        if (k < 0 || k >= cnt) {
            return null;
        }
        return (Key) nodes[k].key;
    }

    void deleteMin() {
        delete(min());
    }

    void deleteMax() {
        delete(max());
    }

    int size(Key lo, Key hi) {
        if(contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    Iterable<Key> keys() {
        LinkedQueue<Key> q = new LinkedQueue<Key>();
        for (int i = 0; i < cnt; i++) {
            q.enqueue((Key) nodes[i].key);
        }
        return q;
    }

    Iterable<Key> keys(Key lo, Key hi) {
        LinkedQueue<Key> q = new LinkedQueue<Key>();
        int start = getIndex(lo);
        int end = getIndex(hi);
        for (int i = start; i <= end; i++) {
            q.enqueue((Key) nodes[i].key);
        }
        return q;
    }

    public int getIndex(Key key) {
        for (int i = 0; i < cnt; i++) {
            if (nodes[i].key.equals(key)) return i;
        }
        return 0;
    }

    private void resize(int capacity) {
        Node<Key, Value>[] copy = (Node[]) Array.newInstance(Node.class, capacity);
        //(Node<Key,Value>[])new Object[capacity];
        for (int i = 0; i < cnt; i++)
            copy[i] = nodes[i];
        nodes = copy;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        ST<Integer, String> st = new ST<>();

        st.put(10, "a");
        st.put(2, "j");
        st.put(-31, "s");
        st.put(17, "m");
        st.put(100, "z");

        System.out.println("size = " + st.size());

        Iterable<Integer> it = st.keys();

        System.out.println("-----------------------------------");
        System.out.println("size in range (-31, 20) = " + st.size(-31, 20));
        System.out.println("-----------------------------------");
        System.out.println("Values: ");
        for (Integer i : it) {
            System.out.println(st.get(i));
        }

        System.out.println("-----------------------------------");
        System.out.println("Keys: ");

        for (Integer i : it) {
            System.out.println(i);
        }

        System.out.println("-----------------------------------");
        System.out.println("Keys in range (2,100): ");

        Iterable<Integer> it2 = st.keys(2, 10);
        for (Integer i : it2) {
            System.out.println(i);
        }

        System.out.println("-----------------------------------");
        System.out.println("min: " + st.min() + "; max: " + st.max()); //min -31, max 100

        System.out.println("-----------------------------------");
        System.out.println("contains(14) = " + st.contains(17));
        System.out.println("contains(-100) = " + st.contains(-100));

        System.out.println("-----------------------------------");
        System.out.println("floor(14) = " + st.floor(14)); //10
        System.out.println("floor(-100) = " + st.floor(-100));
        System.out.println("floor(300) = " + st.floor(300)); //100

        System.out.println("-----------------------------------");
        System.out.println("ceiling(14) = " + st.ceiling(14)); //17
        System.out.println("ceiling(-100) = " + st.ceiling(-100)); //-31
        System.out.println("ceiling(300) = " + st.ceiling(100));

        System.out.println("-----------------------------------");
        System.out.println("rank(15) = " + st.rank(15)); //3
        System.out.println("rank(333) = " + st.rank(333)); //5
        System.out.println("rank(0) = " + st.rank(0)); //0
        System.out.println("-----------------------------------");

        System.out.println("select(4) = " + st.select(3));
        System.out.println("-----------------------------------");

        st.delete(2);

        st.deleteMin();
        st.deleteMax();

        it = st.keys();
        for (Integer i : it) {
            System.out.println("value = " + st.get(i) + "; key = " + i);
        }

    }

}

class Node<Key, Value> {
    Key key;
    Value value;
}
