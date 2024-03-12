package commands;

import commands.interfaces.CommandWithoutParameters;
import model.Person;

public class FieldAscendingGroupAdmin extends Command implements CommandWithoutParameters {
    public FieldAscendingGroupAdmin(String nameInConsole) {
        super(nameInConsole, "Выводит значение поля 'Староста группы' всех элементов в порядке возрастания");
    }
    public void execute(){
        Person[] groupAdmins = collectionManager.getSortGroupAdmin().toArray(new Person[0]);
        for (Person groupAdmin : groupAdmins){
            collectionManager.getConsoleHandler().println(groupAdmin.getName());
        }
    }
}
