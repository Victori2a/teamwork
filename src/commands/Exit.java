package commands;

import commands.interfaces.CommandWithoutParameters;

public class Exit extends Command implements CommandWithoutParameters {
    public Exit(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Завершить программу(без сохранения в файл)");
    }
    @Override
    public void execute() {
        System.exit(0);
    }
}
