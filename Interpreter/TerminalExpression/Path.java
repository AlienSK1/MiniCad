package is.Interpreter.TerminalExpression;

public class Path implements TerminalExpression {
    private String path;

    public Path(String path){
        this.path=path;
    }
    @Override
    public String interpret() {
        return this.path;
    }
}
