public class UpdateDisplay extends CreatureAction{
    public UpdateDisplay(String name, Creature owner){
        //System.out.println("CreatureAction UpdateDisplay-> name:"+name+"; owner: "+owner.name);
    }

    @Override
    public String toString( ) {
        String str = "UpdateDisplay: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   owner: " + owner.name + "\n";
        return str;
    }
}
