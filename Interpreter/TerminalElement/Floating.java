package is.Interpreter.TerminalElement;

import is.Interpreter.typeConstraint.Circle;

public class Floating implements TerminalElement{
    private double number;
    public Floating(String floating){
        this.number= Double.parseDouble(floating);
    }

    @Override
    public String interpret() {
        return number+"";
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Floating)) return false;
        Floating f = (Floating) e;
        return this.number==f.number;
    }
}
