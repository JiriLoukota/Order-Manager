package orderManager.exceptions;

public class InvalidDataException  extends Exception{
    public InvalidDataException(String message) {
        super("Invalid data inputted into: " + message);
    }
}
