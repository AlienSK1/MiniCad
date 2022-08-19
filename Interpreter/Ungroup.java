package is.Interpreter;

import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.Group;
import is.shapes.specificcommand.UnGroupCommand;

public class Ungroup implements Expression{
    private Expression id;

    public Ungroup(Expression id){
        this.id=id;
    }

    @Override
    public String interpret() {
        String ris=null;
        Group g =(Group) GraphicObjectHolder.getInstance().getObject(Integer.parseInt(id.interpret()));
        GraphicObjectHolder.getInstance().getHistory().handle(new UnGroupCommand(g));
        return null;
    }
}
