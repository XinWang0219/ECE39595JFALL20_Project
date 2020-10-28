//package proj1;
import java.util.*;

public class Passage extends Structure{

    private String name;
    private int room1, room2;
    private List<Point> pointList = new ArrayList<Point>();

    public Passage(){
        super();
    }

    public void setName(String string){
        name = string;
        //System.out.println("Passage setName: "+name);
    }

    public void setID(int _room1, int _room2){
        room1 = _room1;
        room2 = _room2;
        //System.out.println("Passage setID: " + room1 + "; " + room2);
    }
    
    public void addPoint(Point point){
        pointList.add(point);
    }
    
    public List<Point> getPointList(){
        return pointList;
    }

    @Override
    public String toString( ) {
            String str = "Passage: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   passage between: " + room1 + "and" + room2 + "\n";
        return str;
    }
}