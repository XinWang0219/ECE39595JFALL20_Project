//package ECE39595JFALL20_Project;
public class Item extends Displayable{
	private String name;
    private Creature owner;
    private int room, serial;

    public Item(){
        super();
    }

    public void setOwner(Creature _owner){
        owner = _owner;
        //System.out.println("Item setOwner: "+owner);
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

    @Override
    public String toString( ) {
        String str = "Item: \n";
        str += super.toString( );
        str += "   owner: " + owner + "\n";
        return str;
    }
}