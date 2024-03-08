package managers;

import model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FileManager {
    public static PriorityQueue<StudyGroup> readCollectionFromCSV(String filename, ConsoleHandler consoleHandler) {
        PriorityQueue<StudyGroup> collection = new PriorityQueue<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            String line;
            while (scanner.hasNext()){
                line = scanner.next();
                String[] values = line.split(";");
                System.out.println(Arrays.toString(values));
                collection.add(parseStudyGroupFromStrings(values));
                System.out.println("Happy");
            }
            scanner.close();
            consoleHandler.println("Коллекция загружена!");
            consoleHandler.printAdvice("Напишите help для просмотра списка команд");
            return collection;
        } catch (IOException e) {
            consoleHandler.printError("Файл с таким именем не найден. Попробуйте еще раз.");
            return null;
        } catch (NullPointerException e) {
            consoleHandler.printError(e.toString());
            return null;
        }
    }
    public static void writeCollectionToCSV(Collection<StudyGroup> collection, String filename){
        String line;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.println("id;name;coordinateX;coordinateY;data;studentCount;expelledStudents;shouldBeExpelled;formOfEducation;nameAdmin;weight;eyeColor;hairColor;nationality");
            for (StudyGroup studyGroup : collection) {
                line = studyGroup.getId().toString() + ";" +
                        studyGroup.getName() + ";" +
                        studyGroup.getCoordinates().getX() + ";" +
                        studyGroup.getCoordinates().getY() + ";" +
                        studyGroup.getCreationDate().toString() + ";" +
                        studyGroup.getStudentsCount() + ";" +
                        studyGroup.getExpelledStudents() + ";" +
                        studyGroup.getShouldBeExpelled() + ";" +
                        studyGroup.getFormOfEducation().ordinal() + ";" +
                        studyGroup.getGroupAdmin().getName()+ ";" +
                        studyGroup.getGroupAdmin().getWeight()+ ";" +
                        studyGroup.getGroupAdmin().getEyecolor()+";"+
                        studyGroup.getGroupAdmin().gethairColor()+";"+
                        studyGroup.getGroupAdmin().getNationality()+";";
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static StudyGroup parseStudyGroupFromStrings(String[] data) {
        Integer id = Integer.parseInt(data[0]); //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        String name = data[1]; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates = new Coordinates(Double.parseDouble(data[2]), Long.parseLong(data[3])); //Поле не может быть null
        java.time.LocalDate creationDate = LocalDate.parse(data[4]); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        Long studentCount = Long.parseLong(data[5]);
        Long expelledStudents = Long.parseLong(data[6]);
        Long shouldBeExpelled = Long.parseLong(data[7]);
        FormOfEducation formOfEducation = FormOfEducation.values()[Integer.parseInt(data[8])];
        String nameAdmin = data[9];
        float weight = Float.parseFloat(data[10]);
        EyeColor eyeColor = EyeColor.values()[Integer.parseInt(data[11])];
        HairColor hairColor = HairColor.values()[Integer.parseInt(data[12])];
        Country nationality = Country.values()[Integer.parseInt(data[13])];
        Person groupAdmin = new Person(nameAdmin, weight,eyeColor, hairColor, nationality);

        return new StudyGroup(id, name, coordinates,creationDate,studentCount,expelledStudents,shouldBeExpelled,formOfEducation, groupAdmin);
    }
}
