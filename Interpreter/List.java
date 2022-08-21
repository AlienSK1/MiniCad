package is.Interpreter;

import is.Interpreter.NonTerminalExpressions.All;
import is.Interpreter.NonTerminalExpressions.Groups;
import is.Interpreter.TerminalElement.Id;
import is.Interpreter.typeConstraint.TypeConstr;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.GraphicObject;

import java.util.Iterator;

public class List implements Expression{
    private Expression subj;

    public List(Expression subj){
        this.subj=subj;
    }

    @Override
    public String interpret() {
        String ris=null;
        if(subj instanceof Id){
            ris= GraphicObjectHolder.getInstance().getObject(Integer.parseInt(subj.interpret())).objectProperties();
        } else if (subj instanceof TypeConstr) {
            java.util.List<GraphicObject> objs=GraphicObjectHolder.getInstance().getAllByType(subj.getClass().getSimpleName());
            if(!objs.isEmpty()){
                StringBuilder sb = new StringBuilder();
                Iterator<GraphicObject> it = objs.listIterator();
                GraphicObject o = it.next();
                while(it.hasNext()){
                    sb.append(o.getId());
                    sb.append(",");
                    o=it.next();
                }
                sb.append(o.getId());
                ris=sb.toString();
            }
            else{
                ris="Non ci sono oggetti di questo tipo";
            }

        }
        else if(subj instanceof All){
            java.util.List<GraphicObject> objs=GraphicObjectHolder.getInstance().getAllObjects();
            objs.addAll(GraphicObjectHolder.getInstance().getAllGroups());
            if(!objs.isEmpty()){
                StringBuilder sb = new StringBuilder();
                Iterator<GraphicObject> it = objs.listIterator();
                GraphicObject o = it.next();
                while(it.hasNext()){
                    sb.append(o.getId());
                    sb.append(",");
                    o=it.next();
                }
                sb.append(o.getId());
                ris=sb.toString();
            }
            else{
                ris="Non ci sono oggetti";
            }

        } else if ( subj instanceof Groups) {
            java.util.List<is.shapes.model.Group> objs=GraphicObjectHolder.getInstance().getAllGroups();
            if(!objs.isEmpty()){
                StringBuilder sb = new StringBuilder();
                Iterator<is.shapes.model.Group> it = objs.listIterator();
                is.shapes.model.Group g = it.next();
                while(it.hasNext()){
                    sb.append(g.getId());
                    sb.append(",");
                    g=it.next();
                }
                sb.append(g.getId());
                ris=sb.toString();
            }
            else{
                ris= "Non ci sono gruppi";
            }
        }
        return ris;
    }

    @Override
    public boolean equals(Object e) {

        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof List)) return false;
        List l = (List) e;
        if(this.subj instanceof Id){
            return this.subj.equals(l.subj);
        }
        return this.subj.getClass()==l.subj.getClass();
    }
}
