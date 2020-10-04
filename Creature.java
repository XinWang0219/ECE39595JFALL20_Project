public class Creature extends Displayable{

    private int h;      // hp
    private CreatureAction da;      // death action
    private CreatureAction ha;      // hit action

    public Creature{
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
}