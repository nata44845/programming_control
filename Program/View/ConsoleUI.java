package View;

import Presenter.Presenter;
import FileWork.FileHandler;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class ConsoleUI implements View {

    private static final String INPUT_ERROR = "Вы ввели неверное значение";
    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        presenter = new Presenter(this, new FileHandler());
        work = true;
        menu = new MainMenu(this);
    }

    public void randomAnimals(int num) {
        Random rnd = new Random();

        String[] names = new String[] {"Маша", "Паша", "Саня"};

        for (int i = 0; i < num; i++) {
            presenter.addItem(rnd.nextInt(6)+1,names[rnd.nextInt(names.length)], null);
        }
    }

    @Override
    public void printAnswer(String text) {
        System.out.println(text);
    }

    @Override
    public void start() {
        title();

        while (work) {
            try {
                printMenu();
                execute();
            } catch (NumberFormatException e) {
                inputError();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void finish() {
        System.out.println("До свидания");
        work = false;
    }

    public void getNurseryInfo() {
        presenter.getNurseryInfo();
    }

    public void addItem() {
        System.out.println("Добавление данных");
                
        System.out.println("Введите тип животного");
        String animal_type = scanner.nextLine();
        int a_type = 0;
        if (animal_type != "" && checkTextForInt(animal_type)) {
            a_type = Integer.parseInt(animal_type);
        } 

        System.out.println("Введите название");
        String name = scanner.nextLine();

        System.out.println("Укажите дату рождения");
        String dateString = scanner.nextLine();
        LocalDate birth = null;
        // if (dateString != "")
        //     birth = LocalDate.F(dateString);

        presenter.addItem(a_type, name, birth);
    }

    private void title() {
        System.out.println("Питомник");
    }

    private void execute() {
        String line = scanner.nextLine();
        if (checkTextForInt(line)) {
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)) {
                menu.execute(numCommand);
            }
        }
    }

    private boolean checkTextForInt(String text) {
        if (text.matches("[0-9]+")) {
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private boolean checkCommand(int numCommand) {
        if (numCommand <= menu.getSize()) {
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private void printMenu() {
        System.out.println(menu.menu());
    }

    private void inputError() {
        System.out.println(INPUT_ERROR);
    }
}
