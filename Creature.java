import java.util.ArrayList;
import java.util.List;

//package ECE39595JFALL20_Project;
public class Creature extends Displayable{

    private int h;      // hp
    private CreatureAction da;      // death action
    private CreatureAction ha;      // hit action
    
    //add roomid and serial properties
    private int roomID;
    private int serial;
    public List<Item> pack = new ArrayList<Item>();
    
    public Creature(){
        super();
    }

    public void setHP(int _h){
        h = _h;
        //System.out.println("Creature setHP: "+h);
    }

    public void setDeathAction(CreatureAction _da){
        da = _da;
        //System.out.println("Creature setDeathAction: "+da);
    }

    public void setHitAction(CreatureAction _ha){
        ha = _ha;
        //System.out.println("Creature setHitAction: "+ha);
    }
    
    public void setID(int _roomID, int _serial){
        roomID = _roomID;
        serial = _serial;
    }

    public int getRoom(){
        return roomID;
    }
    
    public void addItem(Item item) {
    	pack.add(item);
    }
    
    public List<Item> getPack(){
    	return pack;
    }
    
    public void addpack(List<Item> items) {
    	pack.addAll(items);
    }
    
    public void removeItem(Item item) {
    	pack.remove(item);
    }

    @Override
    public String toString( ) {
        String str = "Creature: \n";
        str += super.toString( );
        str += "   HP: " + h + "\n";
        str += "   DeathAction: " + da + "\n";
        str += "   HitAction: " + ha + "\n";
        return str;
    }
}