public class ChangedDisplayedType extends CreatureAction{
    public ChangedDisplayedType(String name, Creature owner){
        //System.out.println("CreatureAction ChangeDisplayedType-> name:"+name+"; owner: "+owner.name);
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
