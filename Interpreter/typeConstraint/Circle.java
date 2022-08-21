package is.Interpreter.typeConstraint;

import is.Interpreter.Expression;

public class Circle implements TypeConstr {
    private Expression radius;

    public Circle(Expression radius){
        this.radius=radius;
    }
    @Override
    public String interpret() {
        return String.format("%s",radius.interpret());
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Circle)) return false;
        Circle c = (Circle) e;
        return this.radius.equals(c.radius);
    }

}
