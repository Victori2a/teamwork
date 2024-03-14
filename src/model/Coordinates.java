package model;

import java.util.Objects;

/**
 * Coordinates data class
 */
public class Coordinates {
    private double x; //Значение поля должно быть больше -871, Поле не может быть null
    private Long y; //Поле не может быть null

    /**
     * @param x - X coordinate
     * @param y - Y coordinate
     */
    public  Coordinates(double x, Long y){
        this .x = x;
        this. y = y;
    }

    /**
     * @return X coordinate
     */
    public double getX(){
        return x;
    }

    /**
     * @return Y coordinate
     */
    public Long getY(){
        return y;
    }
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Coordinates coordinates = (Coordinates) object;
        return x == coordinates.x && Objects.equals(y, coordinates.y);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
