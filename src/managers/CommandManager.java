package managers;

import commands.Command;

import java.util.Arrays;
import java.util.HashSet;

public class CommandManager {
    private final HashSet<Command> commands = new HashSet<>();
    private CollectionManager collectionManager;
    public void exec(String userInput){
        String[] splitted = splitUserRequest(userInput);
    }
    private String[] splitUserRequest(String request){
        if (request.isEmpty()) System.out.println("Введена пустая строка");
        if (!request.contains(" ")) return new String[]{request};
        String command = request.split(" ", 2)[0];
        String[] parameters = request.split(" ", 2)[1].split(" ");
        if (parameters.length !=0){
            for (int i = 0; i < parameters.length; i++){
                if (parameters[i].isEmpty()){
                    parameters[i] = null;
                }
            }
        }
        return null;
    }
    public void addCommands(Command... commands){
        this.commands.addAll(Arrays.asList(commands));
        for (Command command : commands){
            command.setCollectionManager(collectionManager);
        }
    }
}
