package commands;

import commands.interfaces.CommandWithoutParameters;
import model.Person;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 'field_ascending_groupAdmin' command. Prints the value of the 'Group Headman' field of all elements in ascending order.
 */
public class FieldAscendingGroupAdmin extends Command implements CommandWithoutParameters {
    public FieldAscendingGroupAdmin(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Выводит значение поля 'Староста группы' всех элементов в порядке возрастания");
    }
    public void execute(){
        ArrayList<Person> groupAdmins = collectionManager.getGroupAdmins();
        Collections.sort(groupAdmins);
        for (Person groupAdmin : groupAdmins){
            collectionManager.getConsoleHandler().println(groupAdmin.getName());
        }
    }
}
