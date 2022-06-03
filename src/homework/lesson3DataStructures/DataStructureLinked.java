package homework.lesson3DataStructures;

import java.util.Iterator;

abstract class DataStructureLinked<Item> implements Iterable<Item> {

    Node first = null;
    int count = 0;

    class Node {
        Item item;
        Node next;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }
}
