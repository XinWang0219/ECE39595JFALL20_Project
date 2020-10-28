//package ECE39595JFALL20_Project;
public class Item extends Displayable{

    private Creature owner;

    public Item(){
        super();
    }

    public void setOwner(Creature _owner){
        owner = _owner;
        //System.out.println("Item setOwner: "+owner);
    }

    @Override
    public String toString( ) {
        String str = "Item: \n";
        str += super.toString( );
        str += "   owner: " + owner + "\n";
        return str;
    }
}