import commands.*;
import exceptions.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.ConsoleHandler;
import managers.FileManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws CommandNotExistsException, IncorrectFilenameException, ElementNotFoundException, WrongParameterException, IOException, NullUserRequestException {
        CommandManager commandManager = new CommandManager();
        Scanner scanner = new Scanner(System.in);
        ConsoleHandler consoleHandler = new ConsoleHandler(scanner, commandManager);
        CollectionManager collectionManager = new CollectionManager(consoleHandler);
        commandManager.setCollectionManager(collectionManager);
        commandManager.addCommands(
                new Save("save"),
                new Exit("exit"),
                new ExecuteScript("execute_script"),
                new MaxByGroupAdmin("max_by_admin"),
                new CountLessThanGroupAdmin("count_less_than_groupAdmin"),
                new FieldAscendingGroupAdmin("field_ascending_groupAdmin"),
                new Help("help"),
                new Info("info"),
                new Show("show"),
                new Clear("clear"),
                new Head("head"),
                new RemoveHead("remove_head"),
                new RemoveById("remove_by_id"));
//        FileManager.readCollectionFromCSV("C:/Users/user/Desktop/mine/java/coll4.csv", consoleHandler);
        consoleHandler.listen();

    }
}