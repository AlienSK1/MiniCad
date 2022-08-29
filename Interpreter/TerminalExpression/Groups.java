package is.Interpreter.TerminalExpression;

import is.Interpreter.NonTerminalExpressions.NonTerminalExpression;

public class Groups implements TerminalExpression {
    public Groups(){}
    @Override
    public String interpret() {
        return "groups";
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        return e instanceof Groups;
    }
}
