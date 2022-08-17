package is.Interpreter.typeConstraint;

import is.Interpreter.Expression;
import is.Interpreter.typeConstraint.TypeConstr;

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
