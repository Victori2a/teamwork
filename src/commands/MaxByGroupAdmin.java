package commands;

import commands.interfaces.CommandWithoutParameters;
import exceptions.WrongParameterException;
import model.StudyGroup;

import java.util.PriorityQueue;

/**
 * 'max_by_group_admin' command. Prints an item from the collection that has the maximum 'Group Headman' field.
 */
public class MaxByGroupAdmin extends Command implements CommandWithoutParameters {
    public MaxByGroupAdmin(String nameInConsole){
        super(nameInConsole, "<Без параметров> Выводит любой элемент из коллекции, у которого поле 'Староста группы' максимальное");
    }

    @Override
    public void execute() throws WrongParameterException {
        StudyGroup studyGroup = collectionManager.maxByGroupAdmin();
        collectionManager.getConsoleHandler().println(studyGroup);
    }
}
