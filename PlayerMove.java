import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
public class PlayerMove implements InputObserver, Runnable{

    private static Player player = null;
    private static int DEBUG = 1;
    private static String CLASSID = "PlayerMove";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Char pl = new Char('@');
    private Reaction reaction;
    private int dropmode = 0;
    private static Dungeon dungeon = null;

    public PlayerMove(Dungeon _dungeon, Player _player, ObjectDisplayGrid grid){
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        player = _player;
        dungeon = _dungeon;
        reaction = new Reaction(dungeon, _player, 0, 0, grid);

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
                if (dropmode == 0) {
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
	                    case 'p':
	                    	pickItem();
	                    	break;
	                    case 'd':
	                    	dropmode = 1;
	                    	break;
	                    case 'I':
	                    	displayGrid.setPackMode(1);
	                    	break;
	                    default:;
	                }
                }
                else if (dropmode == 1) {
                	switch (ch) {
	                    case '0':
	                    case '1':
	                    case '2':
	                    case '3':
	                    case '4':
	                    case '5':
	                    case '6':
	                    case '7':
	                    case '8':
	                    case '9':
	                    	dropmode = 0;
	                    	dropItem(ch);
	                    	break;
	                    default:
	                    	displayGrid.writeInfo("must follow 0-9 after command d");;
                	}
                }
                
            }
        }
        return true;
    }
    
    private boolean isMonster(int x, int y) {
    	char obj = displayGrid.getChar(x,(y-displayGrid.getTopHeight()));
    	if(obj == 'T' || obj == 'S' || obj == 'H') {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    private boolean isItem(int x, int y) {
    	char obj = displayGrid.getChar(x,(y-displayGrid.getTopHeight()));
    	System.out.println(String.format("x = %d, y = %d, c = %c", x, y, obj));
    	if(obj == ')' || obj == ']' || obj == '?') {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    private boolean isMovable(int x, int y) {
        char obj = displayGrid.getChar(x,(y-displayGrid.getTopHeight()));
        if(obj == '#' || obj == '+' || obj == '.' || obj == ')' || obj == ']' || obj == '?') {
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
            int x = player.getPosX();
            int y = player.getPosY()-1;
            if (isMonster(x, y)) {
            	System.out.print("find it is monster");
            	reaction.interactMonster(x, y);
            }
            
        }
    }

    private void moveDown() {
        if (isMovable(player.getPosX(), (player.getPosY()+1))){
            player.setPosY(player.getPosY() + 1);
        }
        else{
            System.out.println("NOT MOVABLE!");
            int x = player.getPosX();
            int y = player.getPosY()+1;
            if (isMonster(x, y)) {
            	reaction.interactMonster(x, y);
            }
        }
    }

    private void moveLeft() {
        if (isMovable((player.getPosX()-1), player.getPosY())){
            player.setPosX(player.getPosX() - 1);
        }
        else{
            System.out.println("NOT MOVABLE!");
            int x = player.getPosX()-1;
            int y = player.getPosY();
            if (isMonster(x, y)) {
            	reaction.interactMonster(x, y);
            }
        }
    }

    private void moveRight() {
        if (isMovable((player.getPosX()+1), player.getPosY())){
            player.setPosX(player.getPosX() + 1);
        }
        else{
            System.out.println("NOT MOVABLE!");
            int x = player.getPosX()+1;
            int y = player.getPosY();
            if (isMonster(x, y)) {
            	reaction.interactMonster(x, y);
            }
        }
    }
    
    private void pickItem() {
    	int x = player.getPosX();
        int y = player.getPosY();
        reaction.interactItem(x,y);
    }
    
    private void dropItem(char ch) {
    	int index = Character.getNumericValue(ch);
    	List<Item> pack = player.getPack();
    	if (index >= pack.size()) {
    		displayGrid.writeInfo("input index is larger than pack contents");
    	}
    	else {
	    	Item item = pack.get(index);
	    	player.removeItem(item);
	    	
	    	int x = player.getPosX();
	    	int y = player.getPosY();
	    	item.setPosX(x);
	    	item.setPosY(y-2);
	    	item.setRoom(0);
	    	
	    	dungeon.itemList.add(item);
	    	displayGrid.writeInfo(String.format("dropped %s", item.getName()));
    	}
    }
}
