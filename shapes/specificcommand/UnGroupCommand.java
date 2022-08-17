package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.GraphicObject;
import is.shapes.model.Group;

import java.util.List;

public class UnGroupCommand implements Command {
    private Group group;

    public UnGroupCommand(Group group){
        this.group=group;
    }
    @Override
    public boolean doIt() {
        GraphicObjectHolder.getInstance().removeObject(group);
        return true;
    }

    @Override
    public boolean undoIt() {
        List<GraphicObject> elements= group.getObjects();
        for(GraphicObject o:elements){
            if(o instanceof Group){
                GraphicObjectHolder.getInstance().removeGroup((Group) o);
            }
            else {
                GraphicObjectHolder.getInstance().removeObject(o);
            }
        }
        GraphicObjectHolder.getInstance().addObject(group);
        return true;
    }
}
