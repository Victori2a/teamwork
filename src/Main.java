import managers.CommandManager;
import managers.ConsoleHandler;
import managers.FileManager;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        CommandManager commandManager = new CommandManager();
        Scanner scanner = new Scanner(System.in);
        ConsoleHandler consoleHandler = new ConsoleHandler(scanner, commandManager);
        FileManager.readCollectionFromCSV("C:/Users/user/Desktop/mine/java/coll4.csv", consoleHandler);
    }
}