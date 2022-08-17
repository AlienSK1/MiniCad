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
		double x = 10;
		double y = 10;
		GraphicObjectHolder.getInstance().addObject(go);
		go.moveTo(x, y);
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
