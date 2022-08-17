package is.Interpreter.NonTerminalExpressions;

public class All implements NonTerminalExpression{

    public All(){}

    @Override
    public String interpret() {
        return "all";
    }
}
