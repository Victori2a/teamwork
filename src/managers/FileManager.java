package managers;

import model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class FileManager {
    public static LinkedList<StudyGroup> readCollectionFromCSV(String filename, ConsoleHandler consoleHandler) {
        LinkedList<StudyGroup> collection = new LinkedList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(";");
            scanner.nextLine();
            while (scanner.hasNext()){
                System.out.print(scanner.next() + " ");
            }
            scanner.close();
//            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            String firstLine = bufferedReader.readLine();
//            if (firstLine == null) throw new NullPointerException("Этот файл пустой!");
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] values = line.split(";");
//                System.out.println(Arrays.toString(values));
//                collection.add(parseStudyGroupFromStrings(values));
//                System.out.println("Happy");
//            }
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
    private static StudyGroup parseStudyGroupFromStrings(String[] data) {
        Integer id = Integer.parseInt(data[0]); //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        String name = data[1]; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates = new Coordinates(Double.parseDouble(data[2]), Long.parseLong(data[3])); //Поле не может быть null
        java.time.LocalDate creationDate = LocalDate.of(2014,2,18); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        Long studentCount = Long.parseLong(data[4]);
        System.out.println(id+" "+" "+coordinates+" "+creationDate+" "+studentCount);
        Long expelledStudents = Long.parseLong(data[5]);
        Long shouldBeExpelled = Long.parseLong(data[6]);
        FormOfEducation formOfEducation = FormOfEducation.values()[Integer.parseInt(data[7])];
        String nameAdmin = data[8];
        float weight = Float.parseFloat(data[9]);
        EyeColor eyeColor = EyeColor.values()[Integer.parseInt(data[10])];
        HairColor hairColor = HairColor.values()[Integer.parseInt(data[11])];
        Country nationality = Country.values()[Integer.parseInt(data[12])];
        Person groupAdmin = new Person(nameAdmin, weight,eyeColor, hairColor, nationality);
        return new StudyGroup(id, name, coordinates,creationDate,studentCount,expelledStudents,shouldBeExpelled,formOfEducation, groupAdmin);
    }
}
