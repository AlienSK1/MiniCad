package is.Interpreter.typeConstraint;

import is.Interpreter.Expression;
import is.Interpreter.typeConstraint.TypeConstr;

public class Circle implements TypeConstr {
    private Expression radius;

    public Circle(Expression radius){
        this.radius=radius;
    }
    @Override
    public String interpret() {
        return String.format("%s",radius.interpret());
    }
}
