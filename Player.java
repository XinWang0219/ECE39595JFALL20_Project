//package proj1;
//package ECE39595JFALL20_Project;
public class Player extends Creature{

    private Item sword;
    private Item armor;

    public Player(){
        super();
    }

    public void setWeapon(Item _sword){
        sword = _sword;
        //System.out.println("Player setWeapon: "+sword);
    }

    public void setArmor(Item _armor){
        armor = _armor;
        //System.out.println("Player setArmor: "+armor);
    }

    @Override
    public String toString( ) {
        String str = "Player: \n";
        str += super.toString( );
        str += "   sword: " + sword + "\n";
        str += "   armor: " + armor + "\n";

        return str;
    }
}