package managers;

import java.util.Scanner;

public class ConsoleHandler {
    private Scanner scanner;
    private CommandManager commandManager;
    public ConsoleHandler(Scanner scanner, CommandManager commandManager){
        this.scanner = scanner;
        this.commandManager = commandManager;
    }
    public String collectionFileNameRequest(){
        System.out.println("Введите путь к файлу коллекции: ");
        return scanner.nextLine();
    }
    public void listen(){
        while (true){
            print(">>>");
            String request = scanner.nextLine();
            commandManager.exec(request);
        }
    }
    public void println(Object obj) {
        System.out.println(obj.toString());
    }

    public void print(Object obj) {
        System.out.print(obj.toString());
    }

    public void printAdvice(String advice) {
        System.out.println("Совет: " + advice);
    }

    public void printError(String message) {
        System.out.println("Ошибка: " + message);
    }
}
