package model;

import java.util.Objects;

/**
 * Person data class.
 */
public class Person implements Comparable<Person>{
    private String name; //Поле не может быть null, Строка не может быть пустой
    private float weight; //Поле не может быть null, Значение поля должно быть больше 0
    private EyeColor eyecolor; //Поле не может быть null
    private HairColor haircolor; //Поле не может быть null
    private Country nationality; //Поле не может быть null

    /**
     * @param name - name of the person
     * @param weight - weight of the person
     * @param eyecolor - color of eyes
     * @param haircolor - color of eyes
     * @param nationality - nationality of the person
     */
    public Person(String name, float weight, EyeColor eyecolor, HairColor haircolor, Country nationality) {
        this.name = name;
        this.weight = weight;
        this.eyecolor = eyecolor;
        this.haircolor = haircolor;
        this.nationality = nationality;
    }
    public String getName(){
        return name;
    }
    public float getWeight(){
        return weight;
    }
    public EyeColor getEyecolor(){
        return eyecolor;
    }
    public HairColor gethairColor(){
        return haircolor;
    }
    public  Country getNationality(){
        return nationality;
    }
    @Override
    public String toString(){
        return "{"+
                "name="+name+
                ", weight"+weight+
                ", eyecolor="+eyecolor+
                ", haircolor"+haircolor+
                ", nationality"+nationality+
                "}";
    }
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(name, person.name) && Float.compare(weight, person.weight) ==0  && Objects.equals(eyecolor, person.eyecolor) && Objects.equals(haircolor, person.haircolor) && Objects.equals(nationality, person.nationality);
    }
    @Override
    public int compareTo(Person person) {
        return (int) (this.getWeight()-person.getWeight());
    }
}
