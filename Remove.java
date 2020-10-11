public class Remove extends CreatureAction {
    public Remove(String name, Creature owner){
        //System.out.println("CreatureAction Remove-> name:"+name+"; owner: "+owner.name);
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
