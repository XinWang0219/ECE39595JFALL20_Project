//package proj1;
//package ECE39595JFALL20_Project;
import java.util.*;
import java.util.Random;

public class Teleport extends CreatureAction{
    private Creature owner;
    private String name;
//    private static ObjectDisplayGrid displayGrid;
    
    public Teleport(String _name, Creature _owner){
        //System.out.println("CreatureAction Teleport-> name:"+name+"; owner: "+owner.name);
        name = _name;
        owner = _owner;
    }
    
    public void run() {
    	Char passage = new Char('#');
    	Char floor = new Char('.');
    	Random rand = new Random();
    	
    	Char[][] objectGrid = ObjectDisplayGrid.getObjectGrid();
    	System.out.println(objectGrid.length);
    	System.out.println(objectGrid[0].length);
    	List<Point> avaliable = new ArrayList<Point>();
    	for (int i = 0; i < objectGrid.length; i++) {
    		for (int j = 0; j < objectGrid[0].length; j++) {
//    			System.out.println(objectGrid[i][j].toString());
    			if (objectGrid[i][j] != null) {
	    			if (objectGrid[i][j].getChar() == '#' || objectGrid[i][j].getChar() == '.') {
	    				Point p = new Point(i, j-2);
	    				avaliable.add(p);
	    			}
    			}
    		}
    	}
    	int l = avaliable.size();
    	System.out.println(l);
    	int index = rand.nextInt(l);
    	owner.setPosX(avaliable.get(index).getX());
    	owner.setPosY(avaliable.get(index).getY());
    	System.out.println(String.format("x: %d, y %d",avaliable.get(index).getX(), avaliable.get(index).getY() ));
    }

    @Override
    public String toString( ) {
        String str = "Teleport: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   owner: " + owner.name + "\n";
        return str;
    }
}
