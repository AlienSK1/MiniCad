package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.Singleton.ObjectNotPresentException;
import is.shapes.model.GraphicObject;
import is.shapes.model.Group;
import is.shapes.view.GraphicObjectPanel;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class GroupCommand implements Command {
    private List<GraphicObject> groupElements;
    private int id;
    private Group addedGroup;

    private GraphicObjectPanel panel;

    public GroupCommand(List<GraphicObject> groupElements, int id){
        this.groupElements=new LinkedList<>();
        for(GraphicObject o : groupElements){
            this.groupElements.add(o);
        }
        this.id=id;
        this.panel= GraphicObjectHolder.getInstance().getPanel();
    }
    @Override
    public boolean doIt() {
        for(GraphicObject o : groupElements){
            try {
                GraphicObjectHolder.getInstance().removeObject(o);
            } catch (ObjectNotPresentException e) {
                throw new RuntimeException(e);
            }
            panel.remove(o);
        }
        addedGroup=new Group(id,new Point2D.Double(0,0),groupElements);
        GraphicObjectHolder.getInstance().addObject(addedGroup);
        panel.add(addedGroup);
        return true;
    }

    @Override
    public boolean undoIt() {
        try {
            GraphicObjectHolder.getInstance().removeObject(addedGroup);
        } catch (ObjectNotPresentException e) {
            throw new RuntimeException(e);
        }
        panel.remove(addedGroup);
        for(GraphicObject o: groupElements){
            GraphicObjectHolder.getInstance().addObject(o);
            panel.add(o);
        }
        return true;
    }
}
