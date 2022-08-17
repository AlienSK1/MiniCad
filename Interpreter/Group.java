package is.Interpreter;

import is.Interpreter.Expression;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.GroupCommand;

import java.awt.geom.Point2D;
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
            elem.add(GraphicObjectHolder.getInstance().getObject(Integer.parseInt(o)));
        }
        GraphicObjectHolder.getInstance().getHistory().handle(new GroupCommand(elem,id));
        return ris;
    }
}
