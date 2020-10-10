import java.util.*;
public class Room extends Structure{
    private int roomID;
    private String room;
    private Creature[] creature;
    //List<Creature> creature = new ArrayList<Creature>();

    public Room(String string) {
        super();
        room = string;
        System.out.println(room);
    }

    public void setId(int room){
        roomID = room;
        System.out.println("Room setID: "+roomID);
    }

    public void setCreature(Creature monster){
        //creature.add(Monster);
        creature = Arrays.copyOf(creature, creature.length+1);
        creature[creature.length-1] = monster;
        System.out.println("Room setCreature: "+creature);
    }
    
    @Override
    public String toString(){
        String str = "Room: \n";
        str += "    roomID: "+roomID;
//        for(Creature monster: creature){
//            str += monster.toString();
//        }
        return str;
    }
}