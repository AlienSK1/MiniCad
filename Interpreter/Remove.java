package is.Interpreter;

public class Remove implements Expression{
    private Expression id;

    public Remove(Expression id){
        this.id=id;
    }

    @Override
    public String interpret() {
        String ris=null;
        int objectId= Integer.parseInt(id.interpret());
        //todo
        return ris;
    }
}
