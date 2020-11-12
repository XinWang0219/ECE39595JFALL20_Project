//package ECE39595JFALL20_Project;
public class Scroll extends Item{
    private String name;
    private int room, serial;

    public Scroll(String _name){
        super.setName(_name);
        //System.out.println("Scroll: "+name);
    }

    public void setID(int _room, int _serial){
        room = _room;
        serial = _serial;
        //System.out.println("Monster setHP: " + room + "; " + serial);
    }

    public int getRoom(){
        return room;
    }

    public int getSerial(){
        return serial;
    }

    @Override
    public String toString( ) {
        String str = "Scroll: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   room: " + room + "\n";
        str += "   serial: " + serial + "\n";
        return str;
    }
}