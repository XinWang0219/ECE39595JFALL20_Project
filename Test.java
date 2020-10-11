
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Test {
    public static void main(String[] args){
        // check if a filename is passed in.  If not, print a usage message.
	// If it is, open the file
        String fileName = "src/main/java/xmlFiles/testDrawing.xml";
//        switch (args.length){
//            case 1:
//                String fileName = "src/xmlExample" + args[0];
//            
//            default:
//                System.out.println("java Test <xmlfilename>");
//                return;
//            
//        }
        
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
    }

}
