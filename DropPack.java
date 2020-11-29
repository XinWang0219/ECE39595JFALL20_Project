//package proj1;
//package ECE39595JFALL20_Project;
public class DropPack extends CreatureAction {
    private Creature owner;
    private String name;
    
    public DropPack(String _name, Creature _owner){
        //System.out.println("CreatureAction DropPack-> name:"+name+"; owner: "+owner.name);
        name = _name;
        owner = _owner;
    }
    
    public void run() {
    	if (owner.getPack().size() == 0) {
    		
    	}
    	else {
    		PlayerMove.dropItem('0');
    	}
    }

    @Override
    public String toString( ) {
        String str = "DropPack: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   owner: " + owner.name + "\n";
        return str;
    }
}
