package commands;

import commands.interfaces.CommandWithoutParameters;
import exceptions.WrongParameterException;
import model.StudyGroup;

import java.util.PriorityQueue;

public class MaxByGroupAdmin extends Command implements CommandWithoutParameters {
    public MaxByGroupAdmin(String nameInConsole){
        super(nameInConsole, "Выводит любой элемент из коллекции, у которого поле 'Староста группы' максимальное");
    }

    @Override
    public void execute() throws WrongParameterException {
        StudyGroup studyGroup = collectionManager.maxByGroupAdmin();
        collectionManager.getConsoleHandler().println(studyGroup);
    }
}
