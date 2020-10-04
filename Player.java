public class Player extends Creature{

    private Item sword;
    private Item armor;

    public Player(){
        super();
    }

    public void setWeapon(Item _sword){
        sword = _sword;
        System.out.println("Player setWeapon: "+sword);
    }

    public void setArmor(Item _armor){
        armor = _armor;
        System.out.println("Player setArmor: "+armor);
    }
}