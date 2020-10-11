public class Hallucinate extends ItemAction{
    private Creature owner;

    public Hallucinate(Creature _owner){
        //System.out.println("ItemAction Hallucinate-> owner: "+owner.name);
        owner = _owner;
    }

    @Override
    public String toString( ) {
        String str = "Hallucinate: \n";
        str += super.toString( );
        str += "   owner: " + owner.name + "\n";

        return str;
    }
}
