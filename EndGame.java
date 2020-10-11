public class EndGame extends CreatureAction {
    public EndGame(String name, Creature owner){
        //System.out.println("CreatureAction EndGame-> name:"+name+"; owner: "+owner.name);
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
