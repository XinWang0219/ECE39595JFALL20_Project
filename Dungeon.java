import java.util.*;

public class Dungeon {
    String name;
    int width;
    int gameHeight;
    
    List<Room> roomList = new ArrayList<Room>();
    List<Creature> creatureList = new ArrayList<Creature>();
    List<Passage> passageList = new ArrayList<Passage>();
    List<Item> itemList = new ArrayList<Item>();
    
    public Dungeon(){
        
    }
    
    public void getDungeon(String _name, int _width, int _gameHeight){
        this.name = _name;
        this.width = _width;
        this.gameHeight = _gameHeight;
    }
    
    public void addRoom(Room room){
        roomList.add(room);
    }
    
    public void addCreature(Creature creature){
        creatureList.add(creature);
    }
    
    public void addPassage(Passage passage){
        passageList.add(passage);
    }
    
    public void addItem(Item item){
        itemList.add(item);
    }
    
    @Override
    public String toString(){
        String str = "Dungeon: \n";
        str += "    name: "+name + "\n";
        str += "    width: "+width + "\n";
        str += "    gameHeight: "+gameHeight + "\n";
        for (Room room: roomList){
            str += room.toString() + "\n";
        }
        for (Creature creature: creatureList){
            str += creature.toString() + "\n";
        }
        for (Passage passage: passageList){
            str += passage.toString() + "\n";
        }
        for (Item item: itemList){
            str += item.toString() + "\n";
        }
        return str;
    }
}
