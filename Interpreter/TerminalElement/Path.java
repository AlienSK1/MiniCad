package is.Interpreter.TerminalElement;

public class Path implements TerminalElement{
    private String path;

    public Path(String path){
        this.path=path;
    }
    @Override
    public String interpret() {
        return this.path;
    }
}
