package commands;

import commands.interfaces.CommandWithoutParameters;
import managers.FileManager;

public class Save extends Command implements CommandWithoutParameters {
    public Save(String consoleName){
        super(consoleName, "Сохранить коллекцию в файл");
    }
    @Override
    public void execute() {
        FileManager.writeCollectionToCSV(collectionManager.getCollection(), collectionManager.getCollectionFileName());
    }
}
