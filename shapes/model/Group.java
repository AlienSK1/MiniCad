package is.shapes.model;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Group extends AbstractGraphicObject{
    private Point2D position;
    private double factor = 1.0;
    private List<GraphicObject> groupElement;

    public Group(int id, Point2D position,List<GraphicObject> groupElement){
        super(id);
        this.position = new Point2D.Double(position.getX(), position.getY());
        this.groupElement=groupElement;
    }

    @Override
    public void moveTo(Point2D p) {
        Point2D oldPos = position;
        position = p;
        double diffX= p.getX()-oldPos.getX();
        double diffY=p.getY()-oldPos.getY();
        for(GraphicObject o: groupElement){
            o.moveTo(o.getPosition().getX()+diffX, o.getPosition().getY()+diffY);
        }
        notifyListeners(new GraphicEvent(this));
    }
    @Override
    public Point2D getPosition() {
        return new Point2D.Double(position.getX(), position.getY());
    }
    @Override
    public Dimension2D getDimension() {
        Dimension dim = new Dimension();
        int width=0;
        int height=0;
        for(GraphicObject g: groupElement){
            height+=g.getDimension().getHeight();
            width+=g.getDimension().getWidth();
        }
        dim.setSize(width,height);
        return dim;
    }
    @Override
    public void scale(double factor) {
        if (factor <= 0)
            throw new IllegalArgumentException();
        this.factor *= factor;
        for (GraphicObject o : groupElement){
            o.scale(factor);
        }
        notifyListeners(new GraphicEvent(this));
    }

    @Override
    public boolean contains(Point2D p) {
        for(GraphicObject o : groupElement){
            if(o.contains(p)) return  true;
        }
        return false;
    }

    public List<GraphicObject> getObjects(){
        LinkedList<GraphicObject> ris= new LinkedList<>();
        for(GraphicObject o: groupElement){
            ris.add(o);
        }
        return ris;
    }

    @Override
    public String getType() {
        return "Group";
    }

    @Override
    public String objectProperties(){
        String ris="{\n";
        ListIterator<GraphicObject> it = groupElement.listIterator();
        GraphicObject o = it.next();
        while(it.hasNext()){
            ris+=o.objectProperties()+",\n";
            o=it.next();
        }
        ris+=o.objectProperties()+"\n}";
        return ris;
    }

    @Override
    public double perimeter(){
        double ris=0;
        for(GraphicObject o : groupElement){
            ris+=o.perimeter();
        }
        return ris;
    }

    @Override
    public double area(){
        double ris=0;
        for(GraphicObject o: groupElement){
            ris+=o.area();
        }
        return ris;
    }


    @Override
    public boolean equals(Object o){
        if(o==null) return false;
        if(o==this) return true;
        if(!(o instanceof Group)) return false;
        Group g = (Group) o;
        return this.groupElement.equals(g.groupElement);
    }
}
