package is.Interpreter.TerminalElement;

public class Floating implements TerminalElement{
    private double number;
    public Floating(String floating){
        this.number= Double.parseDouble(floating);
    }

    @Override
    public String interpret() {
        return number+"";
    }
}
