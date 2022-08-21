package is.Interpreter.NonTerminalExpressions;

public class All implements NonTerminalExpression{

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
