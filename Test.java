//package ECE39595JFALL20_Project;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.xml.sax.SAXException;

public class Test implements Runnable{

    private static ObjectDisplayGrid displayGrid = null;
    private static Dungeon dungeon = null;
    //private static Player player = null;

    Test() {

    }

    @Override
    public void run(){
        displayGrid.fireUp();
        displayGrid.initializeDisplay();

        for(int i = 0; i < dungeon.roomList.size(); i++){
            Room room = dungeon.roomList.get(i);
            displayGrid.displayRoom(room);

            for (int j = 0; j < dungeon.itemList.size(); j++) {
                if (dungeon.itemList.get(j) instanceof Armor){
                    Armor armor = (Armor) dungeon.itemList.get(j);
                    if (armor.getRoom() == room.getRoomID()) {
                        displayGrid.displayArmor(armor, room);
                    }
                }
                else if(dungeon.itemList.get(j) instanceof Sword){
                    Sword sword = (Sword) dungeon.itemList.get(j);
                    if (sword.getRoom() == room.getRoomID()) {
                        displayGrid.displaySword(sword, room);
                    }
                }
                else{
                    Scroll scroll = (Scroll) dungeon.itemList.get(j);
                    if (scroll.getRoom() == room.getRoomID()) {
                        displayGrid.displayScroll(scroll, room);
                    }
                }
            }

            for (int j = 0; j < dungeon.creatureList.size(); j++) {
                if (dungeon.creatureList.get(j) instanceof Monster){
                    Monster monster = (Monster) dungeon.creatureList.get(j);
                    if (monster.getRoom() == room.getRoomID()) {
                        displayGrid.displayMonster(monster, room);
                    }
                }
                else if (dungeon.creatureList.get(j) instanceof Player){
                    Player player = (Player) dungeon.creatureList.get(j);
                    if (player.getRoom() == room.getRoomID()) {
                        displayGrid.displayPlayer(player, room);
                    }
                }
            }
        }

        for (int i = 0; i < dungeon.passageList.size(); i++){
            Passage passage = dungeon.passageList.get(i);
            displayGrid.displayPassage(passage);
        }
    }


    public static void main(String[] args) throws Exception{
        // check if a filename is passed in.  If not, print a usage message.
        // If it is, open the file
        //String fileName = "src/main/java/xmlFiles/testDrawing.xml";
        String fileName;
        switch (args.length){
            case 1:
                fileName = "xmlExample/" + args[0];
                break;
            default:
                System.out.println("java Test <xmlfilename>");
                return;
        }

        // Create a saxParserFactory, that will allow use to create a parser
        // Use this line unchanged
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();


        // We haven't covered exceptions, so just copy the try { } catch {...}
        // exactly, // filling in what needs to be changed between the open and
        // closed braces.
        try {
            // just copy this
            SAXParser saxParser = saxParserFactory.newSAXParser();
            // just copy this
            DungeonXMLHandler handler = new DungeonXMLHandler();
            // just copy this.  This will parse the xml file given by fileName
            //System.out.println("Before parsing the file");
            saxParser.parse(new File(fileName), handler);
            // This will change depending on what kind of XML we are parsing
            dungeon = handler.getDungeons();
            displayGrid = handler.getDisplayGrid();

            System.out.println(dungeon);
            //System.out.println("test print");
            /*
             * the above is a different form of
             for (int i = 0; i < students.length; i++) {
                System.out.println(students[i]);
            }
            */
            // these lines should be copied exactly.
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }

        Test test = new Test();
        Thread testThread = new Thread(test);
        testThread.start();
        testThread.join();
    }

}
