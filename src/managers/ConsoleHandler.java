package managers;

import exceptions.*;
import model.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Operates command input.
 */
public class ConsoleHandler {
    private Scanner scanner;
    private CommandManager commandManager;
    public ConsoleHandler(Scanner scanner, CommandManager commandManager){
        this.scanner = scanner;
        this.commandManager = commandManager;
    }

    /**
     * @return scanner + \n to Console
     */
    public String collectionFileNameRequest(){
        System.out.println("Введите путь к файлу коллекции: ");
        return scanner.nextLine();
    }
    /**
     * Prints >>>: Prompts the user to enter to Console
     */
    public void listen() throws IncorrectFilenameException, ElementNotFoundException, IOException, WrongParameterException, CommandNotExistsException, NullUserRequestException {
        while (true){
            print(">>>");
            String request = scanner.nextLine();
            commandManager.exec(request);
        }
    }
    /**
     * Prints obj.toString() + \n to Console
     * @param obj Object to print
     */
    public void println(Object obj) {
        System.out.println(obj.toString());
    }

    /**
     * Prints obj.toString() to Console
     * @param obj Object to print
     */
    public void print(Object obj) {
        System.out.print(obj.toString());
    }

    /**
     * Prints advice: advice.toString() to Console
     * @param advice Advice to print
     */
    public void printAdvice(String advice) {
        System.out.println("Совет: " + advice);
    }

    /**
     * Prints error: obj.toString() to Console
     * @param message Error to print
     */
    public void printError(String message) {
        System.out.println("Ошибка: " + message);
    }

    /**
     * @return command manager
     */
    public CommandManager getCommandManager(){
        return commandManager;
    }
    public String askEyeColor(EyeColor[] values) {
        println("Введите номер цвета глаз: ");
        for (EyeColor type : values) {
            println(type.ordinal() + 1 + ") " + type.name());
        }
        return scanner.nextLine();
    }

    /**
     * @param values are array ith some types of hair color
     * @return next line
     */
    public String askHairColor(HairColor[] values) {
        println("Введите номер цвета волос: ");
        for (HairColor type : values) {
            println(type.ordinal() + 1 + ") " + type.name());
        }
        return scanner.nextLine();
    }

    /**
     * @param values are array ith some types of nationality
     * @return next line
     */
    public String askCountry(Country[] values) {
        println("Введите номер страны: ");
        for (Country type : values) {
            println(type.ordinal() + 1 + ") " + type.name());
        }
        return scanner.nextLine();
    }

    /**
     * @param values are array ith some types of format of education
     * @return next line in Console
     */
    public String askFormOfEducation(FormOfEducation[] values) {
        println("Введите номер типа вашей формы обучения: ");
        for (FormOfEducation type : values) {
            println(type.ordinal() + 1 + ") " + type.name());
        }
        return scanner.nextLine();
    }

    /**
     * @param message
     * @return next line in Console
     */
    public String ask(String message) {
        print(message);
        return scanner.nextLine();
    }

    /**
     * Collects information about the fields that needs to be changed
     * @return  next line in Console
     */
    public String askWhatToChange() {
        println("Выберите, что Вы хотите поменять, и укажите соответствующие номера характеристик через пробел: ");
        Field[] fields = StudyGroup.class.getDeclaredFields();
        List<Field> filteredFields = new ArrayList<>();

        for (Field field : fields) {
            if (!field.getName().equals("id") && !field.getName().equals("creationDate")) {
                filteredFields.add(field);
            }
        }
        Field[] resultingArray = filteredFields.toArray(new Field[0]);
        for (int i = 1; i <= resultingArray.length; i++) {
            println(i + ") " + resultingArray[i-1].getName());
        }
        return scanner.nextLine();
    }
}
