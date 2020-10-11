public class Remove extends CreatureAction {
    private Creature owner;
    private String name;
    
    public Remove(String _name, Creature _owner){
        //System.out.println("CreatureAction Remove-> name:"+name+"; owner: "+owner.name);
        name = _name;
        owner = _owner;
    }

    @Override
    public String toString( ) {
        String str = "Remove: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   owner: " + owner.name + "\n";
        return str;
    }
}
