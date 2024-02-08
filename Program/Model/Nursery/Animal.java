package Model.Nursery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Animal {
    protected int id;
    protected String name;
    protected LocalDate birth;

    public Animal() {
    }

    public Animal(String name, LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

    public void setId(int petId) {
        this.id = petId;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getBirthday() {
        if (birth != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return formatter.format(birth);
        } else
            return null;
    }

    @Override
    public String toString() {
        System.out.println("Hi1");
        return String.format("%d. %s: имя: %s, дата рождения: %s ", getId(), getClass().getSimpleName(), name,
                getBirthday());
    }

}