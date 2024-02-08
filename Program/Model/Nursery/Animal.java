package Model.Nursery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Animal {
    protected int Id;
    protected String name;
    protected LocalDate birthday;
    
    public void setId(int petId) {
        this.Id = petId;
    }

    public int getId() {
        return Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirth(LocalDate date) {
        this.birthday = date;
    }

    public LocalDate getBirth(){
        return birthday;
    }

    public String getBirthday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(birthday);
    }

    @Override
    public String toString() {
        return String.format("%d. %s: имя: %s, дата рождения: %s ", getId(), getClass().getSimpleName(), name, getBirthday());
    }

}