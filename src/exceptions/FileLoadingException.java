package orderManager.exceptions;

public class FileLoadingException extends Exception{
    public FileLoadingException(String message){
        super("Could not load data from file: " + message);
    }
}
