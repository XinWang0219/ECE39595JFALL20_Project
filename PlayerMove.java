import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
public class PlayerMove implements InputObserver, Runnable{

    private static Player player = null;
    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Char pl = new Char('@');

    public PlayerMove(Player _player, ObjectDisplayGrid grid){
        player = _player;
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;

    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest();
            working = (processInput( ));
        }
    }

    private void rest() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean processInput() {

        char ch;

        boolean processing = true;
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }
                switch (ch) {
                    case 'h':
                        moveLeft();
                        break;
                    case 'j':
                        moveDown();
                        break;
                    case 'k':
                        moveUp();
                        break;
                    case 'l':
                        moveRight();
                        break;
                    default:;
                }
            }
        }
        return true;
    }


    private boolean isMovable(int x, int y) {
        char obj = displayGrid.getChar(x,y);
        if(obj == '#' || obj == '+' || obj == '.') {
            return true;
        }
        else {
            return false;
        }
    }

    private void moveUp() {
        if (isMovable(player.getPosX(), (player.getPosY()-1))){
            player.setPosY(player.getPosY() - 1);
        }
    }

    private void moveDown() {
        if (isMovable(player.getPosX(), (player.getPosY()+1))){
            player.setPosY(player.getPosY() + 1);
        }
    }

    private void moveLeft() {
        if (isMovable((player.getPosX()-1), player.getPosY())){
            player.setPosX(player.getPosX() - 1);
        }
    }

    private void moveRight() {
        if (isMovable((player.getPosX()+1), player.getPosY())){
            player.setPosY(player.getPosX() + 1);
        }
    }
}
