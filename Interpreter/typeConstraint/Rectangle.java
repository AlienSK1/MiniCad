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
}
