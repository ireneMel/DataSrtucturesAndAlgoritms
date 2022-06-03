package homework.lesson3DataStructures;

import java.util.NoSuchElementException;

public class LinkedStack<Item> extends DataStructureLinked<Item> implements Iterable<Item> {

    public void push(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        count++;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        count--;
        return item;
    }


}
