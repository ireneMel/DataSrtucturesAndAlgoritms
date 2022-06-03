package homework.lesson3DataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class DataStructureArray<Item> {

    int cnt = 0;
    Item[] it;

    DataStructureArray() {
        it = (Item[]) new Object[1];
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return cnt;
    }

    void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        if (cnt >= 0) System.arraycopy(it, 0, copy, 0, cnt);
        it = copy;
    }

    public Iterator<Item> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (currentIndex >= cnt) {
                    currentIndex = 0;
                    return false;
                } else return it[currentIndex] != null;
            }

            @Override
            public Item next() {
                if (this.hasNext()) return it[currentIndex++];
                else throw new NoSuchElementException();
            }
        };
    }
}
