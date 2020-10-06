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
        name = _name;
        width = _width;
        gameHeight = _gameHeight;
    }
    
    public void addRoom(Room room){
        roomList = Arrays.copyOf(roomList, roomList.length+1);
        roomList[roomList.length - 1] = room;
    }
    
    public void addCreatue(Creature creature){
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
}
