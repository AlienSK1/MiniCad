package is.Interpreter;

import is.command.HistoryCommandHandler;

public class Cmd implements Expression{
    private Expression command;

    public Cmd(Expression command){
        this.command=command;
    }

    @Override
    public String interpret() {
        return this.command.interpret();
    }
}
