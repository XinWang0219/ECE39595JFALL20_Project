public class Hallucinate extends ItemAction{
    public Hallucinate(Creature owner){
        //System.out.println("ItemAction Hallucinate-> owner: "+owner.name);
    }

    @Override
    public String toString( ) {
        String str = "Hallucinate: \n";
        str += super.toString( );
        str += "   owner: " + owner.name + "\n";

        return str;
    }
}
