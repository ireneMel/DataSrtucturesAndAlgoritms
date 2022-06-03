package homework.lesson3DataStructures;

import princeton.lib.StdRandom;

import java.util.NoSuchElementException;

/**
 * deletes random element from the queue
 */
public class RandomizedQueueArray<Item> extends ArrayQueue<Item> implements Iterable<Item> {

    @Override
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if(cnt>0 && cnt == it.length /4) resize(it.length/2);
        int randIndex = StdRandom.uniform(cnt);
        Item i = it[randIndex];
        it[randIndex] = it[cnt-1];
        it[--cnt] = null;
        return i;

    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return it[StdRandom.uniform(cnt)];
    }

}