package Presenter;

import Model.Animal.Animal;
import Model.Animal.AnimalCreator;
import Model.Animal.AnimalType;
import Model.Nursery.Nursery;
import View.View;
import FileWork.Writable;
import java.time.LocalDate;

public class Presenter {
    private View view;
    private Nursery<Animal> nursery;
    Writable wr;
    String fileName;
    private AnimalCreator animalCreator;

    public Presenter(View view, Writable wr) {
        this.nursery = new Nursery<>();
        this.view = view;
        this.wr = wr;
        fileName = "Nursery.txt";
        this.animalCreator = new AnimalCreator();
    }

    public void getNurseryInfo() {
        view.printAnswer(nursery.getNurseryInfo());
    }

    public Animal addItem(int a_type,String name, LocalDate date, String commands) {
        Animal animal = animalCreator.createAnimal(AnimalType.getType(a_type),name, date, commands);
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
        view.printAnswer("Запись не найдена\n");
        return false;
    }

    public boolean showCommands(Integer id) {
        for (Animal item : nursery) {
            if (item.getId() == id) {
                view.printAnswer(item.toString());
                view.printAnswer(item.getCommands()+"\n");
                return true;
            }
        }
        view.printAnswer("Запись не найдена\n");
        return false;
    }

    public boolean addCommands(Integer id, String commands) {
        for (Animal item : nursery) {
            if (item.getId() == id) {
                if (item.getCommands().length()>0)
                    item.setCommands(item.getCommands()+", "+commands);
                else 
                    item.setCommands(commands);
                view.printAnswer(item.toString());
                view.printAnswer(item.getCommands()+"\n");
                return true;
            }
        }
        view.printAnswer("Запись не найдена\n");
        return false;
    }

}
