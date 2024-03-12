package commands;

import commands.interfaces.CommandWithParameters;
import exceptions.WrongParameterException;
import managers.Validator;
import model.StudyGroup;

public class CountLessThanGroupAdmin extends Command implements CommandWithParameters {
    public CountLessThanGroupAdmin(String nameInConsole) {
        super(nameInConsole, "Выводит количество элементов, значения поля 'Староста группы' меньше заданного");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        if (Validator.isCorrectNumber(parameters[0], Float.class)) {
        float weight = Float.parseFloat(parameters[0]);
        StudyGroup[] studyGroups = collectionManager.getElementsLessThanGroupAdmin(weight);
        collectionManager.getConsoleHandler().println(studyGroups.length);
        } else{
            throw new WrongParameterException("Неверно введён параметр");
        }
    }
}
