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

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Img)) return false;
        Img i = (Img) e;
        return this.path.equals(i.path);
    }
}
