package commands;

import commands.interfaces.CommandWithoutParameters;
import exceptions.WrongParameterException;
import model.StudyGroup;

/**
 * Command 'add_if_min'. Adds a new element to collection if it's less than the minimal one.
 */
public class AddIfMin extends Command implements CommandWithoutParameters {
    public AddIfMin(String consoleName) {
        super(consoleName, "<Без параметров> Добавляет новый элемент в коллекцию, если его значение минимальное среди всех элементов коллекции","Новый элемент добавлен!" );
    }

    @Override
    public void execute() throws WrongParameterException {
        StudyGroup newElement = this.collectionManager.groupnew();
        if (newElement != null) {
            Long MinimalPoint = Long.MAX_VALUE;

            for (var group : collectionManager.getCollection()) {
                if (MinimalPoint > newElement.getStudentsCount()) {
                    MinimalPoint = group.getStudentsCount();
                }
            }
            if (MinimalPoint == Long.MAX_VALUE) {
                this.collectionManager.getConsoleHandler().println("Коллекция пустая.");
            }
            if (newElement.getStudentsCount() < MinimalPoint) {
                this.collectionManager.addNewElement(newElement);
                printSuccess();
            } else {
                this.collectionManager.getConsoleHandler().println("Новый элемент не добавлен, так как не наименьший.");
            }
        }

    }
}
