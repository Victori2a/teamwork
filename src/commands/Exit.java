package commands;

import commands.interfaces.CommandWithoutParameters;

/**
 * 'exit' command. Closes the program.
 */
public class Exit extends Command implements CommandWithoutParameters {
    public Exit(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Завершает программу(без сохранения в файл)", "Программа завершена!");
    }
    @Override
    public void execute() {
        printSuccess();
        System.exit(0);
    }
}
