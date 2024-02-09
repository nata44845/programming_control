package View.Commands;

import View.ConsoleUI;

public class ShowCommands extends Command {

    public ShowCommands(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Показать список команд";
    }

    public void execute() {
        consoleUI.showCommands();
    }
}
