package Presenter;

import Model.Nursery.Animal;
import Model.Nursery.AnimalCreator;
import Model.Nursery.AnimalType;
import Model.Nursery.Nursery;
import View.View;
import FileWork.Writable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Presenter {
    private View view;
    private Nursery<Animal> nursery;
    Writable wr;
    String fileName;
    DateTimeFormatter formatter;
    private AnimalCreator animalCreator;

    public Presenter(View view, Writable wr) {
        this.nursery = new Nursery<>();
        this.view = view;
        this.wr = wr;
        fileName = "Nursery.txt";
        formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        this.animalCreator = new AnimalCreator();
    }

    public void getNurseryInfo() {
        view.printAnswer(nursery.getNurseryInfo());
    }

    public Animal addItem(int a_type,String name, LocalDate date) {
        Animal animal = animalCreator.createAnimal(AnimalType.getType(a_type),name, date);
        nursery.addItem(animal);
        return animal;
    }

    public Animal getNurseryItem(int number) {
        return nursery.getAnimalItem(number);
    }

    public boolean findData(Integer id) {
        for (Animal item : nursery) {
            if (item.getId() == id) {
                view.printAnswer(item.toString());
                return true;
            }
        }
        return false;
    }

}
