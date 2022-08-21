package is.Interpreter;

import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.ZoomCommand;

public class Scale implements Expression{
    private Expression id;
    private Expression posfloat;

    public Scale(Expression id , Expression posfloat){
        this.id=id;
        this.posfloat=posfloat;
    }

    @Override
    public String interpret() {
        String ris=null;
        GraphicObject o = GraphicObjectHolder.getInstance().getObject(Integer.parseInt(id.interpret()));
        GraphicObjectHolder.getInstance().getHistory().handle(new ZoomCommand(o,Double.parseDouble(posfloat.interpret())));
        return ris;
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Scale)) return false;
        Scale s = (Scale) e;
        return this.id.equals(s.id) && this.posfloat.equals(s.posfloat);
    }
}
