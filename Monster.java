public class Monster extends Creature{

    private int room, serial;
    private String name;

    public Monster(){
        super();
    }

    public void setName(String string){
        name = string;
        //System.out.println("Monster setName: "+name);
    }

    public void setID(int _room, int _serial){
        room = _room;
        serial = _serial;
        //System.out.println("Monster setID: " + room + "; " + serial);
    }

    public int getRoom(){
        return room;
    }

    public int getSerial(){
        return serial;
    }

    @Override
    public String toString( ) {
        String str = "Monster: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   room: " + room + "\n";
        str += "   serial: " + serial + "\n";
        return str;
    }
}