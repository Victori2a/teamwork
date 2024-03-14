package commands;

import commands.interfaces.CommandWithoutParameters;

/**
 * 'head' command. Prints the first element of the collection
*/
public class Head extends Command implements CommandWithoutParameters {

    public Head(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Выводит первый элемент");
    }
    @Override
    public void execute() {
        if (!this.collectionManager.getCollection().isEmpty()) {
            this.collectionManager.getConsoleHandler().println(this.collectionManager.getCollection().peek());
        } else {
            this.collectionManager.getConsoleHandler().println("В коллекции нет элементов");
        }

    }

}

