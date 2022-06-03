package homework.lesson3DataStructures;

import java.util.NoSuchElementException;

public class Deque<Item> extends DataStructureArray<Item> implements Iterable<Item> {

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        if (cnt == it.length) resize(2 * it.length);
        if (!isEmpty()) {
            Item[] tmp = (Item[]) new Object[cnt + 1];
            if (cnt >= 0) System.arraycopy(it, 0, tmp, 1, cnt);
            it = tmp;
        }
        cnt++;
        it[0] = item;

    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        if (cnt == it.length) resize(2 * it.length);
        it[cnt++] = item;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item tmp = it[0];
        cnt--;
        System.arraycopy(it, 1, it, 0, cnt);
        if (cnt > 0 && cnt == it.length / 4) resize(it.length / 2);
        return tmp;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item tmp = it[cnt - 1];
        it[cnt - 1] = null;
        cnt--;
        if (cnt > 0 && cnt == it.length / 4) resize(it.length / 2);
        return tmp;
    }
}


