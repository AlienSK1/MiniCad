package is.Interpreter.TerminalElement;

import is.Interpreter.typeConstraint.Circle;

public class Id implements TerminalElement{
    private int id;

    public Id(String id){
        this.id=Integer.parseInt(id);
    }

    public String toString(){
        return id+"";
    }
    @Override
    public String interpret() {
        return String.format("%d",this.id);
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Circle)) return false;
        Id i = (Id) e;
        return this.id==i.id;
    }
}
