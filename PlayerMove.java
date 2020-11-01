import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
public class PlayerMove implements InputObserver, Runnable{

    private static Player player = null;
    private static int DEBUG = 1;
    private static String CLASSID = "PlayerMove";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Char pl = new Char('@');

    public PlayerMove(Player _player, ObjectDisplayGrid grid){
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        player = _player;

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
                    case 'E':
                        System.out.println("End Game!");
                        return false;
                    default:;
                }
            }
        }
        return true;
    }


    private boolean isMovable(int x, int y) {
        char obj = displayGrid.getChar(x,(y-displayGrid.getTopHeight()));
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
        else{
            System.out.println("NOT MOVABLE!");
        }
    }

    private void moveDown() {
        if (isMovable(player.getPosX(), (player.getPosY()+1))){
            player.setPosY(player.getPosY() + 1);
        }
        else{
            System.out.println("NOT MOVABLE!");
        }
    }

    private void moveLeft() {
        if (isMovable((player.getPosX()-1), player.getPosY())){
            player.setPosX(player.getPosX() - 1);
        }
        else{
            System.out.println("NOT MOVABLE!");
        }
    }

    private void moveRight() {
        if (isMovable((player.getPosX()+1), player.getPosY())){
            player.setPosX(player.getPosX() + 1);
        }
        else{
            System.out.println("NOT MOVABLE!");
        }
    }
}
