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
}
