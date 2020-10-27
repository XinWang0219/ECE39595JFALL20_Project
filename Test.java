package proj1;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Test implements Runnable{
    
    private static ObjectDisplayGrid displayGrid = null;
    
    Test() {
        
    }
    
    @Override
    public void run(){
        displayGrid.fireUp();
        displayGrid.initializeDisplay();
    }
    
    
    public static void main(String[] args) throws Exception{
        // check if a filename is passed in.  If not, print a usage message.
	// If it is, open the file
        //String fileName = "src/main/java/xmlFiles/testDrawing.xml";
        String fileName;
        switch (args.length){
            case 1:
                fileName = "src/main/java/xmlFiles/" + args[0];
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
            Dungeon dungeon = handler.getDungeons();
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
