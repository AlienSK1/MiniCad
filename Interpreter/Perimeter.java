package is.Interpreter;

import is.Interpreter.NonTerminalExpressions.All;
import is.Interpreter.TerminalElement.Id;
import is.Interpreter.typeConstraint.TypeConstr;
import is.shapes.Singleton.GraphicObjectHolder;
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
            GraphicObject o = GraphicObjectHolder.getInstance().getObject(Integer.parseInt(subj.interpret()));
            ris=o.perimeter();
        }
        else if( subj instanceof TypeConstr){
            List<GraphicObject> objs= GraphicObjectHolder.getInstance().getAllByType(subj.getClass().getTypeName());
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
        return String.format("%lf",ris);
    }
}
