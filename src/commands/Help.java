package commands;

import commands.interfaces.CommandWithoutParameters;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 'help' command. Information about available commands
 */
public class Help extends Command implements CommandWithoutParameters {
    public Help(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Информация обо всех командах");
    }

    public void execute() {
        HashSet<Command> commands = this.collectionManager.getConsoleHandler().getCommandManager().getCommands();
        StringBuilder output = new StringBuilder("Список доступных команд:\n");
        Iterator var = commands.iterator();

        while(var.hasNext()) {
            Command command = (Command)var.next();
            output.append(String.format("%-35s%-1s%n", command.getNameInConsole(), command.getDescription()));
        }

        this.collectionManager.getConsoleHandler().println(output);
    }
}