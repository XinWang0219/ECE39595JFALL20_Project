public class DropPack extends CreatureAction {
    public DropPack(String name, Creature owner){
        //System.out.println("CreatureAction DropPack-> name:"+name+"; owner: "+owner.name);
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
