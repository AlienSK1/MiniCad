package is.Interpreter;

import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.Singleton.ObjectNotPresentException;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.GroupCommand;

import java.util.LinkedList;
import java.util.List;

public class Group implements Expression {
    private Expression idList;

    public Group(Expression idList){
        this.idList=idList;
    }
    @Override
    public String interpret() {
        String ris=null;
        String[] objs= idList.interpret().split(",");
        int id = GraphicObjectHolder.getInstance().nextId();
        List<GraphicObject> elem= new LinkedList<>();
        for(String o : objs){
            try {
                elem.add(GraphicObjectHolder.getInstance().getObject(Integer.parseInt(o.trim())));
            } catch (ObjectNotPresentException e) {
                throw new RuntimeException(e);
            }
        }
        GraphicObjectHolder.getInstance().getHistory().handle(new GroupCommand(elem,id));
        return ris;
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Move)) return false;
        Group g = (Group) e;
        return this.idList.equals(g.idList);
    }
}
