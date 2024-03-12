package commands;

import commands.interfaces.CommandWithParameters;
import exceptions.*;
import managers.FileManager;

import java.io.IOException;

public class ExecuteScript extends Command implements CommandWithParameters {
    public ExecuteScript(String nameInConsole) {
        super(nameInConsole, "<Путь к файлу> Выполняет содержимое скрипта");
    }
    @Override
    public void execute(String... parameters) throws WrongParameterException {
        try {
            System.out.println("execute");
            FileManager.readCommands(parameters[0], collectionManager.getConsoleHandler().getCommandManager());
        } catch (IOException | WrongParameterException | IncorrectFilenameException | ElementNotFoundException | CommandNotExistsException | NullUserRequestException e) {
            throw new WrongParameterException(e.toString());
        }
    }
}
