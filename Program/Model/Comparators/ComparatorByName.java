package Model.Comparators;

import java.util.Comparator;

import Model.Animal.Animal;

public class ComparatorByName<E extends Animal> implements Comparator<E> {
    @Override
    public int compare(E o1, E o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
