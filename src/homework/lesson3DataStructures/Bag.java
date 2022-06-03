package homework.lesson3DataStructures;

public class Bag<Item> extends DataStructureArray<Item> implements Iterable<Item> {

    public void add(Item item) {
        if (item == null) throw new NullPointerException();
        if (cnt == it.length) resize(2 * it.length);
        it[cnt++] = item;
    }

}