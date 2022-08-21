package is.Interpreter;

import is.Interpreter.NonTerminalExpressions.All;
import is.Interpreter.TerminalElement.Id;
import is.Interpreter.typeConstraint.TypeConstr;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.Singleton.ObjectNotPresentException;
import is.shapes.model.GraphicObject;

import java.util.List;

public class Area implements Expression{
    private Expression subj;

    public Area(Expression subj){
        this.subj=subj;
    }

    @Override
    public String interpret() {
        double ris=0;
        if(subj instanceof Id){
            GraphicObject o = null;
            try {
                o = GraphicObjectHolder.getInstance().getObject(Integer.parseInt(subj.interpret()));
            } catch (ObjectNotPresentException e) {
                throw new RuntimeException(e);
            }
            ris=o.area();
        }
        else if( subj instanceof TypeConstr){
            java.util.List<GraphicObject> objs= GraphicObjectHolder.getInstance().getAllByType(subj.getClass().getSimpleName());
            for(GraphicObject o: objs){
                ris+=o.area();
            }
        }
        else if(subj instanceof All){
            List<GraphicObject> objs= GraphicObjectHolder.getInstance().getAllObjects();
            for(GraphicObject o: objs){
                ris+=o.area();
            }
        }
        return String.format("%.2f",ris);
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Area)) return false;
        Area a = (Area) e;
        return this.subj.equals(a.subj);
    }
}
