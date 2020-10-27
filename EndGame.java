package proj1;
public class EndGame extends CreatureAction {
    private Creature owner;
    private String name;
    
    public EndGame(String _name, Creature _owner){
        //System.out.println("CreatureAction EndGame-> name:"+name+"; owner: "+owner.name);
        name = _name;
        owner = _owner;
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
