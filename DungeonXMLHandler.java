
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
    private int roomCount = 0;
    private passage[] passages;
    private int passageCount = 0;
    
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
    private String currentDisplay = null;
    private Creature owner = null;
           
    
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
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int width = Integer.parseInt(attributes.getValue("width"));
            String name = attributes.getValue("name");
            dungeon = new Dungeon();
            dungeon.getDungeon(name, width, gameHeight);
            objectDisplayGrid = new ObjectDisplayGrid();
            objectDisplayGrid.getObjectDisplayGrid(gameHeight, width, topHeight);

        }
        else if (qName.equalsIgnoreCase("Rooms")){
        }
        //create new room, add to room ArrayList, and start room parse
        else if (qName.equalsIgnoreCase("Room")){
            int roomID = Integer.parseInt(attributes.getValue("room"));
            Room room = new Room();
            addRoom(room);
            roomBeingParsed = room;
            currentDisplay = "Room";
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
            currentDisplay = "Passage";
        }
        //parse the displayable fields
        else if (qName.equalsIgnoreCase("visible")){
            bVisible = true;
        }
        else if (qName.equalsIgnoreCase("posX")){
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
            bMaxHit = true;
        }
        else if (qName.equalsIgnoreCase("hpMoves")){
            bHpMove = true;
        }
        else if (qName.equalsIgnoreCase("hp")){
            bHp = true;
        }
//        else if (qName.equalsIgnoreCase("Type")){
//            bType = true;
//        }
//        else if (qName.equalsIgnoreCase("IntValue")){
//            bIntValue = true;
//        }
        else if (qName.equalsIgnoreCase("Monster")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster();
            monster.setName(name);
            monster.setID(roomID, serial);
            room.setCreature(monster);
            monsterBeingParsed = monster;
            currentDisplay = "Monster";
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
            currentDisplay = "Player";
            owner = player;
        }
        else if (qName.equalsIgnoreCase("Armor")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Armor armor = new Armor(name);
            armor.setID(roomID, serial);
            armorBeingParsed = armor;
            currentDisplay = "Armor";
        }
        else if (qName.equalsIgnoreCase("Sword")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(name);
            sword.serID(roomID, serial);
            swordBeingParsed = sword;
            currentDisplay = "Sword";
        }
        else if (qName.equalsIgnoreCase("Scroll")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(name);
            scroll.setID(roomID, serial);
            scrollBeingParsed = scroll;
            currentDisplay = "Scroll";
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
        }
        
        data = new StringBuilder();
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (currentDisplay){
            case "Room":
                Room display = roomBeingParsed;
                break;
            case "Passage":
                Passage display = passageBeingParsed;
                break;
            case "Monster":
                Monster display= monsterBeingParsed;
                break;
            case "Player":
                Player display = playerBeingParsed;
                break;
            case "Armor":
                Armor display = armorBeingParsed;
                break;
            case "Sword":
                Sword display = swordBeingParsed;
                break;
            case "Scroll":
                Scroll display = scrollBeingParsed;
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
            display.setPosX(Integer.parseInt(data.toString()));
            bPosX = false;
        }
        else if (bPosY) {
            display.setPosY(Integer.parseInt(data.toString()));
            bPosY = false;
        }
        else if (bMaxHit){
            display.setMaxHit(Integer.parseInt(data.toString()));
            bMaxHit = false;
        }
        else if (bHpMove){
            display.setHpMove(Integer.parseInt(data.toString()));
            bHpMove = false;
        }
        else if (bType){
            display.setType(data.toString());
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
        else{
            System.out.println("Unknown qname: "+qName);
        }

        //ending BeingParsed
        if (qName.equalsIgnoreCase("Dungeon")){  
        }
        else if (qName.equalsIgnoreCase("Rooms")){
        }
        else if (qName.equalsIgnoreCase("Room")){
            roomBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Passages")){
        }
        else if (qName.equalsIgnoreCase("Passage")){
            passageBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Monster")){
            monsterBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Player")){
            playerBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Armor")){
            armorBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Sword")){
            swordBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("Scroll")){
            scrollBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("CreatureAction")){
            creatureActionBeingParsed = null;
        }
        else if (qName.equalsIgnoreCase("ItemAction")){
            itemActionBeingParsed = null;
        }
    }
    
    private void addRoom(Room room){
        rooms[roomCount++] = room;
    }
    
    private void addPassage(Passage passage){
        passages[passageCount++] = passage;
    }
}


