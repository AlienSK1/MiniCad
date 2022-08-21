package is.Interpreter;

import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.MoveCommand;

import java.awt.geom.Point2D;

public class Move implements Expression{
    private boolean isOffset;
    private Expression id;
    private Expression pos;

    public Move(boolean isOffset, Expression id, Expression pos){
        this.isOffset=isOffset;
        this.id=id;
        this.pos=pos;
    }

    @Override
    public String interpret() {
        String ris=null;
        int objid= Integer.parseInt(id.interpret());
        GraphicObject o= GraphicObjectHolder.getInstance().getObject(objid);
        String[] positionValues= pos.interpret().split(",");
        Point2D position;
        if(isOffset){
            position= new Point2D.Double(o.getPosition().getX()+Double.parseDouble(positionValues[0]), o.getPosition().getY()+Double.parseDouble(positionValues[1]));
        }
        else{
            position = new Point2D.Double(Double.parseDouble(positionValues[0]), Double.parseDouble(positionValues[1]));
        }
        GraphicObjectHolder.getInstance().getHistory().handle(new MoveCommand(o,position));
        return ris;
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof Move)) return false;
        Move m = (Move) e;
        return this.isOffset==m.isOffset && this.pos.equals(this.pos) && this.id.equals(m.id);
    }


}
