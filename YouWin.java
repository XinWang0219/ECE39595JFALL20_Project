package proj1;
public class YouWin extends CreatureAction {
    private Creature owner;
    private String name;
    
    public YouWin(String _name, Creature _owner){
        //System.out.println("CreatureAction YouWin-> name:"+name+"; owner: "+owner.name);
        name = _name;
        owner = _owner;
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
