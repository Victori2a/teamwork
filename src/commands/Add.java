package commands;

import commands.interfaces.CommandWithoutParameters;
import model.StudyGroup;

/**
 * 'add' command. Adds a new element to the collection.
 */
public class Add extends Command implements CommandWithoutParameters {
    public Add(String consoleName) {
        super(consoleName, "<Без параметров> Добавляет новый элемент в коллекцию");
    }

    public void execute() {
        StudyGroup newElement = this.collectionManager.groupnew();
        this.collectionManager.addNewElement(newElement);
    }
}