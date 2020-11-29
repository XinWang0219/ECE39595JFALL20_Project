///package proj1;
public class YouWin extends CreatureAction {
    private Creature owner;
    private String name;
//    private static ObjectDisplayGrid displayGrid;
    
    public YouWin(String _name, Creature _owner){
        //System.out.println("CreatureAction YouWin-> name:"+name+"; owner: "+owner.name);
        name = _name;
        owner = _owner;
    }
    
    public void run() {
    	ObjectDisplayGrid.writeInfo(this.getMessage());
    }

    @Override
    public String toString( ) {
        String str = "YouWin: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   owner: " + owner.name + "\n";
        return str;
    }
}
