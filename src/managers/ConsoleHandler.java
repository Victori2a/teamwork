package managers;

import exceptions.*;

import java.io.*;
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
}
