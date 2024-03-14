package commands;

import commands.interfaces.CommandWithoutParameters;
import model.StudyGroup;

import java.util.Iterator;

/**
 * 'show' command. Shows information about all elements of the collection.
 */
public class Show extends Command implements CommandWithoutParameters {
    public Show(String nameInConsole) {
        super(nameInConsole, "Вывести все элементы коллекции строкой");
    }
    @Override
    public void execute() {
        if (!this.collectionManager.getCollection().isEmpty()) {
            Iterator var1 = this.collectionManager.getCollection().iterator();

            while(var1.hasNext()) {
                StudyGroup group = (StudyGroup) var1.next();
                this.collectionManager.getConsoleHandler().println(group);
            }
        } else {
            this.collectionManager.getConsoleHandler().println("В коллекции нет элементов");
        }

    }

}
