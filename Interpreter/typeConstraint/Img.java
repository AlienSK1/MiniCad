package is.Interpreter.typeConstraint;

import is.Interpreter.Expression;

public class Img implements TypeConstr {
    private Expression path;

    public Img(Expression path){
        this.path=path;
    }

    @Override
    public String interpret() {
        return this.path.interpret();
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
