public class Passage extends Structure{

    private String name;
    private int room1, room2;

    public Passage(){
        super();
    }

    public void setName(String string){
        name = string;
        System.out.println("Passage setName: "+name);
    }

    public void setID(int _room1, int _room2){
        room1 = _room1;
        room2 = _room2;
        System.out.println("Passage setID: %d, %d", room1, room2);
    }
}