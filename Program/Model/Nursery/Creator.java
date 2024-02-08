package Model.Nursery;

import java.time.LocalDate;

public abstract class Creator {

    protected abstract Animal createNewAnimal(AnimalType type);

    public Animal createAnimal(AnimalType type, String name, LocalDate date) {

        Animal animal = createNewAnimal(type);
        animal.setName(name);
        animal.setBirth(date);
        return animal;
    }
}