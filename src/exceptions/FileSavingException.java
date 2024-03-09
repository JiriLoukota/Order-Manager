package orderManager.exceptions;

public class FileSavingException  extends Exception{
    public FileSavingException(String message){
        super("Could not save data to file: " + message);
    }
}
