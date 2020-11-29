import java.util.ArrayList;
import java.util.List;

//package proj1;
//package ECE39595JFALL20_Project;
public class Player extends Creature{

    private Item sword = null;
    private Item armor = null;

    public Player(){
        super();
    }

    public void setWeapon(Item _sword){
        sword = _sword;
        sword.setOwner(this);
        //System.out.println("Player setWeapon: "+sword);
    }

    public void setArmor(Item _armor){
        armor = _armor;
        armor.setOwner(this);
        //System.out.println("Player setArmor: "+armor);
    }
    
    public boolean dropWeapon() {
    	if (sword == null) {
    		return false;
    	}
    	else {
//	    	super.addItem(sword);
	    	sword.removeOwner();
	    	sword = null;
	    	return true;
    	}
    }
    
    public boolean dropArmor() {
    	if (armor == null) {
    		return false;
    	}
    	else {
//        	super.addItem(armor);
        	armor.removeOwner();
        	armor = null;
        	return true;
    	}
    }
    
    public int getWeapon() {
    	if (sword == null) {
    		return 0;
    	}
    	return sword.getIntValue();
    }
    
    public int getArmor() {
    	if (armor == null) {
    		return 0;
    	}
    	return armor.getIntValue();
    }

    @Override
    public String toString( ) {
        String str = "Player: \n";
        str += super.toString( );
        str += "   sword: " + sword + "\n";
        str += "   armor: " + armor + "\n";

        return str;
    }
}