package model;

import java.time.LocalDate;
import java.util.Objects;

public class StudyGroup implements Comparable<StudyGroup> {
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0
    private Long expelledStudents; //Значение поля должно быть больше 0, Поле не может быть null
    private Long shouldBeExpelled; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null
    public StudyGroup(Integer id, String name, Coordinates coordinates,java.time.LocalDate creationDate, Long studentsCount,Long expelledStudents,Long shouldBeExpelled,FormOfEducation formOfEducation,Person groupAdmin){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.shouldBeExpelled = shouldBeExpelled;
        this.formOfEducation = formOfEducation;
        this.groupAdmin = groupAdmin;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public Long getExpelledStudents() {
        return expelledStudents;
    }

    public Long getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }
    @Override
    public String toString(){
        return "StudyGroup{"+
                "id="+id+
                ", name="+name+
                ", coordinates="+coordinates+
                ", creationDate="+creationDate+
                ", studentCount="+studentsCount+
                ", expelledStudents"+expelledStudents+
                ", shouldBeExpelled"+shouldBeExpelled+
                ", formOfEducation"+formOfEducation+
                ", groupAdmin"+groupAdmin+
                "}";
    }
    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StudyGroup group = (StudyGroup) object;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name) && Objects.equals(coordinates, group.coordinates) && Objects.equals(creationDate, group.creationDate) && Objects.equals(studentsCount, group.studentsCount) && Objects.equals(expelledStudents, group.expelledStudents) && Objects.equals(shouldBeExpelled, group.shouldBeExpelled) && Objects.equals(formOfEducation, group.formOfEducation) && Objects.equals(groupAdmin, group.groupAdmin);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id, name, coordinates, creationDate, studentsCount, expelledStudents, shouldBeExpelled, formOfEducation, groupAdmin);
    }

    @Override
    public int compareTo(StudyGroup studyGroup) {
        return 0;
    }
}
