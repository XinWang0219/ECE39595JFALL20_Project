import java.util.Arrays;

public class Dungeon {
    String name;
    int width;
    int gameHeight;
    
    Room[] roomList;
    Creature[] creatureList;
    Passage[] passageList;
    Item[] itemList;
    
    public Dungeon(){
        
    }
    
    public void getDungeon(String _name, int _width, int _gameHeight){
        this.name = _name;
        this.width = _width;
        this.gameHeight = _gameHeight;
    }
    
    public void addRoom(Room room){
        roomList = Arrays.copyOf(roomList, roomList.length+1);
        roomList[roomList.length - 1] = room;
    }
    
    public void addCreature(Creature creature){
        creatureList = Arrays.copyOf(creatureList, creatureList.length+1);
        creatureList[creatureList.length-1] = creature;
    }
    
    public void addPassage(Passage passage){
        passageList = Arrays.copyOf(passageList, passageList.length+1);
        passageList[passageList.length-1] = passage; 
    }
    
    public void addItem(Item item){
        itemList = Arrays.copyOf(itemList, itemList.length+1);
        itemList[itemList.length-1] = item;
    }
    
    @Override
    public String toString(){
        String str = "Dungeon: \n";
        str += "    name: "+name + "\n";
        str += "    width: "+width + "\n";
        str += "    gameHeight: "+gameHeight + "\n";
//        for (Room room: roomList){
//            str += room.toString() + "\n";
//        }
//        for (Creature creature: creatureList){
//            str += creature.toString() + "\n";
//        }
//        for (Passage passage: passageList){
//            str += passage.toString() + "\n";
//        }
//        for (Item item: itemList){
//            str += item.toString() + "\n";
//        }
        return str;
    }
}
