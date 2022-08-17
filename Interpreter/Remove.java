package is.Interpreter;

import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.RemoveCommand;

public class Remove implements Expression{
    private Expression id;

    public Remove(Expression id){
        this.id=id;
    }

    @Override
    public String interpret() {
        String ris=null;
        int objectId= Integer.parseInt(id.interpret());
        GraphicObject o = GraphicObjectHolder.getInstance().getObject(objectId);
        GraphicObjectHolder.getInstance().getHistory().handle(new RemoveCommand(o));
        return ris;
    }
}
