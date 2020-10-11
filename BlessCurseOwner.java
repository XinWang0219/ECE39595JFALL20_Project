public class BlessCurseOwner extends ItemAction {
    public BlessCurseOwner(Creature owner){
        //System.out.println("ItemAction BlessCurseOwner-> owner: "+owner.name);
    }

    @Override
    public String toString(){
        String str = "BlessCurseOwner: \n";
        str += super.toString( );
        str += "    ItemOwner: "+owner.name;
        return str;
    }
}
