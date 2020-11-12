import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reaction {
	static Dungeon dungeon;
	static Player player;
	private Point point = new Point();
	private CreatureAction creatureAction = new CreatureAction();
	private ObjectDisplayGrid displayGrid;
	

	public Reaction(Dungeon dungeon2, Player _player, int i, int j, ObjectDisplayGrid grid) {
		// TODO Auto-generated constructor stub
		dungeon = dungeon2;
		player = _player;
		point.setX(i);
		point.setY(j);
		displayGrid = grid;
	}

	public void interactMonster(int x, int y) {
		String message = "";
		Random rand = new Random();
		//find which thing on this coordinate
		System.out.println(String.format("Target position %d, %d", x, y) );
		for (int i = 0; i < dungeon.creatureList.size(); i++) {
			if (dungeon.creatureList.get(i) instanceof Monster){
				Monster monster = (Monster) dungeon.creatureList.get(i);
				int mx = monster.getPosX();
				int my = monster.getPosY();
				int room_index = monster.getRoom();
				Room room = dungeon.roomList.get(room_index-1);
				
				Point mp = c_converter(mx, my, room);
				message = String.format("check in monster list: monster %c, position at %d, %d", monster.getType(), mp.getX(), mp.getY());
				System.out.println(message);
				if (isAtPosition(x, y, mp)) {
					dungeon.creatureList.remove(i);
					int player_maxHit = player.getMaxHit();
					int damage = rand.nextInt(player_maxHit+1);
					System.out.print(String.format("maxhit = %d, damage = %d, monster hp = %d", player_maxHit, damage, monster.getHp()));
					if (monster.getHp() <= damage) {
						monster.setHp(0);
						monster.setInvisible();
						dungeon.creatureList.add(monster);
						displayGrid.writeInfo(String.format("player killed the monster %c", monster.getType()));
					}
					else {
						System.out.println("player hit monster");
						monster.setHp(monster.getHp() - damage);
						dungeon.creatureList.add(monster);
						int monster_damage = rand.nextInt(monster.getMaxHit()+1);
						if (player.getHp() <= monster_damage) {
							System.out.println("player died, end game");
						}
						else {
							player.setHp(player.getHp() - monster_damage);
						}
						String s = String.format("player cause %d damage to Monster %c and recieved %d damage", damage, monster.getType(), monster_damage);
						displayGrid.writeInfo(s);
					}
					break;
				}
			}
			
		}
	}
	
	public void interactItem(int x, int y) {
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < dungeon.itemList.size(); i++) {
			Item item = dungeon.itemList.get(i);
			int ix = item.getPosX();
			int iy = item.getPosY();
			int room_index = item.getRoom();
			Room room = dungeon.roomList.get(room_index-1);
			Point ip = new Point();
			if (room_index == 0) {
				ip.setX(ix);
				ip.setY(iy);
			}
			else {
				ip = c_converter(ix, iy, room);
			}
			if (isAtPosition(x, y, ip)) {
				items.add(item);
			}
		}
		if (items.size() <= 0) {
			displayGrid.writeInfo("no items on the floor");
		}
		else {
			player.addItem(items.get(items.size()-1));
			String s = "player pick up" + items.get(items.size()-1).getName();
			displayGrid.writeInfo(s);
			dungeon.itemList.remove(items.get(items.size()-1));
		}
	}
	
	private Point c_converter(int x, int y, Room room) {
		Point p = new Point(0 , 0);
		int rx = room.getPosX();
		int ry = room.getPosY();
		p.setX(x+rx);
		p.setY(y+ry+2);
		return p;
	}
	
	private boolean isAtPosition(int x, int y, Point p) {
		if (x == p.getX() && y == p.getY()) {
			return true;
		}
		else {
			return false;
		}
	}

}
