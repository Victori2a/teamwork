package exceptions;

/**
 * Is throwing when the entered command is missing.
 */
public class CommandNotExistsException extends Exception {
    public CommandNotExistsException(String message){
        super(message);
    }
}
