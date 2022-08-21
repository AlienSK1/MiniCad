package is.Interpreter;

public class Cmd implements Expression{
    private Expression command;

    public Cmd(Expression command){
        this.command=command;
    }

    @Override
    public String interpret() {
        return this.command.interpret();
    }

    @Override
    public boolean equals(Object e){
        if(!(e instanceof Cmd)) return false;
        if(e==null) return false;
        if(e==this) return true;
        Cmd c= (Cmd) e;
        return this.command.equals(c.command);
    }
}
