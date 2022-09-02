package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.Singleton.ObjectNotPresentException;
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
        try {
            GraphicObjectHolder.getInstance().removeGroup(group);
        } catch (ObjectNotPresentException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean undoIt() {
        List<GraphicObject> elements= group.getObjects();
        for(GraphicObject o:elements){
            try {
                GraphicObjectHolder.getInstance().removeObject(o);
            } catch (ObjectNotPresentException e) {
                throw new RuntimeException(e);
            }
        }

        GraphicObjectHolder.getInstance().addObject(group);
        return true;
    }
}
