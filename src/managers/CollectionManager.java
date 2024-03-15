package managers;

import exceptions.ElementNotFoundException;
import exceptions.NullUserRequestException;
import exceptions.WrongParameterException;
import model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

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

    /**
     * @return new study group
     */
    public StudyGroup groupnew() {
        return new StudyGroup(
                (int) (Math.random() * Integer.MAX_VALUE),
                nameRequest(),
                coordinatesRequest(),
                LocalDate.now(),
                studentCountRequest(),
                expelledStudentsRequest(),
                shouldbeExpelled(),
                formOfEducationRequest(),
                GroupAdminRequest());
    }

    /**
     * Add new element in collection
     * @param studyGroup
     */
    public void addNewElement(StudyGroup studyGroup) {
        collection.add(studyGroup);
        lastUpdateDate = LocalDate.now();
    }

    /**
     * Updates a collection element by id
     * @param id
     * @param element
     */
    public void setElementById(Integer id, StudyGroup element) {
        collection = collection.stream().map(e -> (e.getId().equals(id) ? element : e)).collect(Collectors.toCollection(PriorityQueue::new));
    }

    /**
     * @return name of study group
     */
    public String nameRequest() {
        String name = consoleHandler.ask("Введите название группы: ");
        try {
            if (Validator.isValidName(name)) {
                return name;
            } else {
                throw new WrongParameterException("Имя не может быть пустым");
            }
        } catch (WrongParameterException e) {
            consoleHandler.printError(e.toString());
            return nameRequest();
        }
    }

    /**
     * @return coordinates x and y
     */
    public Coordinates coordinatesRequest() {
        String response = consoleHandler.ask("Введите через пробел координаты x и y (числа целые): ");
        double x;
        long y;
        try {
            if (response.split(" ").length < 2) {
                throw new WrongParameterException("Введены не все параметры.");
            }
            if (Validator.isCorrectNumber(response.split(" ")[0], Double.class) && Validator.isCorrectNumber(response.split(" ")[1], Long.class)) {
                if (!Validator.isNull(response.split(" ")[0]) && !Validator.isNull(response.split(" ")[1])) {
                    x = Double.parseDouble(response.split(" ")[0]);
                    y = Long.parseLong(response.split(" ")[1]);
                    if (x <= -871) {
                        throw new WrongParameterException("x должен быть больше -871.");
                    } else {
                        return new Coordinates(x, y);
                    }
                } else {
                    throw new WrongParameterException("x или y не может быть null.");
                }
            } else {
                throw new WrongParameterException("Неверно введены числа.");
            }
        } catch (WrongParameterException e) {
            consoleHandler.printError(e.toString());
            return coordinatesRequest();
        }
    }

    /**
     * @return count of students
     */
    public long studentCountRequest() {
        long result = -1;
        String response = consoleHandler.ask("Введите количество студентов(целое число): ");

        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Введена пустая строка");
            }
            if (response.contains(" ")) {
                String[] splitted = response.split(" ");
                if (Validator.isCorrectNumber(splitted[0], Long.class)) {
                    result = Long.parseLong(splitted[0]);
                }
            } else if (Validator.isCorrectNumber(response, Long.class)) {
                result = Long.parseLong(response);
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
            if (result > 0) {
                return result;
            } else {
                throw new WrongParameterException("Количество студентов не может быть меньше нуля.");
            }
        } catch (WrongParameterException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return studentCountRequest();
        }
    }

    /**
     * @return expelled students
     */
    public long expelledStudentsRequest() {
        long result = -1;
        String response = consoleHandler.ask("Введите количество отчисленных студентов(целое число): ");

        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Введена пустая строка");
            }
            if (response.contains(" ")) {
                String[] splitted = response.split(" ");
                if (Validator.isCorrectNumber(splitted[0], Long.class)) {
                    result = Long.parseLong(splitted[0]);
                }
            } else if (Validator.isCorrectNumber(response, Long.class)) {
                result = Long.parseLong(response);
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
            if (result > 0) {
                return result;
            } else {
                throw new WrongParameterException("Количество отчисленных студентов не может быть меньше нуля.");
            }
        } catch (WrongParameterException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return expelledStudentsRequest();
        }
    }

    /**
     * @return should be expelled students
     */
    public long shouldbeExpelled() {
        long result = -1;
        String response = consoleHandler.ask("Введите количество потенциально отчисленных студентов(целое число): ");
        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Введена пустая строка");
            }
            if (response.contains(" ")) {
                String[] splitted = response.split(" ");
                if (Validator.isCorrectNumber(splitted[0], Long.class)) {
                    result = Long.parseLong(splitted[0]);
                }
            } else if (Validator.isCorrectNumber(response, Long.class)) {
                result = Long.parseLong(response);
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
            if (result > 0) {
                return result;
            } else {
                throw new WrongParameterException("Количество потенциально отчисленных студентов не может быть меньше нуля.");
            }
        } catch (WrongParameterException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return shouldbeExpelled();
        }
    }

    /**
     * @return form of education
     */
    public FormOfEducation formOfEducationRequest() {
        String response = consoleHandler.askFormOfEducation(FormOfEducation.values());
        String num;
        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Поле не может быть пустым.");
            }
            if (response.contains(" ")) {
                num = response.split(" ")[0];
                if (Validator.isNull(num)) {
                    throw new WrongParameterException("Строка введена неверно.");
                }

            } else {
                num = response;
            }
            if (Validator.isCorrectNumber(num, Integer.class)) {
                if (Integer.parseInt(num) <= FormOfEducation.values().length && Integer.parseInt(num) >= 1) {
                    return FormOfEducation.values()[Integer.parseInt(num) - 1];
                } else {
                    throw new WrongParameterException("Введено неверный номер.");
                }
            } else {
                throw new WrongParameterException("Неправильно введено число.");
            }

        } catch (WrongParameterException | NumberFormatException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return formOfEducationRequest();
        }
    }

    /**
     * @return person witch group admin
     */
    public Person GroupAdminRequest(){
        String name = GroupAdminNameRequest();
        float weight = GroupAdminWeightRequest();
        EyeColor eyeColor = GroupAdminEyeColorRequest();
        HairColor hairColor = GroupAdminHairColorRequest();
        Country country = GroupAdminCountryRequest();
        return new Person(name, weight, eyeColor, hairColor, country);
    }
    public String GroupAdminNameRequest(){
        String name = consoleHandler.ask("Введите имя админа группы: ");
        try {
            if (Validator.isValidName(name)) {
                return name;
            } else {
                throw new WrongParameterException("Имя не может быть пустым.");
            }
        } catch (WrongParameterException e) {
            consoleHandler.printError(e.toString());
            return GroupAdminNameRequest();
        }
    }

    /**
     * @return weight of admin
     */
    public float GroupAdminWeightRequest(){
        float result = -1;
        String response = consoleHandler.ask("Введите вес админа группы (вещественное число): ");
        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Введена пустая строка");
            }
            if (response.contains(" ")) {
                String[] splitted = response.split(" ");
                if (Validator.isCorrectNumber(splitted[0], Float.class)) {
                    result = Float.parseFloat(splitted[0]);
                }
            } else if (Validator.isCorrectNumber(response, Float.class)) {
                result = Float.parseFloat(response);
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
            if (result > 0) {
                return result;
            } else {
                throw new WrongParameterException("Вес админа группы не может быть меньше нуля.");
            }
        } catch (WrongParameterException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return GroupAdminWeightRequest();
        }
    }

    /**
     * @return eye color of admin
     */
    public EyeColor GroupAdminEyeColorRequest(){
        String response = consoleHandler.askEyeColor(EyeColor.values());
        String num;
        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Поле не может быть пустым.");
            }
            if (response.contains(" ")) {
                num = response.split(" ")[0];
                if (Validator.isNull(num)) {
                    throw new WrongParameterException("Строка введена неверно.");
                }
            } else {
                num = response;
            }
            if (Validator.isCorrectNumber(num, Integer.class)) {
                if (Integer.parseInt(num) <= EyeColor.values().length && Integer.parseInt(num) >= 1) {
                    return EyeColor.values()[Integer.parseInt(num) - 1];
                } else {
                    throw new WrongParameterException("Введено неверный номер.");
                }
            } else {
                throw new WrongParameterException("Неправильно введено число.");
            }

        } catch (WrongParameterException | NumberFormatException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return GroupAdminEyeColorRequest();
        }
    }

    /**
     * @return hair color of admin
     */
    public HairColor GroupAdminHairColorRequest(){
        String response = consoleHandler.askHairColor(HairColor.values());
        String num;
        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Поле не может быть пустым.");
            }
            if (response.contains(" ")) {
                num = response.split(" ")[0];
                if (Validator.isNull(num)) {
                    throw new WrongParameterException("Строка введена неверно.");
                }
            } else {
                num = response;
            }
            if (Validator.isCorrectNumber(num, Integer.class)) {
                if (Integer.parseInt(num) <= HairColor.values().length && Integer.parseInt(num) >= 1) {
                    return HairColor.values()[Integer.parseInt(num) - 1];
                } else {
                    throw new WrongParameterException("Введено неверный номер.");
                }
            } else {
                throw new WrongParameterException("Неправильно введено число.");
            }

        } catch (WrongParameterException | NumberFormatException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return GroupAdminHairColorRequest();
        }
    }

    /**
     * @return nationality of admin
     */
    public Country GroupAdminCountryRequest(){
        String response = consoleHandler.askCountry(Country.values());
        String num;
        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Поле не может быть пустым.");
            }
            if (response.contains(" ")) {
                num = response.split(" ")[0];
                if (Validator.isNull(num)) {
                    throw new WrongParameterException("Строка введена неверно.");
                }
            } else {
                num = response;
            }
            if (Validator.isCorrectNumber(num, Integer.class)) {
                if (Integer.parseInt(num) <= Country.values().length && Integer.parseInt(num) >= 1) {
                    return Country.values()[Integer.parseInt(num) - 1];
                } else {
                    throw new WrongParameterException("Введено неверный номер.");
                }
            } else {
                throw new WrongParameterException("Неправильно введено число.");
            }

        } catch (WrongParameterException | NumberFormatException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return GroupAdminCountryRequest();
        }
    }
}
