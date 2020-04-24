/*
	PLAYER CLASS
*/

/*	Import Packages	*/
import java.util.ArrayList;	// Array Lists
import java.util.Iterator;	// Iterator for array lists

public class Player{

	//	Variables
	private int[] location;	// Location of the player, represented as [ROW,COL].
	private int HP;	// Player health
	private int maxDamage;	// Maximum damage player can incur on monsters
	private ArrayList<Item> items;	// Items held by the player.

	//	Constructor
	public Player(){

		location = new int[] {0,1};	// Initialise current location to (2,0)
		HP = 100;	// Initial health of Player (maximum 100 HP)
		maxDamage = 50;	// Initial maxDamage of player
		items = new ArrayList<Item>();	// Initialise ArrayList of items.
	}

	//	Get the current HP of the player
	public int getCurrentHP(){
		return HP;
	}

	//	Change the HP - used in fights or when player uses potion.
	public void setHP(int newHP){
		HP = newHP;
	}

	// Check if player is Alive. Returns true if HP > 0
	public boolean isAlive(){
		return HP > 0;
	}

	//	Get the maxDamage of player
	public int getMaxDamage(){
		return maxDamage;
	}

	//	Set the maximum damage of the player
	public void setMaxDamage(int newMaxDamage){
		maxDamage = newMaxDamage;
	}

	//	Get the location of the player
	public int[] getLocation(){
		return location;
	}

	//	Print the current stats of the player
	public void getCurrentStats(){
		System.out.println("\n	HP: "+HP);
		System.out.println("	maxDamage: "+maxDamage);
		System.out.println("	Location: ("+location[0]+", "+location[1]+")");
		System.out.print("    	Items: ");

		Item[] itemList = this.getItemList();

		for(int i=0; i < itemList.length; i++){
			System.out.print(itemList[i].getDescription()+" ");
		}
		System.out.println("");
	}

	//	Add an item to the array list.
	public void addItem(Item it){
		items.add(it);
	}

	//  Get a list of the current items
	public Item[] getItemList(){

		//	If the items list is empty then print a message notifying the user and return an empty array.
		if (items.isEmpty()){
			System.out.println("  None");
			return new Item[]{};
		}

		// Otherwise, if the tile has entities then print convert each object from the ArrayList to Entity class.
		Item[] item_list=new Item[items.size()];
		int i=0;

		//	Iterate through the entities.
		Iterator itr = items.iterator();

		while(itr.hasNext()){
			item_list[i] = (Item) itr.next();
			i++;
		}

		return item_list;
	}

	//	Check to see if player has an item
	public boolean hasItem(String itemDesc){

		// Loop through the items array list.
		Iterator itr = items.iterator();

		while(itr.hasNext()){
			Item currentItem = (Item) itr.next();

			if (currentItem.getDescription().equals(itemDesc)){
				return true;
			}
		}

		return false;
	}
}
