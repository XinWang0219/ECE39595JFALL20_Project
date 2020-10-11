public class ChangedDisplayedType extends CreatureAction{
    private Creature owner;
    private String name;
    
    public ChangedDisplayedType(String _name, Creature _owner){
        //System.out.println("CreatureAction ChangeDisplayedType-> name:"+name+"; owner: "+owner.name);
        owner = _owner;
        name = _name;
                
    }

    @Override
    public String toString(){
        String str = "ChangedDisplayedType: \n";
        str += super.toString( );
        str += "    name: "+name;
        str += "    owner:"+owner.name;
        return str;
    }
}
