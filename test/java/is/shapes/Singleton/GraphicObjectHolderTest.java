package is.shapes.Singleton;

import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.Group;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphicObjectHolderTest {
    static List<GraphicObject> objs;

    @BeforeAll
    public static void init(){
        objs=new LinkedList<>();
        objs.add(new CircleObject(new Point2D.Double(200,200),20,0));
        objs.add(new Group(1, new Point2D.Double(300, 300), new LinkedList<>()));
        objs.add(new Group(2, new Point2D.Double(123, 100), new LinkedList<>()));
    }

    @RepeatedTest(3)
    public void testAddAndRemove(RepetitionInfo info){
        if(info.getCurrentRepetition()-1<2)
        assertAll(
                ()->assertEquals(GraphicObjectHolder.getInstance().nextId(), GraphicObjectHolder.getInstance().addObject(objs.get(info.getCurrentRepetition()-1))),
                ()->assertDoesNotThrow(()->GraphicObjectHolder.getInstance().removeObject(objs.get(info.getCurrentRepetition()-1)))
        );
        else
            assertThrows(ObjectNotPresentException.class,()->GraphicObjectHolder.getInstance().removeObject(objs.get(info.getCurrentRepetition()-1)));
    }

    @Nested
    class TestGroupOperation{
        static List<GraphicObject> objs;

        @BeforeAll
        public static void init(){
            objs=new LinkedList<>();
            objs.add(new Group(0, new Point2D.Double(300, 300), new LinkedList<>()));
            objs.add(new Group(1, new Point2D.Double(123, 100), new LinkedList<>()));
        }

        @RepeatedTest(2)
        public void testGroupOperation(RepetitionInfo info){
            if(info.getCurrentRepetition()-1<1){
                assertAll(
                        ()->assertEquals(info.getCurrentRepetition()-1,GraphicObjectHolder.getInstance().addObject(objs.get(info.getCurrentRepetition()-1))),
                        ()->assertDoesNotThrow(()->GraphicObjectHolder.getInstance().removeObject(objs.get(info.getCurrentRepetition()-1)))
                );
            }
            else
                assertThrows(ObjectNotPresentException.class, ()->GraphicObjectHolder.getInstance().removeObject(objs.get(info.getCurrentRepetition()-1)));
        }
    }
}