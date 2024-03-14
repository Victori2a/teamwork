package exceptions;


/**
 * Is throwing when the file name is incorrect.
 */
public class IncorrectFilenameException extends Exception{
    public IncorrectFilenameException(String message){
        super(message);
    }
}
