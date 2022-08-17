package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.GraphicObject;
import is.shapes.model.Group;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class GroupCommand implements Command {
    private List<GraphicObject> groupElements;
    private int id;
    private Group addedGroup;

    public GroupCommand(List<GraphicObject> groupElements, int id){
        this.groupElements=new LinkedList<>();
        for(GraphicObject o : groupElements){
            this.groupElements.add(o);
        }
        this.id=id;
    }
    @Override
    public boolean doIt() {
        for(GraphicObject o : groupElements){
            if(o instanceof Group){
                GraphicObjectHolder.getInstance().removeGroup((Group) o);
            }
            else{
                GraphicObjectHolder.getInstance().removeObject(o);
            }
        }
        addedGroup=new Group(id,new Point2D.Double(0,0),groupElements);
        GraphicObjectHolder.getInstance().addObject(addedGroup);
        return true;
    }

    @Override
    public boolean undoIt() {
        GraphicObjectHolder.getInstance().removeObject(addedGroup);
        for(GraphicObject o: groupElements){
            GraphicObjectHolder.getInstance().addObject(o);
        }
        return true;
    }
}
