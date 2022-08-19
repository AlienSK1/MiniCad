package is.Interpreter.TerminalElement;

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
}
