package homework.lesson3DataStructures;

import java.util.NoSuchElementException;

public class ArrayQueue<Item> extends DataStructureArray<Item> implements Iterable<Item> {

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (cnt == it.length) resize(2 * it.length);
        it[cnt++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item tmp = it[0];
        cnt--;
        System.arraycopy(it, 1, it, 0, cnt);
        if (cnt > 0 && cnt == it.length / 4) resize(it.length / 2);
        return tmp;
    }


}
