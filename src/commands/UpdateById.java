package commands;

import commands.Command;
import commands.interfaces.CommandWithParameters;
import exceptions.ElementNotFoundException;
import exceptions.WrongParameterException;
import managers.Validator;
import model.Coordinates;
import model.FormOfEducation;
import model.Person;
import model.StudyGroup;

/**
 * 'update_by-id' command. Updates the information about selected studyGroup.
 */
public class UpdateById extends Command implements CommandWithParameters {
    public UpdateById(String consoleName) {
        super(consoleName, "<long id> Обновляет элемент коллекции по id", "Элемент успешно обновлен!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        try {
            StudyGroup element = collectionManager.getElementById(Integer.parseInt(parameters[0]));
            String name = element.getName();
            Coordinates coordinates = element.getCoordinates();
            Long studentsCount = element.getStudentsCount();
            Long expelledStudents = element.getExpelledStudents();
            Long shouldBeExpelled = element.getShouldBeExpelled();
            FormOfEducation formOfEducation = element.getFormOfEducation();
            Person groupAdmin = element.getGroupAdmin();

            String answer = collectionManager.getConsoleHandler().askWhatToChange();
            if (Validator.isStringWithIntegers(answer)) {
                String[] splitted = answer.split(" ");
                int[] fieldsNumbers = new int[splitted.length];
                for (int i = 0; i < fieldsNumbers.length; i++) {
                    fieldsNumbers[i] = Integer.parseInt(splitted[i]);
                }
                for (int num : fieldsNumbers) {
                    if (num > 7) {
                        throw new WrongParameterException("Число " + num + " не соответствует ни одному из полей");
                    }
                    switch (num) {
                        case 1 -> name = collectionManager.nameRequest();
                        case 2 -> coordinates = collectionManager.coordinatesRequest();
                        case 3 -> studentsCount = collectionManager.studentCountRequest();
                        case 4 -> expelledStudents = collectionManager.expelledStudentsRequest();
                        case 5 -> shouldBeExpelled = collectionManager.shouldbeExpelled();
                        case 6 -> formOfEducation = collectionManager.formOfEducationRequest();
                        case 7 -> groupAdmin = collectionManager.GroupAdminRequest();
                    }
                }
                StudyGroup updatedElement = new StudyGroup(
                        element.getId(),
                        name,
                        coordinates,
                        element.getCreationDate(),
                        studentsCount,
                        expelledStudents,
                        shouldBeExpelled,
                        formOfEducation,
                        groupAdmin);
                collectionManager.setElementById(Integer.parseInt(parameters[0]), updatedElement);
                printSuccess();
            } else throw new WrongParameterException("Строка должна состоять только из чисел и пробелов.");
        } catch (NumberFormatException e) {
            throw new WrongParameterException("Неправильно введен параметр.");
        } catch (ElementNotFoundException e) {
            collectionManager.getConsoleHandler().printError(e.toString());
            collectionManager.getConsoleHandler().printAdvice("Введите id существующего элемента. Используйте команду show для просмотра коллекции.");
        } catch (WrongParameterException e) {
            collectionManager.getConsoleHandler().printError(e.toString());
            execute(parameters);
        }
    }
}
