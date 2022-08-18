package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class NewObjectCmd implements Command {

	private GraphicObjectPanel panel;
	private GraphicObject go;

	public NewObjectCmd(GraphicObjectPanel panel, GraphicObject go) {
		this.panel = panel;
		this.go = go;
	}

	@Override
	public boolean doIt() {
		GraphicObjectHolder.getInstance().addObject(go);
		go.moveTo(go.getPosition().getX(),go.getPosition().getY());
		panel.add(go);
		return true;
	}

	@Override
	public boolean undoIt() {
		panel.remove(go);
		GraphicObjectHolder.getInstance().removeObject(go);
		return true;
	}

}
