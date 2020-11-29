//package proj1;
//package ECE39595JFALL20_Project;
public class Hallucinate extends ItemAction{
    private Item owner;

    public Hallucinate(Item _owner){
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
