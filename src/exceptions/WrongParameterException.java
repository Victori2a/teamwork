package exceptions;

/**
 * Is throwing when the parameter is entered incorrectly.
 */
public class WrongParameterException extends Exception{
    public WrongParameterException(String message){
        super(message);
    }
}
