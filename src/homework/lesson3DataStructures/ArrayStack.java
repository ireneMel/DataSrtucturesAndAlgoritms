package homework.lesson3DataStructures;

import java.util.NoSuchElementException;

public class ArrayStack<Item> extends DataStructureArray<Item> implements Iterable<Item> {

    public void push(Item item) {
        if (item == null) throw new NullPointerException();
        if (cnt == it.length) resize(2 * it.length);
        it[cnt++] = item;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException();
        return it[--cnt];
    }

}
