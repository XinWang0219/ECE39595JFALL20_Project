import java.util.*;
public class Room extends Structure{
    private int roomID;
    private String room;
    List<Creature> creature = new ArrayList<Creature>();

    public Room(String string){
        super();
        room = string;
        System.out.println(room);

    public void setId(int room){
        roomID = room;
        System.out.println("Room setID: "+roomID);
    }

    public void setCreature(Creature Monster){
        creature.add(Monster);
        System.out.println("Room setCreature: "+creature);
    }
}