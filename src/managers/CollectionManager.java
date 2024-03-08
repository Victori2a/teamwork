package managers;

import model.StudyGroup;

import java.time.LocalDate;
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
}
