public class Item extends Displayable{

    private Creature owner;

    public Item(){
        super();
    }

    public void setOwner(Creature _owner){
        owner = _owner;
        System.out.println("Item setOwner: "+owner);
    }
}