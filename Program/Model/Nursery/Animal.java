package Model.Nursery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Animal {
    protected int id;
    protected String name;
    protected LocalDate birth;
    protected String commands;

    public Animal() {
    }

    public Animal(String name, LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int petId) {
        this.id = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getBirthday() {
        if (birth != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return formatter.format(birth);
        } else
            return null;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return String.format("%d. %s: имя: %s, дата рождения: %s ", getId(), getClass().getSimpleName(), name,
                getBirthday());
    }

}