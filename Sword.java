public class Sword extends Item{
    private String name;
    private int room, serial;

    public Sword(String _name){
        name = _name;
        //System.out.println("Sword: "+name);
    }

    public void setID(int _room, int _serial){
        room = _room;
        serial = _serial;
        //System.out.println("Sword setID: " + room + "; " + serial);
    }

    public int getRoom(){
        return room;
    }

    public int getSerial(){
        return serial;
    }

    @Override
    public String toString( ) {
        String str = "Sword: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   room: " + room + "\n";
        str += "   serial: " + serial + "\n";
        return str;
    }
}