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
    private static int movec = 0;
    private int wearmode = 0;
    private int endgamemode = 0;
    private int takeoutmode = 0;
    private int helpmode = 0;
    private int readmode = 0;
    public static boolean working;
    private static int hmoves = 0;

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
        working = true;
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
                if (dropmode == 0 && wearmode == 0 && endgamemode == 0 && takeoutmode == 0 && helpmode == 0 && readmode == 0) {
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
	                    	endgamemode = 1;
	                    	ObjectDisplayGrid.writeInfo("Make sure you want to end game? (Y|y)");
	                        break;
	                    case 'p':
	                    	pickItem();
	                    	break;
	                    case 'd':
	                    	dropmode = 1;
	                    	break;
	                    case 'i':
	                    	ObjectDisplayGrid.setPackMode(1);
	                    	break;
	                    case 'c' :
	                    	if (player.dropArmor() == false) {
	                    		 ObjectDisplayGrid.writeInfo("no armor is worn");
	                    	}
	                    	break;
	                    case 'w' :
	                    	wearmode = 1;
	                    	break;
	                    case 'T' :
	                    	takeoutmode = 1;
	                    	break;
	                    case '?':
	                    	Command cmd = new Command();
	                    	ObjectDisplayGrid.writeInfo(cmd.command);
	                    	break;
	                    case 'H':
	                    	helpmode = 1;
	                    	ObjectDisplayGrid.writeInfo("type a command");
	                    	break;
	                    case 'r':
	                    	readmode = 1;
	                    	ObjectDisplayGrid.writeInfo("type index from 0 - 9");
	                    default:;
	                }
                }
                else if (readmode == 1) {
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
	                    	readmode = 0;
	                    	readScroll(ch);
	                    	break;
	                    default:
	                    	ObjectDisplayGrid.writeInfo("must follow 0-9 after command d");
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
	                    	ObjectDisplayGrid.writeInfo("must follow 0-9 after command d");
                	}
                }
                else if (wearmode == 1) {
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
	                    	wearmode = 0;
	                    	wearArmor(ch);
	                    	break;
	                    default:
	                    	ObjectDisplayGrid.writeInfo("must follow 0-9 after command d");
                	}
                }
                else if (takeoutmode == 1) {
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
	                    	takeoutmode = 0;
	                    	wearSword(ch);
	                    	break;
	                    default:
	                    	ObjectDisplayGrid.writeInfo("must follow 0-9 after command d");
                	}
                }
                else if (endgamemode == 1) {
                	if (ch == 'Y'|| ch == 'y') {
                		ObjectDisplayGrid.writeInfo("End Game by typing command E");
                		return false;
                	}
                	else {
                		ObjectDisplayGrid.writeInfo(" ");
                		endgamemode = 0;
                	}
                }
                else if (helpmode == 1 ) {
                	helpmode = 0;
                	switch (ch) {
                		case 'c': 
                			ObjectDisplayGrid.writeInfo("Change, or take off armor 'c': armor that is being worn is taken off and placed it in the pack. If no armor "
                					+ "is being worn a message should be shown in the info area of the game display.");
                			break;
                		case 'd':
                			ObjectDisplayGrid.writeInfo("Drop 'd' <integer>: drop item <integer> from the pack, where <integer>-1 is an index into the pack container.");
                			break;
                		case 'E':
                			ObjectDisplayGrid.writeInfo("End game 'E' <Y | y>: end the game. Print a message in the info area using Char objects added to the "
                					+ "objectGrid to make it clear why the game ended.");
                			break;
                		case '?':
                			ObjectDisplayGrid.writeInfo("Help: '?': show the different commands in the info section of the display. This is the bottom message "
                					+ "display area");
                			break;
                		case 'i':
                			ObjectDisplayGrid.writeInfo("Show or display the inventory 'i': show the contents of the pack, printing the name for each item in the "
                					+ "pack.");
                			break;
                		case 'p':
                			ObjectDisplayGrid.writeInfo("Pick up an item from the dungeon floor 'p': pick up the item on the dungeon floor location that the "
                					+ "player is standing on and add it to the pack container. ");
                			break;
                		case 'r':
                			ObjectDisplayGrid.writeInfo("Read an item 'r' <integer>: the item specified by the <integer>-1 must be a scroll that is in the pack."
                					+ "Reading a scroll causes the ItemActions associated with the scroll to be performed.");
                			break;
                		case 'T':
                			ObjectDisplayGrid.writeInfo("Take out a weapon 'T' <integer>: take the sword identified by <integer>-1 in the pack container and "
                					+ "have the player wield it.");
                			break;
                		case 'w':
                			ObjectDisplayGrid.writeInfo("Wear item 'w' <integer>: take the armor identified by <integer> from the pack container and make it the "
                					+ "armor being worn.");
                			break;
                		default:
	                    	ObjectDisplayGrid.writeInfo("no such command exists");
                		
                		
                	}
                }
                
            }
        }
        
        if (working == false) {
        	return false;
        }
        return true;
    }
    
    private void wearSword(char ch) {
    	int index = Character.getNumericValue(ch);
    	List<Item> pack = player.getPack();
    	if (index >= pack.size()) {
    		ObjectDisplayGrid.writeInfo("input index is larger than pack contents");
    	}
    	else {
	    	Item item = pack.get(index);
	    	if (item instanceof Sword) {
	    		player.dropWeapon();
	    		player.setWeapon(item);
//	    		item.setOwner(player);
	    	}
	    	else {
	    		ObjectDisplayGrid.writeInfo("The item is not Sword");
	    	}
    	}
    }
    
    private void wearArmor(char ch) {
    	int index = Character.getNumericValue(ch);
    	List<Item> pack = player.getPack();
    	if (index >= pack.size()) {
    		ObjectDisplayGrid.writeInfo("input index is larger than pack contents");
    	}
    	else {
	    	Item item = pack.get(index);
	    	if (item instanceof Armor) {
	    		player.dropArmor();
	    		player.setArmor(item);
//	    		item.setOwner(player);
	    	}
	    	else {
	    		ObjectDisplayGrid.writeInfo("The item is not Armor");
	    	}
    	}
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
    	if (hmoves > 0) {
    		ObjectDisplayGrid.generateFake();
    		ObjectDisplayGrid.fakemode = 1;
    		hmoves -= 1;
    	}
    	else {
    		ObjectDisplayGrid.fakemode = 0;
    	}
    	
        char obj = displayGrid.getChar(x,(y-displayGrid.getTopHeight()));
        if(obj == '#' || obj == '+' || obj == '.' || obj == ')' || obj == ']' || obj == '?') {
        	countMove();
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
    
    public static void dropItem(char ch) {
    	int index = Character.getNumericValue(ch);
    	List<Item> pack = player.getPack();
    	if (index > pack.size()) {
    		ObjectDisplayGrid.writeInfo("input index is larger than pack contents");
    	}
    	else {
	    	Item item = pack.get(index);
	    	item.removeOwner();
	    	player.removeItem(item);
	    	
	    	int x = player.getPosX();
	    	int y = player.getPosY();
	    	item.setPosX(x);
	    	item.setPosY(y-2);
	    	item.setRoom(0);
	    	
	    	dungeon.itemList.add(item);
	    	ObjectDisplayGrid.writeInfo(String.format("dropped %s", item.getName()));
    	}
    }
    
    private void readScroll(char ch) {
    	int index = Character.getNumericValue(ch);
    	List<Item> pack = player.getPack();
    	if (index > pack.size()) {
    		ObjectDisplayGrid.writeInfo("input index is larger than pack contents");
    	}
    	else {
    		Item item = pack.get(index);
    		if (item instanceof Scroll) {
    			for (int i = 0; i < item.getActionList().size(); i++) {
    				if (item.getActionList().get(i) instanceof BlessCurseOwner) {
    					ItemAction ia = item.getActionList().get(i);
    					int effect = ia.getIntValue();
    	    			char target = ia.getCharValue();
    	    			if (target == 'a') {
    	    				for (int i1 = 0; i1 < pack.size(); i1++) {
    	    					if (pack.get(i1).checkOwner(player) && pack.get(i1) instanceof Armor) {
    	    						pack.get(i1).setIntValue(pack.get(i1).getIntValue() + effect);
    	    					}
    	    				}
    	    			}
    	    			else if (target == 'w') {
    	    				for (int i1 = 0; i1 < pack.size(); i1++) {
    	    					if (pack.get(i1).checkOwner(player) && pack.get(i1) instanceof Sword) {
    	    						pack.get(i1).setIntValue(pack.get(i1).getIntValue() + effect);
    	    					}
    	    				}
    	    			}
    	    			ObjectDisplayGrid.writeInfo(ia.getMessage());
    	    			pack.remove(index);
    				}
    				else if(item.getActionList().get(i) instanceof Hallucinate) {
    					ItemAction ia = item.getActionList().get(i);
    					hmoves = ia.getIntValue();
    					ObjectDisplayGrid.writeInfo(ia.getMessage());
//    					ObjectDisplayGrid.fakemode = 1;
//    					ObjectDisplayGrid.generateFake();
    					pack.remove(index);	
    				}
    				else {
    					System.out.println("something erro in get itemAction in playerMove");
    				}
    			}
    		}
    		else {
    			ObjectDisplayGrid.writeInfo("It is not scroll");
    		}
    	}
    }
    
    private void countMove() {
    	movec += 1;
    	if (movec == player.getHpMove()) {
    		movec = 0;
    		player.setHp(player.getHp() + 1);
    	}
    }
}
