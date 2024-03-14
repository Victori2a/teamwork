package exceptions;

/**
 * Is throwing when an empty string is entered.
 */
public class NullUserRequestException extends Exception{
    public NullUserRequestException (String message){
        super(message);
    }
}
