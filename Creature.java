public class Creature extends Displayable{

    private int h;      // hp
    private CreatureAction da;      // death action
    private CreatureAction ha;      // hit action
    
    //add roomid and serial properties
    private int roomID;
    private int serial;
    
    public Creature(){
        super();
    }

    public void setHP(int _h){
        h = _h;
        System.out.println("Creature setHP: "+h);
    }

    public void setDeathAction(CreatureAction _da){
        da = _da;
        System.out.println("Creature setDeathAction: "+da);
    }

    public void setHitAction(CreatureAction _ha){
        ha = _ha;
        System.out.println("Creature setHitAction: "+ha);
    }
    
    public void setID(int _roomID, int _serial){
        roomID = _roomID;
        serial = _serial;
    }
}