package is.shapes.Singleton;

public class WrongObjectClassException extends RuntimeException{
    public WrongObjectClassException(String msg){
        super(msg);
    }
    public WrongObjectClassException(){}
}
