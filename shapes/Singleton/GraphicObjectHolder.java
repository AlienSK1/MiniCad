package is.shapes.Singleton;

import is.command.HistoryCommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.model.Group;
import is.shapes.view.GraphicObjectPanel;

import java.util.LinkedList;
import java.util.List;

public class GraphicObjectHolder {
    private static GraphicObjectHolder instance= null;

    private List<GraphicObject> objects;
    private List<Group> groups;
    private int nextId;
    private GraphicObjectPanel panel;
    private HistoryCommandHandler history;


    private GraphicObjectHolder(){
        this.objects= new LinkedList<>();
        this.groups=new LinkedList<>();
        this.nextId=0;
        this.panel= new GraphicObjectPanel();
        this.history= new HistoryCommandHandler();
    }

    public HistoryCommandHandler getHistory() {
        return this.history;
    }

    public GraphicObjectPanel getPanel(){
        return this.panel;
    }

    public static synchronized GraphicObjectHolder getInstance(){
        if(instance==null){
            instance= new GraphicObjectHolder();
        }
        return instance;
    }

    public int nextId(){
        return this.nextId++;
    }

    public int addObject(GraphicObject o){
        if(o.getType().equals("Group")){
            groups.add((Group) o);
            return o.getId();
        }
        else{
            objects.add(o);
            return o.getId();
        }
    }

    public void removeGroup(Group g){
        if(groups.contains(g)){
            groups.remove(g);
        }
    }

    public void removeObject(GraphicObject o){
        if(objects.contains(o)){
            objects.remove(o);
        }
        else if(groups.contains(o)){
            Group g = (Group) o;
            groups.remove(o);
            for(GraphicObject obj : g.getObjects()){
                if(obj.getType().equals("Group")){
                    groups.add((Group) obj);
                }
                else{
                    objects.add(obj);
                }
            }
        }
    }

    public List<GraphicObject> getAllObjects(){
        LinkedList<GraphicObject> ris = new LinkedList<>();
        for(GraphicObject o : objects){
            ris.add(o);
        }
        return ris;
    }

    public List<Group> getAllGroups(){
        LinkedList<Group> ris = new LinkedList<>();
        for(Group g: groups){
            ris.add(g);
        }
        return ris;
    }

    public List<GraphicObject> getAllByType(String type){
        LinkedList<GraphicObject> ris = new LinkedList<>();
        for(GraphicObject o : objects){
            if(o.getType().equalsIgnoreCase(type)){
                ris.add(o);
            }
        }
        return ris;
    }

    public GraphicObject getObject(int id){
        GraphicObject ris = null;
        for(GraphicObject o : objects){
            if(o.getId()==id){
                ris= o;
            }
        }
        for (GraphicObject o: groups){
            if(o.getId()==id) ris=o;
        }
        return ris;
    }
}