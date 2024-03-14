package managers;

import commands.Command;
import commands.interfaces.CommandWithParameters;
import commands.interfaces.CommandWithoutParameters;
import exceptions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Operates the commands.
 */
public class CommandManager {
    private final HashSet<Command> commands = new HashSet<>();
    private CollectionManager collectionManager;

    /**
     * Executes the command
     * @param userInput
     */
    public void exec(String userInput) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException, CommandNotExistsException, NullUserRequestException, IOException{
        try {
            String[] splitted = splitUserRequest(userInput);
            String commandName = splitted[0];
            if (!getConsoleCommandsNames().contains(commandName))
                throw new CommandNotExistsException("Такой команды нет.");
            for (Command command : commands) {
                if (command.getNameInConsole().equals(commandName)) {
                    String[] parameters = new String[splitted.length - 1];
                    for (int i = 1; i < splitted.length; i++) {
                        parameters[i - 1] = splitted[i];
                    }
                    if (command instanceof CommandWithParameters && parameters.length != 0) {
                        ((CommandWithParameters) command).execute(parameters);
                    } else if (command instanceof CommandWithoutParameters) {
                        ((CommandWithoutParameters) command).execute();
                    } else if (command instanceof CommandWithParameters && parameters.length == 0) {
                        throw new WrongParameterException("Вы не ввели параметр.");
                    }
                }
            }
        } catch (CommandNotExistsException | WrongParameterException e) {
            collectionManager.getConsoleHandler().printError(e.toString());
            //collectionManager.getConsoleHandler().listen();
        }
    }

    /**
     * @param request
     * @return the name of the entered command and the parameter, if it is entered
     * @throws NullUserRequestException
     */
    private String[] splitUserRequest(String request) throws NullUserRequestException {
        if (request.isEmpty()) throw new NullUserRequestException("Введена пустая строка");
        if (!request.contains(" ")) return new String[]{request};
        String command = request.split(" ", 2)[0];
        String[] parameters = request.split(" ", 2)[1].split(" ");
        if (parameters.length != 0) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i].isEmpty()) {
                    parameters[i] = null;
                }
            }
        }
        String[] processed;
        if (Validator.isArrayConsistsOfOnlyNull(parameters)) {
            processed = new String[]{command};
            return processed;
        } else {
            processed = new String[parameters.length + 1];
            processed[0] = command;
            System.arraycopy(parameters, 0, processed, 1, parameters.length);
        }
        return processed;
    }

    /**
     * @return names of the manager's commands.
     */
    public HashSet<String> getConsoleCommandsNames(){
        HashSet<String> commandNames = new HashSet<>();
        for (Command command: commands){
            commandNames.add(command.getNameInConsole());
        }
        return commandNames;
    }


    /**
     * @param commands
     */
    public void addCommands(Command... commands){
        this.commands.addAll(Arrays.asList(commands));
        for (Command command : commands){
            command.setCollectionManager(collectionManager);
        }
    }

    /**
     * @param collectionManager
     */
    public void setCollectionManager(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        for (Command command: commands){
            command.setCollectionManager(collectionManager);
        }
    }

    /**
     * @param commands to executes.
     */
    public void processFileCommands(String[] commands) throws WrongParameterException, CommandNotExistsException, IncorrectFilenameException, ElementNotFoundException, IOException, NullUserRequestException {
        int i = 0;
        System.out.println("process" + commands.length);
        while (i < commands.length) {
            exec(commands[i]);
            i++;
        }
    }

    /**
     * @return HahSet of manager's commands.
     */
    public HashSet<Command> getCommands(){
        return commands;
    }
}
