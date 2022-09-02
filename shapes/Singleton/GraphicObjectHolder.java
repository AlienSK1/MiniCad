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

    public void removeGroup(Group g) throws ObjectNotPresentException {
        if(groups.contains(g)){
            groups.remove(g);
            for(GraphicObject obj : g.getObjects()){
                if(obj.getType().equals("Group")){
                    groups.add((Group) obj);
                }
                else{
                    objects.add(obj);
                }
            }
        }
        else throw new ObjectNotPresentException("Il gruppo non è presente");
    }

    public void removeObject(GraphicObject o) throws ObjectNotPresentException {
        boolean rimosso=false;
        if(objects.contains(o)){
            objects.remove(o);
            rimosso=true;
        }
        else if(groups.contains(o) && !rimosso){
            groups.remove(o);
            rimosso=true;
        }
        if(!rimosso) throw new ObjectNotPresentException("L'ogetto non è presente");
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
        if(!(type.equals("Group") || type.equals("Circle")|| type.equals("Rectangle") || type.equals("Image"))) throw new RuntimeException("Passato come parametro un tipo non gestito");
        LinkedList<GraphicObject> ris = new LinkedList<>();
        for(GraphicObject o : objects){
            if(o.getType().equalsIgnoreCase(type)){
                ris.add(o);
            }
        }
        return ris;
    }

    public GraphicObject getObject(int id) throws ObjectNotPresentException {
        GraphicObject ris = null;
        for(GraphicObject o : objects){
            if(o.getId()==id){
                ris= o;
            }
        }
        for (GraphicObject o: groups){
            if(o.getId()==id) ris=o;
        }
        if(ris==null) throw new ObjectNotPresentException("L'oggetto cercato non è memorizzato");
        return ris;
    }
}