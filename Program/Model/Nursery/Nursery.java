package Model.Nursery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Model.Comparators.ComparatorByBirth;
import Model.Comparators.ComparatorByName;

public class Nursery<E extends Animal> implements Serializable, Iterable<E> {
    private int id_pet;
    private List<E> itemList;

    public Nursery() {
        itemList = new ArrayList<>();
        id_pet = 1;
    }

    public void addItem(E item) {
        item.setId(id_pet++);
        itemList.add(item);
    }

    public List<E> getPetList() {
        return itemList;
    }

    public String getNurseryInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Список животных: \n");
        for (E item : itemList) {
            sb.append(item.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new NurseryIterator<>(itemList);
    }

    public void sortByName() {
        itemList.sort(new ComparatorByName<>());
    }

    public void sortByBirth() {
        itemList.sort(new ComparatorByBirth<>());
    }

    public int getSize() {
        return itemList.size();
    }

    public E getAnimal(int number) {
        for (int i = 0; i < itemList.size(); i++)
            if (itemList.get(i).getId() == number)
                return itemList.get(i);
        return null;
    }

}
