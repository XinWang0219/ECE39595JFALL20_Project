//package ECE39595JFALL20_Project;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.xml.sax.SAXException;

public class Rogue implements Runnable{

    private static ObjectDisplayGrid displayGrid = null;
    private static Dungeon dungeon = null;
    
    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
//    private static ObjectDisplayGrid displayGrid = null;
    private Thread PlayerMove;
    private static Player player = null;

    public Rogue() {

    }

    @Override
    public void run(){
        displayGrid.fireUp();
        displayGrid.initializeDisplay(dungeon);
        isRunning = true;
        while(isRunning){
            if (false){
                isRunning = false;
            }
            
            try{
                Thread.sleep(FRAMESPERSECOND);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }

            displayGrid.initializeDisplay(dungeon);
            displayGrid.displayPlayer(player);
            displayGrid.showTopInfo();
            displayGrid.showBottomInfo();

            //System.out.println("x:"+player.getPosX()+"y:"+player.getPosY());
        }

    }


    public static void main(String[] args) throws Exception{
        // check if a filename is passed in.  If not, print a usage message.
        // If it is, open the file
        //String fileName = "src/main/java/xmlFiles/testDrawing.xml";
        String fileName;
        switch (args.length){
            case 1:
                fileName = "xmlFiles/" + args[0];
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
            
            for (int i = 0; i < dungeon.creatureList.size(); i++){
                if (dungeon.creatureList.get(i) instanceof Player){
                    player = (Player) dungeon.creatureList.get(i);
                }
            }
            //System.out.println(dungeon);
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

        Rogue test = new Rogue();
        Thread testThread = new Thread(test);
        testThread.start();
        
        test.PlayerMove = new Thread(new PlayerMove(dungeon, player, displayGrid));
        test.PlayerMove.start();
        
        testThread.join();
        test.PlayerMove.join();
    }

}
