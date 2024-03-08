import exceptions.CommandNotExistsException;
import exceptions.WrongParameterException;
import managers.CollectionManager;
import managers.CommandManager;
import managers.ConsoleHandler;
import managers.FileManager;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws WrongParameterException, CommandNotExistsException {
        CommandManager commandManager = new CommandManager();
        Scanner scanner = new Scanner(System.in);
        ConsoleHandler consoleHandler = new ConsoleHandler(scanner, commandManager);
        CollectionManager collectionManager = new CollectionManager(consoleHandler);
        commandManager.setCollectionManager(collectionManager);
//        FileManager.readCollectionFromCSV("C:/Users/user/Desktop/mine/java/coll4.csv", consoleHandler);
        consoleHandler.listen();
    }
}