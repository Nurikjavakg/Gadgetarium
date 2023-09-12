package peaksoft.exception;

public class InvalidNameException extends NullPointerException{
    public InvalidNameException() {
    }

    public InvalidNameException(String s) {
        super(s);
    }
}
