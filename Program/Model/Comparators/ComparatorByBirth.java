package Model.Comparators;

import java.util.Comparator;

import Model.Nursery.Animal;

public class ComparatorByBirth<E extends Animal> implements Comparator<E> {
    @Override
    public int compare(E o1, E o2) {
        if (o1.getBirth() == null && o2.getBirth() == null)
            return 0;
        if (o1.getBirth() == null && o2.getBirth() != null)
            return 1;
        if (o1.getBirth() != null && o2.getBirth() == null)
            return -1;
        return o1.getBirth().compareTo(o2.getBirth());
    }
}