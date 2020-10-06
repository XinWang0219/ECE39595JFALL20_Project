public class Monster extends Creature{

    private int room, serial;
    private String name;

    public Monster(){
        super();
    }

    public void setName(String string){
        name = string;
        System.out.println("Monster setName: "+name);
    }

    public void setID(int _room, int _serial){
        room = _room;
        serial = _serial;
        System.out.println("Monster setID: " + room + "; " + serial);
    }
}