package homework.lesson3DataStructures;

import java.util.NoSuchElementException;

public class LinkedQueue<Item> extends DataStructureLinked<Item> implements Iterable<Item> {

    private Node last;

    public void enqueue(Item item) {
        if(item == null) throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        count++;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        count--;
        return item;
    }


}
