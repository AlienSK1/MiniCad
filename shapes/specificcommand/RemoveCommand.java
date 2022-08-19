package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.GraphicObject;
import is.shapes.model.Group;
import is.shapes.view.GraphicObjectPanel;

public class RemoveCommand implements Command {
    GraphicObject object;
    private GraphicObjectPanel panel;

    public RemoveCommand(GraphicObject object){
        this.object=object;
        this.panel= GraphicObjectHolder.getInstance().getPanel();
    }

    @Override
    public boolean doIt() {
        if(object instanceof Group){
            GraphicObjectHolder.getInstance().removeGroup((Group) object);
        }
        else {
            GraphicObjectHolder.getInstance().removeObject(object);
        }
        panel.remove(object);
        return true;
    }

    @Override
    public boolean undoIt() {
        GraphicObjectHolder.getInstance().addObject(object);
        panel.add(object);
        return true;
    }
}
