package commands;

import commands.interfaces.CommandWithoutParameters;

/**
 *  'clear' command. Clears the collection.
 */
public class Clear extends Command implements CommandWithoutParameters {

    public Clear(String consoleName) {
        super(consoleName, "<Без параметров> Очищает коллекцию", "Коллекция очищена!");
    }

    @Override
    public void execute() {
        this.collectionManager.getCollection().clear();
        printSuccess();
    }
}


