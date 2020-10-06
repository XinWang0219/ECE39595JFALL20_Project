public class Armor extends Item{
    private String name;
    private int room, serial;

    public Armor(String string){
        name = string;
        System.out.println("Armor: " +name);
    }

    public void setName(String _name){
        name = _name;
        System.out.println("Armor setName: "+name);
    }

    public void setID(int _room, int _serial){
        room = _room;
        serial = _serial;
        System.out.println("Armor setID: "+room +"; " + serial);
    }
}