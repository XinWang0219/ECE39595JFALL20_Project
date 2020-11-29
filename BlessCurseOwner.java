//package proj1;
//package ECE39595JFALL20_Project;
public class BlessCurseOwner extends ItemAction {
    private Item owner;
    public BlessCurseOwner(Item _owner){
        owner = _owner;
    }

    @Override
    public String toString(){
        String str = "BlessCurseOwner: \n";
        str += super.toString( );
//        str += "    ItemOwner: "+owner.getName();
        return str;
    }
}
