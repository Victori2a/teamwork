package commands;

import managers.CollectionManager;

public abstract class Command {
    private final String nameInConsole;
    private final String description;
    protected CollectionManager collectionManager;

    protected Command(String nameInConsole, String description) {
        this.nameInConsole = nameInConsole;
        this.description = description;
    }

    public void setCollectionManager(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public String getNameInConsole(){
        return nameInConsole;
    }
    public String getDescription(){
        return description;
    }
    public abstract void execute();
}
