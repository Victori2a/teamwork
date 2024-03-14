package commands;

import commands.interfaces.CommandWithoutParameters;

/**
 * 'remove_head' command. Prints and deletes the first element from collection.
 */
public class RemoveHead extends Command implements CommandWithoutParameters {
    public RemoveHead(String nameInConsole) {
        super(nameInConsole, "Вывoдит и удаляет первый элемент");
    }
    @Override
    public void execute() {
        if (!this.collectionManager.getCollection().isEmpty()) {
            this.collectionManager.getConsoleHandler().println(this.collectionManager.getCollection().poll());
        } else {
            this.collectionManager.getConsoleHandler().println("В коллекции нет элементов");
        }

    }

}


