package is.Interpreter.NonTerminalExpressions;

import is.Interpreter.Expression;

public class Pos implements Expression {
    private Expression rightvalue;
    private Expression leftvalue;

    public Pos(Expression rightvalue, Expression leftvalue){
        this.leftvalue= leftvalue;
        this.rightvalue=rightvalue;
    }
    @Override
    public String interpret() {
        return String.format("%s,%s", rightvalue.interpret(), leftvalue.interpret());
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Pos)) return false;
        Pos p = (Pos) e;
        return this.rightvalue.equals(p.rightvalue) && this.leftvalue.equals(p.leftvalue);
    }

}
