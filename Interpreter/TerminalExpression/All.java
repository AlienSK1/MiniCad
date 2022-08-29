package is.Interpreter.TerminalExpression;

import is.Interpreter.NonTerminalExpressions.NonTerminalExpression;

public class All implements TerminalExpression {

    public All(){}

    @Override
    public String interpret() {
        return "all";
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        return e instanceof All;
    }
}
