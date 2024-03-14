package exceptions;

/**
 * Is throwing when there is no value for this characteristic.
 */
public class ElementNotFoundException extends Exception{
    public ElementNotFoundException(String message){
        super(message);
    }
}
