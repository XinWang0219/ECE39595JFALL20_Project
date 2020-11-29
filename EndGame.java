//package proj1;
//package ECE39595JFALL20_Project;
public class EndGame extends CreatureAction {
    private Creature owner;
    private String name;
//    private static boolean working;
//    private static ObjectDisplayGrid displayGrid;
    
    public EndGame(String _name, Creature _owner){
        //System.out.println("CreatureAction EndGame-> name:"+name+"; owner: "+owner.name);
        name = _name;
        owner = _owner;
    }
    
    public void run() {
    	ObjectDisplayGrid.writeInfo(this.getMessage());
    	PlayerMove.working = false;
    }

    @Override
    public String toString( ) {
        String str = "EndGame: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   owner: " + owner.name + "\n";
        return str;
    }
}
