package commands;

import exceptions.WrongParameterException;
import managers.CollectionManager;

public abstract class Command {
    private final String nameInConsole;
    private final String description;
    private String successPhrase;
    protected CollectionManager collectionManager;

    /**
     * Abstract Command class contains name, description and success phrase.
     */
    protected Command(String nameInConsole, String description, String successPhrase) {
        this.nameInConsole = nameInConsole;
        this.description = description;
        this.successPhrase = successPhrase;
    }
    protected Command(String nameInConsole, String description) {
        this.nameInConsole = nameInConsole;
        this.description = description;
    }

    /**
     * @param collectionManager
     */
    public void setCollectionManager(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * @return Name of command in console.
     */
    public String getNameInConsole(){
        return nameInConsole;
    }

    /**
     * @return Description of the command.
     */
    public String getDescription(){
        return description;
    }

    /**
     * @throws WrongParameterException
     */
    public void execute() throws WrongParameterException{
    }

    /**
     * Outputs the phrase to the console command execution field
     */
    protected void printSuccess() {
        collectionManager.getConsoleHandler().println(successPhrase);
    }
}
