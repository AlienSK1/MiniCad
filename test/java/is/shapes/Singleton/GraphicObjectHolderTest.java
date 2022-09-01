package is.shapes.Singleton;

import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.Group;
import is.shapes.model.RectangleObject;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//withe box coverage o black box
class GraphicObjectHolderTest {
    GraphicObjectHolder holder;
    @BeforeEach
    public void init(){
        holder= GraphicObjectHolder.getInstance();
        holder.addObject(new CircleObject(new Point2D.Double(2.0,2.0),20,holder.nextId()));
        List<GraphicObject> gElem= new LinkedList<>();
        gElem.add(new CircleObject(new Point2D.Double(20,20),10, holder.nextId()));
        holder.addObject(new Group(holder.nextId(), new Point2D.Double(20,20),gElem));
    }

    @Test
    public void removeObjectSuccess(){
        CircleObject c = new CircleObject(new Point2D.Double(2.0,2.0),20,0);
        assertDoesNotThrow(()->holder.removeObject(c));
    }

    @Test
    public void removeObjectFail(){
        RectangleObject r = new RectangleObject(new Point2D.Double(20,20),10,30,holder.nextId());
        assertThrows(ObjectNotPresentException.class,()->holder.removeObject(r));
    }

    @Test
    public void removeGroupWork(){
        List<GraphicObject> gElem= new LinkedList<>();
        gElem.add(new CircleObject(new Point2D.Double(20,20),10, holder.nextId()));
        Group g = new Group(holder.nextId(), new Point2D.Double(20,20),gElem);
        assertDoesNotThrow(()->holder.removeGroup(g));
    }

    @Test
    public void removeGroupFail(){
        List<GraphicObject> gElem= new LinkedList<>();
        gElem.add(new CircleObject(new Point2D.Double(10,20),4, holder.nextId()));
        Group g = new Group(holder.nextId(), new Point2D.Double(20,20),gElem);
        assertThrows(ObjectNotPresentException.class,()->holder.removeGroup(g));
    }

    @Test
    public void addObjectWork(){
        RectangleObject r = new RectangleObject(new Point2D.Double(200,200),20,20,holder.nextId());
        assertEquals(holder.nextId()-1, holder.addObject(r));
    }

    @Test
    public void getObjectByIdFail(){
        int id= 3;
        assertThrows(ObjectNotPresentException.class,()-> holder.getObject(id));
    }

    @Test
    public void getAllByTypeWork(){
        String type="Image";
        assertDoesNotThrow(()->holder.getAllByType(type));
    }

    @Test
    public void getAllByTypeFail(){
        String type="Lista";
        assertThrows(RuntimeException.class,()-> holder.getAllByType(type));
    }
}