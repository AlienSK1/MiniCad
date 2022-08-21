package is.Interpreter.NonTerminalExpressions;

public class Groups implements  NonTerminalExpression{
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
