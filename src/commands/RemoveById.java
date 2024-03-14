package commands;

import commands.interfaces.CommandWithParameters;
import exceptions.ElementNotFoundException;
import exceptions.WrongParameterException;

/**
 * 'remove_by_id' command. Removes the element by its ID.
 */
public class RemoveById extends Command implements CommandWithParameters {
    public RemoveById(String nameInConsole) {
        super(nameInConsole, "<Integer id> Удаляет элемент из коллекции по его id", "Элемент удалён из коллекции");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        if (parameters[0].isEmpty()) throw new WrongParameterException("Параметр пуст.");
        try {
            collectionManager.removeById(Integer.parseInt(parameters[0]));
            printSuccess();
        } catch (NumberFormatException e) {
            throw new WrongParameterException("Параметр введен неверно.");
        } catch (ElementNotFoundException e) {
            collectionManager.getConsoleHandler().printError(e.toString());
        }
    }
}
