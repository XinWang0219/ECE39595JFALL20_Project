//
//public class ObjectDisplayGrid {
//    private int gameHeight;
//    private int width;
//    private int topHeight;
//
//    public ObjectDisplayGrid(){
//
//    }
//
//    public void getObjectDisplayGrid(int _gameHeight, int _width, int _topHeight){
//        gameHeight = _gameHeight;
//        width = _width;
//        topHeight = _topHeight;
//    }
//
//    public void setTopMessageHeight(int _topHeight){
//        topHeight = _topHeight;
//    }
//
//    @Override
//    public String toString(){
//        String str = "ObjectDisplayGrid: \n";
//        str += "    gameHeight: "+gameHeight + "\n";
//        str += "    width: "+width + "\n";
//        str += "    topHeight: "+topHeight + "\n";
//        return str;
//    }
//
//}

import asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject{
    private int gameHeight;
    private int width;
    private int topHeight;
    private int bottomHeight;
    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";
    private static AsciiPanel terminal;
    private Char[][] objectGrid = null;
    private List<InputObserver> inputObservers = null;

    public ObjectDisplayGrid(int _gameHeight, int _width, int _topHeight, int _bottomHeight ){
        width = _width;
        height = _gameHeight + _topHeight + _bottomHeight;

        terminal = new AsciiPanel(width, height);

        objectGrid = new Char[width][height];

        initializeDisplay();

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

    public final void initializeDisplay() {
        Char ch = new Char('-');
        for (int i = 0; i < width; i++) {
            addObjectToDisplay(ch, i, topHeight);
            addObjectToDisplay(ch, i, topHeight+gameHeight);
        }
        terminal.repaint();
    }

    public void fireUp() {
        //if (terminal.requestFocusInWindow()) {
        //    System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        //} else {
        System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
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
}

