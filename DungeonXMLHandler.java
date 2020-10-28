
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
    private List<Room> roomList = new ArrayList<Room>();
    private List<Passage> passageList = new ArrayList<Passage>();
    private List<Creature> creatureList = new ArrayList<Creature>();
    private List<Item> itemList = new ArrayList<Item>();
    
    
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
    private Point point = null;
    
    //bX fileds
    private boolean bVisible = false;
    private boolean bMaxHit = false;
    private boolean bHpMove = false;
    private boolean bHp = false;
    private boolean bType = false;
    private boolean bItemIntValue = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bWidth = false;
    private boolean bHeight = false;
    
    private boolean bActionMessage = false;
    private boolean bActionIntValue = false;
    private boolean bActionCharValue = false;
    
    
    
    public Dungeon getDungeons(){
        for (Room room: roomList){
            dungeon.addRoom(room);
        }
        for (Passage passage: passageList){
            dungeon.addPassage(passage);
        }
        for(Creature creature: creatureList){
            dungeon.addCreature(creature);
        }
        for(Item item: itemList){
            dungeon.addItem(item);
        }
        return dungeon;
    }
    
    public ObjectDisplayGrid getDisplayGrid(){
        return objectDisplayGrid;
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
            //System.out.println("Find Dungeon");
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int width = Integer.parseInt(attributes.getValue("width"));
            String name = attributes.getValue("name");
            
            dungeon = new Dungeon();
            dungeon.getDungeon(name, width, gameHeight);
            //System.out.println("passing input to dungeon:");
            //System.out.println(dungeon.toString());
            
            objectDisplayGrid = new ObjectDisplayGrid(gameHeight, width, topHeight, bottomHeight);
            objectDisplayGrid.getObjectDisplayGrid(gameHeight, width, topHeight, bottomHeight);
            //test
            //System.out.println(objectDisplayGrid.toString());
            
        }
        else if (qName.equalsIgnoreCase("Rooms")){
            //System.out.println("Find Rooms");
        }
        //create new room, add to room ArrayList, and start room parse
        else if (qName.equalsIgnoreCase("Room")){
            //System.out.println("Find Room:");
            String roomID = attributes.getValue("room");
            Room room = new Room(roomID);
            int roomid = Integer.parseInt(roomID);
            room.setId(roomid);
            
            roomList.add(room);
            roomBeingParsed = room;
            currentDisplay.add("Room");
        }
        //create new passage, add to passage ArrayList, and start passage parse
        else if (qName.equalsIgnoreCase("passages")){
        }
        else if (qName.equalsIgnoreCase("passage")){
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage();
            passage.setID(room1, room2);
            
            passageList.add(passage);
            passageBeingParsed = passage;
            currentDisplay.add("Passage");
        }
        //parse the displayable fields
        else if (qName.equalsIgnoreCase("visible")){
            bVisible = true;
        }
        else if (qName.equalsIgnoreCase("posX")){
            //System.out.println("Find posX:");
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
            //System.out.println("Find maxhit");
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
        else if (qName.equalsIgnoreCase("itemIntValue")){
            bItemIntValue = true;
        }
//        else if (qName.equalsIgnoreCase("actionIntValue")){
//            bActionIntValue = true;
//        }
//        else if (qName.equalsIgnoreCase("actionCharValue")){
//            bActionCharValue = true;
//        }
        else if (qName.equalsIgnoreCase("Monster")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster();
            monster.setName(name);
            monster.setID(roomID, serial);
            
            //System.out.println("Monster: "+monster.toString());
            creatureList.add(monster);

            roomBeingParsed.setCreature(monster);
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
            
            creatureList.add(player);
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
            
            itemList.add(armor);
            armorBeingParsed = armor;
            currentDisplay.add("Armor");
        }
        else if (qName.equalsIgnoreCase("Sword")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(name);
            sword.setID(roomID, serial);
            
            itemList.add(sword);
            swordBeingParsed = sword;
            currentDisplay.add("Sword");
        }
        else if (qName.equalsIgnoreCase("Scroll")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(name);
            scroll.setID(roomID, serial);
            
            itemList.add(scroll);
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
                case "ChangeDisplayedType":
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
        
        if (currentDisplay.size() == 0){
            
        }
        else{
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
        }
        
        if (bVisible) {
            int visibility = Integer.parseInt(data.toString());
            if (visibility == 1){
                display.setVisible();
            }
            else if (visibility==0){
                display.setInvisible();
            }
            else{
                System.out.println("Display visibility False!");
            }
            bVisible = false;
        }
        else if (bPosX) {
            if (currentDisplay.get(currentDisplay.size() - 1) == "Passage" ){
                point = new Point();
                point.setX(Integer.parseInt(data.toString()));
            }
            else {
                display.setPosX(Integer.parseInt(data.toString()));
            }   
            bPosX = false;
        }
        else if (bPosY) {
            if (currentDisplay.get(currentDisplay.size() - 1) == "Passage" ){
                point.setY(Integer.parseInt(data.toString()));
                passageBeingParsed.addPoint(point);
                point = null;
            }
            else {
                display.setPosY(Integer.parseInt(data.toString()));
            }
            bPosY = false;
        }
        else if (bMaxHit){
            //System.out.println("dispaly: "+display.toString());
            display.setMaxHit(Integer.parseInt(data.toString()));
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
        else if (bItemIntValue){
            display.setIntValue(Integer.parseInt(data.toString()));
            bItemIntValue = false;
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
            //System.out.println("end dungeon parse");
        }
        else if (qName.equalsIgnoreCase("Rooms")){
            //System.out.println("end rooms parse");
        }
        else if (qName.equalsIgnoreCase("Room")){
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            //System.out.println("Room: "+roomBeingParsed.toString());
            roomBeingParsed = null;
            //System.out.println("end room parse");
            
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
            String belong = currentDisplay.get(currentDisplay.size()-2);
            if (belong == "Monster"){
                armorBeingParsed.setPosX(monsterBeingParsed.getPosX());
                armorBeingParsed.setPosY(monsterBeingParsed.getPosY());
                if (monsterBeingParsed.isVisible()){
                    armorBeingParsed.setVisible();
                }
            }
            else if(belong == "Player"){
                armorBeingParsed.setPosX(playerBeingParsed.getPosX());
                armorBeingParsed.setPosY(playerBeingParsed.getPosY());
                if (playerBeingParsed.isVisible()){
                    armorBeingParsed.setVisible();
                }
            }
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            armorBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Sword")){
            String belong = currentDisplay.get(currentDisplay.size()-2);
            if (belong == "Monster"){
                swordBeingParsed.setPosX(monsterBeingParsed.getPosX());
                swordBeingParsed.setPosY(monsterBeingParsed.getPosY());
                if (monsterBeingParsed.isVisible()){
                    swordBeingParsed.setVisible();
                }
            }
            else if(belong == "Player"){
                swordBeingParsed.setPosX(playerBeingParsed.getPosX());
                swordBeingParsed.setPosY(playerBeingParsed.getPosY());
                if (playerBeingParsed.isVisible()){
                    swordBeingParsed.setVisible();
                }
            }
            String cd = currentDisplay.get(currentDisplay.size() - 1);
            currentDisplay.remove(cd);
            swordBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Scroll")){
            String belong = currentDisplay.get(currentDisplay.size()-2);
            if (belong == "Monster"){
                scrollBeingParsed.setPosX(monsterBeingParsed.getPosX());
                scrollBeingParsed.setPosY(monsterBeingParsed.getPosY());
                if (monsterBeingParsed.isVisible()){
                    scrollBeingParsed.setVisible();
                }
            }
            else if(belong == "Player"){
                scrollBeingParsed.setPosX(playerBeingParsed.getPosX());
                scrollBeingParsed.setPosY(playerBeingParsed.getPosY());
                if (playerBeingParsed.isVisible()){
                    scrollBeingParsed.setVisible();
                }
            }
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
    
//    private void addRoom(Room room){
//        rooms = Arrays.copyOf(rooms, rooms.length+1);
//        rooms[rooms.length-1] = room;
//    }
//    
//    private void addPassage(Passage passage){
//        passages = Arrays.copyOf(passages, passages.length+1);
//        passages[passages.length-1] = passage;
//    }
    
//    private void addCreature(Creature creature){
//        creatures = Arrays.copyOf(creatures, creatures.length+1);
//        creatures[creatures.length - 1] = creature;
//    }
    
//    private void addItem(Item item){
//        items = Arrays.copyOf(items, items.length + 1);
//        items[items.length - 1] = item;
//    }
    
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }
}


