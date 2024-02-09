package View.Commands;

import View.ConsoleUI;

public class AddCommands extends Command {

    public AddCommands(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Обучить животное новым командам";
    }

    public void execute() {
        consoleUI.addCommands();
    }
}
