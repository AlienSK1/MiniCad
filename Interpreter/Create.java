package is.Interpreter;

import is.Interpreter.typeConstraint.Circle;
import is.Interpreter.typeConstraint.Img;
import is.Interpreter.typeConstraint.Rectangle;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.TestGraphics;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.specificcommand.NewObjectCmd;

import javax.swing.*;
import java.awt.geom.Point2D;

public class Create implements Expression{
    private Expression typeConstr;
    private Expression pos;

    public Create(Expression typeConstr, Expression pos){
        this.pos=pos;
        this.typeConstr=typeConstr;
    }

    @Override
    public String interpret() {
        int id =GraphicObjectHolder.getInstance().nextId();
        GraphicObject o=null;
        if(typeConstr instanceof Circle){
            double radius= Double.parseDouble(typeConstr.interpret());
            String[] positionValues= pos.interpret().split(",");
            Point2D position=  new Point2D.Double(Double.parseDouble(positionValues[0]), Double.parseDouble(positionValues[1]));
            o = new CircleObject(position,radius, id);
        }
        else if(typeConstr instanceof Rectangle){
            String[] positionValues= pos.interpret().split(",");
            Point2D position=  new Point2D.Double(Double.parseDouble(positionValues[0]), Double.parseDouble(positionValues[1]));
            String[] dimension= typeConstr.interpret().split(",");
            o = new RectangleObject(position,Double.parseDouble(dimension[0]),Double.parseDouble(dimension[1]),id);
        }
        else if (typeConstr instanceof Img){
            String[] positionValues= pos.interpret().split(",");
            Point2D position=  new Point2D.Double(Double.parseDouble(positionValues[0]), Double.parseDouble(positionValues[1]));
            ImageIcon image = new ImageIcon(TestGraphics.class.getResource(typeConstr.interpret()));
            o= new ImageObject(image, position, id);
        }
        GraphicObjectHolder.getInstance().getHistory().handle(new NewObjectCmd(GraphicObjectHolder.getInstance().getPanel(),o));
        return String.format("%d",id);
    }
}
