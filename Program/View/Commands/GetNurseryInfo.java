package View.Commands;

import View.ConsoleUI;

public class GetNurseryInfo extends Command {
    public GetNurseryInfo(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Получить список";
    }

    public void execute() {
        consoleUI.getNurseryInfo();
    }
}
