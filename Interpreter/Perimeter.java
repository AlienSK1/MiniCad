package is.Interpreter;

import is.Interpreter.TerminalExpression.All;
import is.Interpreter.TerminalExpression.Id;
import is.Interpreter.typeConstraint.TypeConstr;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.Singleton.ObjectNotPresentException;
import is.shapes.model.GraphicObject;

import java.util.List;

public class Perimeter implements Expression{
    private Expression subj;

    public Perimeter(Expression subj){
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
            ris=o.perimeter();
        }
        else if( subj instanceof TypeConstr){
            List<GraphicObject> objs= GraphicObjectHolder.getInstance().getAllByType(subj.getClass().getSimpleName());
            for(GraphicObject o: objs){
                ris+=o.perimeter();
            }
        }
        else if(subj instanceof All){
            List<GraphicObject> objs= GraphicObjectHolder.getInstance().getAllObjects();
            for(GraphicObject o: objs){
                ris+=o.perimeter();
            }
        }
        return String.format("%.2f",ris);
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Perimeter)) return false;
        Perimeter p = (Perimeter) e;
        return this.subj.equals(p.subj);
    }

}
