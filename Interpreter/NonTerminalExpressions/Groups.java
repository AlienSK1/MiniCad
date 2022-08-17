package is.Interpreter.NonTerminalExpressions;

public class Groups implements  NonTerminalExpression{

    public Groups(){}
    @Override
    public String interpret() {
        return "groups";
    }
}
