import java.util.Arrays;
import java.util.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DungeonXMLHandler extends DefaultHandler{
    // the two lines that follow declare a DEBUG flag to control
    // debug print statements and to allow the class to be easily
    // printed out.  These are not necessary for the parser.
    private static final int DEBUG = 1;
    private static final String CLASSID = "StudentXMLHandler";
    
    // data can be called anything, but it is the variables that
    // contains information found while parsing the xml file
    private StringBuilder data = null;
    
    private Dungeon dungeon = null;
    private ObjectDisplayGrid objectDisplayGrid = null;
    private Room[] rooms;
    private Passage[] passages;
    
    //private Dungeon dungeonBeingParsed = null;
    private Room roomBeingParsed = null;
    private Passage passageBeingParsed = null;
    private Monster monsterBeingParsed = null;
    private Player playerBeingParsed = null;
    private Armor armorBeingParsed = null;
    private Sword swordBeingParsed = null;
    private Scroll scrollBeingParsed = null;
    private CreatureAction creatureActionBeingParsed = null;
    private ItemAction itemActionBeingParsed = null;
    
    //
    private List<String> currentDisplay = new ArrayList<String>(); //the name of parameters
    private boolean actionflag = false; //true: CreatureAction; false: ItemAction
    private Creature owner = null; //store the address of current creature
           
    
    //bX fileds
    private boolean bVisible = false;
    private boolean bMaxHit = false;
    private boolean bHpMove = false;
    private boolean bHp = false;
    private boolean bType = false;
    private boolean bIntValue = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bWidth = false;
    private boolean bHeight = false;
    
    private boolean bActionMessage = false;
    private boolean bActionIntValue = false;
    private boolean bActionCharValue = false;
    
    
    
    public Dungeon getDungeons(){
        return dungeon;
    }
    
    public DungeonXMLHandler(){
        
    }
    
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }
        // create Dungeon and ObjectDisplayGrid
        if (qName.equalsIgnoreCase("Dungeon")){
            System.out.println("Find Dungeon");
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int width = Integer.parseInt(attributes.getValue("width"));
            String name = attributes.getValue("name");
 //           dungeon = new Dungeon();
            //System.out.println("Scanned inputs: ");
//            System.out.println("    bottomHeight: "+bottomHeight);
//            System.out.println("    gameHeight: "+gameHeight);
//            System.out.println("    topHeight: "+topHeight);
//            System.out.println("    width: "+width);
//            System.out.println("    name: "+name);
            
            dungeon = new Dungeon();
            dungeon.getDungeon(name, width, gameHeight);
            //System.out.println("passing input to dungeon:");
            //System.out.println(dungeon.toString());
            
            objectDisplayGrid = new ObjectDisplayGrid();
            objectDisplayGrid.getObjectDisplayGrid(gameHeight, width, topHeight);
            //test
            //System.out.println(objectDisplayGrid.toString());
            
            //create new room and passage arraylist for this dungeon
            rooms = new Room[0];
            passages = new Passage[0];
        }
        else if (qName.equalsIgnoreCase("Rooms")){
            System.out.println("Find Rooms");
        }
        //create new room, add to room ArrayList, and start room parse
        else if (qName.equalsIgnoreCase("Room")){
            System.out.println("Find Room:");
            String roomID = attributes.getValue("room");
            Room room = new Room(roomID);
            int roomid = Integer.parseInt(roomID);
            room.setId(roomid);
            addRoom(room);
            System.out.println("After add room");
            roomBeingParsed = room;
            currentDisplay.add("Room");
            System.out.println("End the start of the Room");
        }
        //create new passage, add to passage ArrayList, and start passage parse
        else if (qName.equalsIgnoreCase("passages")){
        }
        else if (qName.equalsIgnoreCase("passage")){
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage();
            passage.setID(room1, room2);
            addPassage(passage);
            passageBeingParsed = passage;
            currentDisplay.add("Passage");
        }
        //parse the displayable fields
        else if (qName.equalsIgnoreCase("visible")){
            bVisible = true;
        }
        else if (qName.equalsIgnoreCase("posX")){
            System.out.println("Find posX:");
            bPosX = true;
        }
        else if (qName.equalsIgnoreCase("posY")){
            bPosY = true;
        }
        else if (qName.equalsIgnoreCase("width")){
            bWidth = true;
        }
        else if (qName.equalsIgnoreCase("height")){
            bHeight = true;
        }
        else if (qName.equalsIgnoreCase("maxhit")){
            System.out.println("Find maxhit");
            bMaxHit = true;
        }
        else if (qName.equalsIgnoreCase("hpMoves")){
            bHpMove = true;
        }
        else if (qName.equalsIgnoreCase("hp")){
            bHp = true;
        }
        else if (qName.equalsIgnoreCase("Type")){
            bType = true;
        }
        else if (qName.equalsIgnoreCase("IntValue")){
            bIntValue = true;
        }
        else if (qName.equalsIgnoreCase("Monster")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster();
            monster.setName(name);
            monster.setID(roomID, serial);
            System.out.println("Monster error check:");
            roomBeingParsed.setCreature(monster);
            System.out.println("After room setCreature");
            monsterBeingParsed = monster;
            currentDisplay.add("Monster");
            owner = monster;
        }
        else if (qName.equalsIgnoreCase("Player")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Player player = new Player();
            player.setName(name);
            player.setID(roomID, serial);
            playerBeingParsed = player;
            currentDisplay.add("Player");
            owner = player;
        }
        else if (qName.equalsIgnoreCase("Armor")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Armor armor = new Armor(name);
            armor.setID(roomID, serial);
            armorBeingParsed = armor;
            currentDisplay.add("Armor");
        }
        else if (qName.equalsIgnoreCase("Sword")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(name);
            sword.setID(roomID, serial);
            swordBeingParsed = sword;
            currentDisplay.add("Sword");
        }
        else if (qName.equalsIgnoreCase("Scroll")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(name);
            scroll.setID(roomID, serial);
            scrollBeingParsed = scroll;
            currentDisplay.add("Scroll");
        }
        else if (qName.equalsIgnoreCase("CreatureAction")){
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            CreatureAction creatureAction = null;
            switch (name){
                case "Remove":
                    creatureAction = new Remove(name, owner);
                    break;
                case "YouWin":
                    creatureAction = new YouWin(name, owner);
                    break;
                case "UpdateDisplay":
                    creatureAction = new UpdateDisplay(name, owner);
                    break;
                case "Teleport":
                    creatureAction = new Teleport(name, owner);
                    break;
                case "ChangedDisplayType":
                    creatureAction = new ChangedDisplayedType(name, owner);
                    break;
                case "EndGame":
                    creatureAction = new EndGame(name, owner);
                    break;
                case "DropPack":
                    creatureAction = new DropPack(name, owner);
                    break;
                default:
                    System.out.println("Unkown CreatureAction: "+name);
                    break;     
            }
            creatureActionBeingParsed = creatureAction;
            actionflag = true;
        }
        else if(qName.equalsIgnoreCase("ItemAction")){
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            ItemAction itemAction = null;
            switch (name){
                case "BlessCurseOwner":
                    itemAction = new BlessCurseOwner(owner);
                    break;
                case "Hallucinate":
                    itemAction = new Hallucinate(owner);
                    break;
                default:
                    System.out.println("Unknown ItemAction: "+name);
            }
            itemActionBeingParsed = itemAction;  
            actionflag = false;
        }
        else if (qName.equalsIgnoreCase("actionMessage")){
            bActionMessage = true;
        }
        else if (qName.equalsIgnoreCase("actionIntValue")){
            bActionIntValue = true;
        }
        else if (qName.equalsIgnoreCase("actionCharValue")){
            bActionCharValue = true;
        }
        
        //System.out.println("After every start finding, read data");
        data = new StringBuilder();
        //System.out.println("Data: "+ data.toString());
        
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Displayable display = new Displayable();
        
        switch (currentDisplay.get(currentDisplay.size() - 1)){
            case "Room":
                display = (Displayable) roomBeingParsed;
                break;
            case "Passage":
                display = (Displayable) passageBeingParsed;
                break;
            case "Monster":
                display = (Displayable) monsterBeingParsed;
                break;
            case "Player":
                display = (Displayable) playerBeingParsed;
                break;
            case "Armor":
                display = (Displayable) armorBeingParsed;
                System.out.println("Display is Armor:");
                System.out.println("armorBeingParsed: "+ armorBeingParsed.toString());
                break;
            case "Sword":
                display = (Displayable) swordBeingParsed;
                break;
            case "Scroll":
                display = (Displayable) scrollBeingParsed;
                break;
            default:
                System.out.println("Unknown displayable error!");
        }
        
        if (bVisible) {
            boolean visibility = Boolean.parseBoolean(data.toString());
            if (visibility){
                display.setVisible();
            }
            else if (!visibility){
                display.setInvisible();
            }
            else{
                System.out.println("Display visibility False!");
            }
            bVisible = false;
        }
        else if (bPosX) {
            System.out.println("before setPox");
            System.out.println(display.toString());
            System.out.println(data.toString());
            display.setPosX(Integer.parseInt(data.toString()));
            System.out.println("after setPox");
            bPosX = false;
        }
        else if (bPosY) {
            display.setPosY(Integer.parseInt(data.toString()));
            bPosY = false;
        }
        else if (bMaxHit){
            System.out.println("At end of maxhit:");
            System.out.println("currentDisplay: "+currentDisplay.get(currentDisplay.size() - 1));
            System.out.println("Parse input: "+data.toString());
            //System.out.println("dispaly: "+display.toString());
            display.setMaxHit(Integer.parseInt(data.toString()));
            System.out.println("dispaly: "+display.toString());
            System.out.println("After setMaxHit");
            bMaxHit = false;
        }
        else if (bHpMove){
            display.setHpMove(Integer.parseInt(data.toString()));
            bHpMove = false;
        }
        else if (bHp){
            display.setHp(Integer.parseInt(data.toString()));
            bHp = false;
        }
        else if (bType){
            display.setType(data.toString().charAt(0));
            bType = false;
        }
        else if (bIntValue){
            display.setIntValue(Integer.parseInt(data.toString()));
            bIntValue = false;
        }
        else if (bWidth){
            display.setWidth(Integer.parseInt(data.toString()));
            bWidth = false;
        }
        else if (bHeight){
            display.setHeight(Integer.parseInt(data.toString()));
            bHeight = false;
        }

        //ending BeingParsed
        else if (qName.equalsIgnoreCase("Dungeon")){  
        }
        else if (qName.equalsIgnoreCase("Rooms")){
        }
        else if (qName.equalsIgnoreCase("Room")){
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            roomBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Passages")){
        }
        else if (qName.equalsIgnoreCase("Passage")){
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            passageBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Monster")){
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            monsterBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Player")){
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            playerBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Armor")){
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            armorBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Sword")){
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            swordBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Scroll")){
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            scrollBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("CreatureAction")){
            creatureActionBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("ItemAction")){
            itemActionBeingParsed = null;
        }
        else if (bActionMessage){
            if(actionflag){
                creatureActionBeingParsed.setMessage(data.toString());
            }
            else{
                itemActionBeingParsed.setMessage(data.toString());
            }
            bActionMessage = false;
        }
        else if (bActionIntValue){
            if(actionflag){
                creatureActionBeingParsed.setIntValue(Integer.parseInt(data.toString()));
            }
            else{
                itemActionBeingParsed.setIntValue(Integer.parseInt(data.toString()));
            }
            bActionIntValue = false;
        }
        else if (bActionCharValue){
            if (actionflag){
                creatureActionBeingParsed.setCharValue(data.toString().charAt(0));
            }
            else{
                itemActionBeingParsed.setCharValue(data.toString().charAt(0));
            }
            bActionCharValue = false;
        }
        else{
            System.out.println("Unknown qname: "+qName);
        }
    }
    
    private void addRoom(Room room){
        System.out.println(room.toString());
        System.out.println("Before Adding room:");
        System.out.println(this.rooms.length);
        for(Room r: rooms){
            System.out.println(r.toString());
        }
        System.out.println("no result because of empty list");
        rooms = Arrays.copyOf(rooms, rooms.length+1);
        rooms[rooms.length-1] = room;
    }
    
    private void addPassage(Passage passage){
        passages = Arrays.copyOf(passages, passages.length+1);
        passages[passages.length-1] = passage;
    }
    
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }
}


