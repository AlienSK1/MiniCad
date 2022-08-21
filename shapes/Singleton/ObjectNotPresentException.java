package is.shapes.Singleton;

public class ObjectNotPresentException extends Exception{
    public ObjectNotPresentException() {}

    public ObjectNotPresentException(String msg) {
        super(msg);
    }
}
