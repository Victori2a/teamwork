package commands;

import commands.interfaces.CommandWithoutParameters;

/**
 * 'info' command. Prints information about the collection.
 */
public class Info extends Command implements CommandWithoutParameters {
    public Info(String consoleName) {
        super(consoleName, "<Без параметров> Выводит информацию о коллекции");
    }

    public void execute() {
        this.collectionManager.getConsoleHandler().println(this.collectionManager.getInformation());
    }
}