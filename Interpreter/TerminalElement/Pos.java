package is.Interpreter.TerminalElement;

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
}
