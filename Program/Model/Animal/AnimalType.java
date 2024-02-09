package Model.Animal;

public enum AnimalType {

    Cat, 
    Dog, 
    Hamster, 
    Horse, 
    Camel, 
    Donkey;

    public static AnimalType getType (int typeId){
        switch (typeId){
            case 1:
                return AnimalType.Cat;
            case 2:
                return AnimalType.Dog;
            case 3:
                return AnimalType.Hamster;
            case 4:
                return AnimalType.Horse;
            case 5:
                return AnimalType.Camel;
            case 6:
                return AnimalType.Donkey;
            default:
                return null;
        }
    }
}
