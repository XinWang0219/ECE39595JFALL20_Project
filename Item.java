//package ECE39595JFALL20_Project;
import java.util.*;

public class Item extends Displayable{
	public String name;
    private Creature owner;
    private int room, serial;
    private List<ItemAction> ia = new ArrayList<ItemAction>();

    public Item(){
        super();
    }

    public void setOwner(Creature _owner){
        owner = _owner;
        //System.out.println("Item setOwner: "+owner);
    }
    
    public void addItemAction(ItemAction _ia) {
    	ia.add(_ia);
    }
    
    public int getRoom(){
        return room;
    }

    public int getSerial(){
        return serial;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String _name) {
    	name = _name;
    }
    
    public void setRoom(int _room) {
    	room = _room;
    }
    
    public List<ItemAction> getActionList(){
    	return ia;
    }
    
    public boolean checkOwner(Creature creature) {
    	if (owner == creature) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void removeOwner() {
    	owner = null;
    }

    @Override
    public String toString( ) {
        String str = "Item: \n";
        str += super.toString( );
        str += "   owner: " + owner + "\n";
        return str;
    }
}