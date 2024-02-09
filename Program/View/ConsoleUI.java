package View;

import Presenter.Presenter;
import FileWork.FileHandler;
import Model.Animal.AnimalType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleUI implements View {

    private static final String INPUT_ERROR = "Вы ввели неверное значение";
    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;
    DateTimeFormatter formatter;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        presenter = new Presenter(this, new FileHandler());
        work = true;
        menu = new MainMenu(this);
        formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    }

    public void randomAnimals(int num) {
        Random rnd = new Random();
        int type;


        long minDay = LocalDate.of(2010, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2023, 12, 31).toEpochDay();

        String[] names = new String[] { "Маша", "Паша", "Саня", "Сеня", "Васька", "Мурка" };
        String[][] commands = new String[][] { { "спать", "есть" }, { "сидать", "лежать", "голос" }, { "бежать", "есть" },
                { "но", "стоп", "тьпру" }, { "стоять", "лежать", "сидеть" }, { "стоять","бежать", "есть" } };

        for (int i = 0; i < num; i++) {
            type = rnd.nextInt(6);

            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

            presenter.addItem(type + 1, names[rnd.nextInt(names.length)], randomDate, 
                    commands[type][rnd.nextInt(commands[type].length)]+", "+commands[type][rnd.nextInt(commands[type].length)]);
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
        boolean flag = true;
        LocalDate birth = null;
        String name="", commands = "";

        System.out.println("Добавление данных");

        System.out.println("Введите тип животного (1-6)");
        String animal_type = scanner.nextLine();
        int a_type = 0;
        if (animal_type != "" && checkTextForInt(animal_type)) {
            a_type = Integer.parseInt(animal_type);
        }
        flag = checkType(a_type);

        if (flag) {
        System.out.println("Тип животного "+AnimalType.getType(a_type));
        System.out.println("Введите название");
        name = scanner.nextLine();
        flag = checkData("Имя", name);
        }

        if (flag) {
            System.out.println("Укажите дату рождения (ddMMyyyy)");
            String dateString = scanner.nextLine();

            flag = checkDate(dateString);
            if (flag)
                birth = LocalDate.parse(dateString, formatter);
        }

        if (flag) {
            System.out.println("Введите команды");
            commands = scanner.nextLine();
        }

        if (flag)
            presenter.addItem(a_type, name, birth, commands);

    }


    public void showCommands() {
        System.out.println("Список комманд");
        Integer num=0;

        System.out.print("Введите номер записи: ");
        String numString = scanner.nextLine();
        if (numString != "" && checkTextForInt(numString)) {
            num = Integer.parseInt(numString);
        }
        presenter.showCommands(num);
    }

    public void addCommands() {
        System.out.println("Обучить животное новым командам");
        Integer num=0;
        String commands = "";

        System.out.print("Введите номер записи: ");
        String numString = scanner.nextLine();
        if (numString != "" && checkTextForInt(numString)) {
            num = Integer.parseInt(numString);
            System.out.print("Введите список команд: ");
            commands = scanner.nextLine();
            if (checkData("Команды",commands))
                presenter.addCommands(num, commands);
        }

        
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

    private boolean checkType(Integer a_type) {
        if (AnimalType.getType(a_type) == null) {
            inputError();
            return false;
        } else {
            return true;
        }
    }

    private boolean checkData(String paramName, String data) {
        if (data.length() == 0) {
            inputError();
            return false;
        } else {
            return true;
        }
    }

    private boolean checkDate(String data) {
        try {
            formatter.parse(data);
            return true;
        } catch (Exception e) {
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
