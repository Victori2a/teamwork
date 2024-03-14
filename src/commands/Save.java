package commands;

import commands.interfaces.CommandWithParameters;
import commands.interfaces.CommandWithoutParameters;
import exceptions.IncorrectFilenameException;
import managers.FileManager;

/**
 * 'save' command. Saves the collection to the file.
 */
public class Save extends Command implements CommandWithoutParameters, CommandWithParameters{
    public Save(String consoleName){
        super(consoleName, "<необязательно <Путь к файлу>. Сохраняет коллекцию в файл", "Коллекция сохранена!");
    }
    @Override
    public void execute() {
        FileManager.writeCollectionToCSV(collectionManager.getCollection(), collectionManager.getCollectionFileName());
        printSuccess();
    }
    @Override
    public void execute(String... parameters) {
        if (parameters.length == 0) execute();
        else {
            try {
                if (parameters[0].matches("^\\D.*")) {
                    if (parameters[0].matches(".*\\.csv$")) {
                        String filename = parameters[0];
                        FileManager.writeCollectionToCSV(collectionManager.getCollection(), filename);
                    } else throw new IncorrectFilenameException("Расширение файла должно быть .csv");
                } else throw new IncorrectFilenameException("Строка не должна начинаться с числа");
            } catch (IncorrectFilenameException e) {
                collectionManager.getConsoleHandler().printError(e.toString());
            }
        }
        printSuccess();
    }
}
