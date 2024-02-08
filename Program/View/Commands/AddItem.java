package View.Commands;

import View.ConsoleUI;

public class AddItem extends Command {

    public AddItem(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Добавить животное";
    }

    public void execute() {
        consoleUI.addItem();
    }
}
