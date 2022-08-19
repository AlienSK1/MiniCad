package is.Interpreter.typeConstraint;

public class Img implements TypeConstr {
    private String path;

    public Img(String path){
        this.path=path;
    }

    @Override
    public String interpret() {
        return this.path;
    }
}
