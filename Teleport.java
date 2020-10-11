public class Teleport extends CreatureAction{
    public Teleport(String name, Creature owner){
        //System.out.println("CreatureAction Teleport-> name:"+name+"; owner: "+owner.name);
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
