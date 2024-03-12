package managers;

import model.Person;
import model.StudyGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

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
                + "Последняя дата инициализации:" + lastUpdateDate;
    }
    public String getCollectionFileName(){
        return collectionFileName;
    }
    public PriorityQueue<StudyGroup> getCollection(){
        return collection;
    }
    public ConsoleHandler getConsoleHandler(){
        return consoleHandler;
    }
    public StudyGroup maxByGroupAdmin(){
        ArrayList<Person> groupsAdmins = getSortGroupAdmin();
        Collections.sort(groupsAdmins);
        for (StudyGroup studyGroup: collection){
            if (studyGroup.getGroupAdmin().equals(groupsAdmins.get(collection.size()-1))){
                return studyGroup;
            }
        }
        return null;
    }
    public StudyGroup[] getElementsLessThanGroupAdmin(float weight){
        PriorityQueue<StudyGroup> elements = new PriorityQueue<>();
        for (StudyGroup studyGroup:collection){
            if (studyGroup.getGroupAdmin().getWeight()<weight){
                elements.add(studyGroup);
            }
        }
        return elements.toArray(new StudyGroup[0]);
    }
    public ArrayList<Person> getSortGroupAdmin(){
        ArrayList<Person> groupsAdmins = new ArrayList<>();
        for (StudyGroup studyGroup : collection){
            groupsAdmins.add(studyGroup.getGroupAdmin());
        }
        Collections.sort(groupsAdmins);
        return groupsAdmins;
    }
}
