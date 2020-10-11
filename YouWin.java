public class YouWin extends CreatureAction {
    public YouWin(String name, Creature owner){
        //System.out.println("CreatureAction YouWin-> name:"+name+"; owner: "+owner.name);
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
