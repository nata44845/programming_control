package Model.Nursery;

import java.util.Iterator;
import java.util.List;

import Model.Animal.Animal;

public class NurseryIterator<E extends Animal> implements Iterator<E> {
    private int index;
    private List<E> itemList;

    public NurseryIterator(List<E> itemList) {
        this.itemList = itemList;
    }

    @Override
    public boolean hasNext() {
        return itemList.size() > index;
    }

    @Override
    public E next() {
        return itemList.get(index++);
    }
}