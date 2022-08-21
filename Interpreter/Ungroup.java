package is.Interpreter;

import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.Singleton.ObjectNotPresentException;
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
        Group g = null;
        try {
            g = (Group) GraphicObjectHolder.getInstance().getObject(Integer.parseInt(id.interpret()));
        } catch (ObjectNotPresentException e) {
            throw new RuntimeException(e);
        }
        GraphicObjectHolder.getInstance().getHistory().handle(new UnGroupCommand(g));
        return null;
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Ungroup)) return false;
        Ungroup ug= (Ungroup) e;
        return this.id.equals(ug.id);
    }
}
