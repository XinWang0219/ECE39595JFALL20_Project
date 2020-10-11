public class Scroll extends Item{
    private String name;
    private int room, serial;

    public Scroll(String _name){
        name = _name;
        //System.out.println("Scroll: "+name);
    }

    public void setID(int _room, int _serial){
        room = _room;
        serial = _serial;
        //System.out.println("Monster setHP: " + room + "; " + serial);
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