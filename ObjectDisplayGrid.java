import asciiPanel.AsciiPanel;
import org.xml.sax.SAXException;
import java.io.IOException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject{
    private int gameHeight;
    private int width;
    private int topHeight;
    private int bottomHeight;
    private int height;
    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";
    private static AsciiPanel terminal;
    private Char[][] objectGrid = null;
    private List<InputObserver> inputObservers = null;
    private static Player player = null;
    private static boolean firstRun = true;
    private String packString = "";
    private String infoString = "";
    private int packmode = 0;

    public ObjectDisplayGrid(int _gameHeight, int _width, int _topHeight, int _bottomHeight ){
        width = _width;
        height = _gameHeight + _topHeight + _bottomHeight;
        topHeight = _topHeight;
        gameHeight = _gameHeight;
        bottomHeight = _bottomHeight;
        terminal = new AsciiPanel(width, height);

        objectGrid = new Char[width][height];

        //initializeDisplay();

        super.add(terminal);
        super.setSize(width * 9, height * 16);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.repaint();
        // terminal.repaint( );
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }

    public void getObjectDisplayGrid(int _gameHeight, int _width, int _topHeight, int _bottomHeight){
        gameHeight = _gameHeight;
        width = _width;
        topHeight = _topHeight;
        bottomHeight = _bottomHeight;
    }

    public void setTopMessageHeight(int _topHeight){
        topHeight = _topHeight;
    }

    @Override
    public String toString(){
        String str = "ObjectDisplayGrid: \n";
        str += "    gameHeight: "+gameHeight + "\n";
        str += "    width: "+width + "\n";
        str += "    topHeight: "+topHeight + "\n";
        str += "    bottomHeight: "+bottomHeight + "\n";
        return str;
    }

    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay(Dungeon dungeon) {
        //print divider
//        Char ch = new Char('-');
//        for (int i = 0; i < width; i++) {
//            addObjectToDisplay(ch, i, topHeight);
//            addObjectToDisplay(ch, i, topHeight+gameHeight);
//        }


        //display Room : and visibable monsters and items
        for(int i = 0; i < dungeon.roomList.size(); i++){
            Room room = dungeon.roomList.get(i);
            displayRoom(room);

            for (int j = 0; j < dungeon.itemList.size(); j++) {
                if (dungeon.itemList.get(j) instanceof Armor){
                    Armor armor = (Armor) dungeon.itemList.get(j);
                    if (armor.getRoom() == room.getRoomID()) {
                        displayArmor(armor, room);
                    }
                }
                else if(dungeon.itemList.get(j) instanceof Sword){
                    Sword sword = (Sword) dungeon.itemList.get(j);
                    if (sword.getRoom() == room.getRoomID()) {
                        displaySword(sword, room);
                    }
                }
                else{
                    Scroll scroll = (Scroll) dungeon.itemList.get(j);
                    if (scroll.getRoom() == room.getRoomID()) {
                        displayScroll(scroll, room);
                    }
                }
            }

            for (int j = 0; j < dungeon.creatureList.size(); j++) {
                if (dungeon.creatureList.get(j) instanceof Monster){
                    Monster monster = (Monster) dungeon.creatureList.get(j);
                    if (monster.getRoom() == room.getRoomID()) {
                        //System.out.println("ROOM ID:"+room.getRoomID()+"Monster"+j);
                        displayMonster(monster, room);
                    }
                }
                if (dungeon.creatureList.get(j) instanceof Player){
                    player = (Player) dungeon.creatureList.get(j);             
                    if (player.getRoom() == room.getRoomID() && firstRun) {
                        //displayPlayer(player, room);
                        int pl_x = room.getPosX() + player.getPosX();
                        int pl_y = room.getPosY() + player.getPosY() + topHeight;

                        player.setPosY(pl_y);
                        player.setPosX(pl_x);

                        displayPlayer(player);
                        firstRun = false;
                    }
                }
            }   
        }

    //display passages
        for (int i = 0; i < dungeon.passageList.size(); i++){
            Passage passage = dungeon.passageList.get(i);
            displayPassage(passage);
        }
        //displayPlayer(player);
        terminal.repaint();
    }


    public char getChar(int x, int y){
        char ch;
        try {
            ch = objectGrid[x][y + topHeight].getChar();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            ch = 'q';
        }
        return ch;
    }

    public final void displayRoom(Room room) {
        Char fl = new Char('.');
        Char wall = new Char('X');

        // floor
        for (int i = (room.getPosX()+1); i < (room.getPosX() + room.getWidth()-1); i++) {
            for (int j = (topHeight + room.getPosY()+1); j < (topHeight + room.getPosY() + room.getHeight()-1);j++) {
                addObjectToDisplay(fl, i, j);
            }
        }

        // vertical wall
        for (int i = (topHeight + room.getPosY()); i < (topHeight + room.getPosY() + room.getHeight()-1); i++) {
            addObjectToDisplay(wall, room.getPosX(), i);
            addObjectToDisplay(wall, (room.getPosX() + room.getWidth()-1),i);
        }

        //horizontal wall
        for (int i = room.getPosX() ; i < (room.getPosX() + room.getWidth()); i++) {
            addObjectToDisplay(wall, i, (topHeight+room.getPosY()));
            addObjectToDisplay(wall, i,(room.getPosY() + room.getHeight() + topHeight-1));
        }

        //terminal.repaint();
    }

    // after running displayPlayer, player's coordinates are set corresponding to game area (instead of room)
    public final void displayPlayer(Player player) {
        Char pl = new Char('@');
        int pl_x = player.getPosX();
        int pl_y = player.getPosY();
        addObjectToDisplay(pl, pl_x, pl_y);
        //player.setPosY(pl_y);
        //player.setPosX(pl_x);
        //terminal.repaint();
    }

    public final void displayMonster(Monster monster, Room room) {
    	if (monster.isVisible()) {
	        Char mon = new Char(monster.getType());
	        int mon_x = room.getPosX() + monster.getPosX();
	        int mon_y = room.getPosY() + monster.getPosY() + topHeight;
	        addObjectToDisplay(mon, mon_x, mon_y);
	
	        //terminal.repaint();
    	}
    }

    public final void displayScroll(Scroll scroll, Room room) {
        if(scroll.isVisible()) {
            Char scl = new Char('?');
            int scl_x = room.getPosX() + scroll.getPosX();
            int scl_y = room.getPosY() + scroll.getPosY() + topHeight;
            addObjectToDisplay(scl, scl_x, scl_y);
        }
        //terminal.repaint();
    }

    public final void displayArmor(Armor armor, Room room) {
        Char troll = new Char('T');
        Char snake = new Char('S');
        Char goblin = new Char('H');

        if(armor.isVisible()) {
            Char arm = new Char(']');
            int arm_x = room.getPosX() + armor.getPosX();
            int arm_y = room.getPosY() + armor.getPosY() + topHeight;
            if (objectGrid[arm_x][arm_y] != troll & objectGrid[arm_x][arm_y] != snake & objectGrid[arm_x][arm_y] != goblin){
                addObjectToDisplay(arm, arm_x, arm_y);
            }
        }
        //terminal.repaint();
    }

    public final void displaySword(Sword sword, Room room) {
        if(sword.isVisible()) {
            Char sw = new Char(')');
            int sw_x = room.getPosX() + sword.getPosX();
            int sw_y = room.getPosY() + sword.getPosY() + topHeight;
            addObjectToDisplay(sw, sw_x, sw_y);
        }
        //terminal.repaint();
    }

    public final void displayPassage(Passage passage){
        Char ch = new Char('#');
        List<Point> pointList = passage.getPointList();
        List<Point> line = null;
        //System.out.println("point list size:"+pointList.size());
        for(int i = 0; i < pointList.size() - 1; i++){
            Point p1 = pointList.get(i);
            Point p2 = pointList.get(i+1);
            line = getLine(p1, p2);
            for(int j = 0; j < line.size(); j++){
                int x = line.get(j).getX();
                int y = line.get(j).getY();
//                System.out.println("xy value:"+x+" "+y);
                addObjectToDisplay(ch, x, (y+topHeight));
            }
        }
        Char door = new Char('+');
        Point start = pointList.get(0);
        Point end = pointList.get((pointList.size() - 1));
        addObjectToDisplay(door, start.getX(), (start.getY()+topHeight));
        addObjectToDisplay(door, end.getX(), (end.getY()+topHeight));
        //terminal.repaint();
    }

    public List<Point> getLine(Point p1, Point p2){
        List<Point> line = new ArrayList<Point>();
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();
//        System.out.println("x1y1 value:"+x1+" "+y1);
//        System.out.println("x2y2 value:"+x2+" "+y2);
        if (x1 == x2){
            if (y1 <= y2){
                line = createHLine(x1, y1, y2);
            }
            else {
                line = createHLine(x1, y2, y1);
            }
        }
        else if (y1 == y2){
            if (x1 <= x2){
                line = createVLine(y1, x1, x2);
            }
            else {
                line = createVLine(x1, y2, y1);
            }
        }
        return line;
    }

    public List<Point> createHLine(int x, int y1, int y2){
        List<Point> line = new ArrayList<Point>();
        for (int j = y1; j <= y2; j++){
            Point p = new Point(x, j);
            line.add(p);
        }
        return line;
    }

    public List<Point> createVLine(int y, int x1, int x2){
        List<Point> line = new ArrayList<Point>();
        for (int i = x1; i <= x2; i++){
            Point p = new Point(i, y);
            line.add(p);
        }
        return line;
    }
    
    public void showTopInfo() {
    	String topInfo = String.format("HP:%4d  core:  0", player.getHp());
    	for (int i = 0; i < topInfo.length(); i++) {
    		Char ch = new Char(topInfo.charAt(i));
    		addObjectToDisplay(ch, i, 0);
    	}
    	
    	//terminal.repaint();
    }
    
    public void showBottomInfo() {
    	
//    	for (int i = 0; i < width; i++) {
//    		Char ch = new Char(' ');
//    		addObjectToDisplay(ch, i, (topHeight + gameHeight + 1));
//    		addObjectToDisplay(ch, i, (topHeight + gameHeight + 3));
//    	}
    	int i = 0;
    	
    	if (packmode == 1) {
    		String packInfo ="Pack: ";
    		List<Item> pack = player.getPack();
	    	
	    	for(i = 0; i< pack.size() && i < 10; i++) {
	    		packInfo = packInfo + Integer.toString(i) + ": " + pack.get(i).getName() + " ,";
	    	}
    	
	    	for (i = 0; i < packInfo.length(); i++) {
	    		Char ch = new Char(packInfo.charAt(i));
	    		addObjectToDisplay(ch, i, (topHeight + gameHeight + 1 + i/width));
	    	}
	    	for (int j = i; j < bottomHeight * width; j++) {
	    		Char ch = new Char(' ');
	    		addObjectToDisplay(ch, j, (topHeight + gameHeight + 1 + j/width));
	    	}
    	}
    	else if (packmode == 0) {
    	String messageInfo = String.format("Info:");
	    	messageInfo = messageInfo + infoString;
	    	for (i = 0; i < messageInfo.length(); i++) {
	    		Char ch = new Char(messageInfo.charAt(i));
	    		addObjectToDisplay(ch, i, (topHeight + gameHeight + 1));
	    	}
	    	for (int j = i; j < width; j++) {
	    		Char ch = new Char(' ');
	    		addObjectToDisplay(ch, j, (topHeight + gameHeight + 1));	
	    	}
    	}
    	
    	//terminal.repaint();
    }
    
    public void writeInfo(String s) {
    	setPackMode(0);
    	infoString = s;
    }

    public void fireUp() {
        //if (terminal.requestFocusInWindow()) {
        System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        //} else {
        //System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        //}
    }

    public void addObjectToDisplay(Char ch, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y] = ch;
                writeToTerminal(x, y);
            }
        }
    }

    private void writeToTerminal(int x, int y) {
        char ch = objectGrid[x][y].getChar();
        terminal.write(ch, x, y);
        terminal.repaint();
    }
    
    public int getTopHeight() {
    	return topHeight;
    }
    
    public void setPackMode(int i) {
    	packmode = i;
    }
}

