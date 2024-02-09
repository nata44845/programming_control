package Model.Animal;

import Model.Pack.Camel;
import Model.Pack.Donkey;
import Model.Pack.Horse;
import Model.Pet.Cat;
import Model.Pet.Dog;
import Model.Pet.Hamster;

public class AnimalCreator extends Creator {

    @Override
    protected Animal createNewAnimal(AnimalType type) {

        switch (type) {
            case Cat:
                return new Cat();
            case Dog:
                return new Dog();
            case Hamster:
                return new Hamster();
            case Horse:
                return new Horse();
            case Camel:
                return new Camel();
            case Donkey:
                return new Donkey();
        }
        return null;
    }
}
