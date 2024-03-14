package managers;

import exceptions.ElementNotFoundException;
import model.Person;
import model.StudyGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Operates the collection itself.
 */
public class CollectionManager {
    private ConsoleHandler consoleHandler;
    private LocalDate lastUpdateDate;
    private String collectionFileName;
    private PriorityQueue<StudyGroup> collection;
    private String information;
    public CollectionManager(ConsoleHandler consoleHandler){
         this.consoleHandler = consoleHandler;
         lastUpdateDate = LocalDate.now();
         loadCollection();
         updateInformation();
    }
    public void loadCollection(){
        String name = consoleHandler.collectionFileNameRequest();
        this.collection = FileManager.readCollectionFromCSV(name, consoleHandler);
        if (collection==null){
            loadCollection();
        }
        this.collectionFileName = name;
    }
    public void updateInformation(){
        this.information = "Тип коллекции: "+ collection.getClass()+"\n"
                + "Хранит объекты типа: " + getCollectionClassName() + "\n"
                + "Последняя дата инициализации:" + lastUpdateDate;
    }

    /**
     * @return The name of the file where the collection is located.
     */
    public String getCollectionFileName(){
        return collectionFileName;
    }

    /**
     * @return The collection itself.
     */
    public PriorityQueue<StudyGroup> getCollection(){
        return collection;
    }

    /**
     * @return consoleHandler of collection.
     */
    public ConsoleHandler getConsoleHandler(){
        return consoleHandler;
    }

    /**
     * @return Еhe study group with the maximum admin of the group.
     */
    public StudyGroup maxByGroupAdmin(){
        ArrayList<Person> groupsAdmins = getGroupAdmins();
        Collections.sort(groupsAdmins);
        for (StudyGroup studyGroup: collection){
            if (studyGroup.getGroupAdmin().equals(groupsAdmins.get(collection.size()-1))){
                return studyGroup;
            }
        }
        return null;
    }

    /**
     * @param  weight of the group admin
     * @return study group
     */
    public StudyGroup[] getElementsLessThanGroupAdmin(float weight){
        PriorityQueue<StudyGroup> elements = new PriorityQueue<>();
        for (StudyGroup studyGroup:collection){
            if (studyGroup.getGroupAdmin().getWeight()<weight){
                elements.add(studyGroup);
            }
        }
        return elements.toArray(new StudyGroup[0]);
    }

    /**
     * @return List of group admins.
     */
    public ArrayList<Person> getGroupAdmins(){
        ArrayList<Person> groupsAdmins = new ArrayList<>();
        for (StudyGroup studyGroup : collection){
            groupsAdmins.add(studyGroup.getGroupAdmin());
        }
        return groupsAdmins;
    }

    /**
     * @return information about collection.
     */
    public String getInformation() {
        return information;
    }

    /**
     * @return Name of the collection class.
     */
    private String getCollectionClassName() {
        Class<? extends PriorityQueue> dataType = collection.getClass();
        return dataType.getName();
    }

    /**
     * @param id of the study group.
     * @throws ElementNotFoundException
     */
    public void removeById(Integer id) throws ElementNotFoundException {
        collection.remove(getElementById(id));
        lastUpdateDate = LocalDate.now();
    }

    /**
     * @param id
     * @return stydy group
     * @throws ElementNotFoundException
     */
    public StudyGroup getElementById(Integer id) throws ElementNotFoundException {
        for (StudyGroup studyGroup : collection) {
            if (studyGroup.getId().equals(id)) return studyGroup;
        }
        throw new ElementNotFoundException("Элемента с таким id не существует");
    }
}
