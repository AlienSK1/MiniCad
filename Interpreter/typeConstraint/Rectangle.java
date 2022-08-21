package is.Interpreter.typeConstraint;

import is.Interpreter.Expression;

public class Rectangle implements TypeConstr {
    private Expression dim;

    public Rectangle(Expression dim){
        this.dim=dim;
    }

    @Override
    public String interpret() {
        return dim.interpret();
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Rectangle)) return false;
        Rectangle r = (Rectangle) e;
        return this.dim.equals(r.dim);
    }
}
