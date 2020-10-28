//package proj1;
//package ECE39595JFALL20_Project;
public class Teleport extends CreatureAction{
    private Creature owner;
    private String name;
    
    public Teleport(String _name, Creature _owner){
        //System.out.println("CreatureAction Teleport-> name:"+name+"; owner: "+owner.name);
        name = _name;
        owner = _owner;
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
