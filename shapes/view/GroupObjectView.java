package is.shapes.view;

import is.shapes.model.GraphicObject;
import is.shapes.model.Group;

import java.awt.*;

public class GroupObjectView implements  GraphicObjectView{
    @Override
    public void drawGraphicObject(GraphicObject go, Graphics2D g) {
        Group group= (Group) go;
        for(GraphicObject obj: group.getObjects()){
            switch (obj.getClass().getSimpleName()){
                case "CircleObject":
                    new CircleObjectView().drawGraphicObject(obj,g);
                    break;
                case "RectangleObject":
                    new RectangleObjectView().drawGraphicObject(obj,g);
                    break;
                case "ImageObject":
                    new ImageObjectView().drawGraphicObject(obj,g);
                    break;
                default: new GroupObjectView().drawGraphicObject(obj,g);
            }
        }
    }
}
