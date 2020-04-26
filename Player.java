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
	private int maxDamageThreshold;	// Probability of player inflicting max damage.

	//	Constructor
	public Player(){

		location = new int[] {0,2};	// Initialise current location to (0,2)
		HP = 100;	// Initial health of Player (maximum 100 HP)
		maxDamage = 25;	// Initial maxDamage of player
		items = new ArrayList<Item>();	// Initialise ArrayList of items.
		maxDamageThreshold = 7;
	}

	//	Get the current HP of the player
	public int getHP(){
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

	//	Get the location of the player in [row, column] format.
	public int[] getLocation(){
		return location;
	}
	
	public void setLocation(int row, int col){
		location[0] = row;
		location[1] = col;
	}

	//	Print the current stats of the player
	public void getStats(){
		System.out.println("\n    ------------------------------------------");
		System.out.println("    | HP:                  " + HP + "HP");
		System.out.println("    ------------------------------------------");
		System.out.println("    | Maximum damage:      " + maxDamage + "HP");
		System.out.println("    ------------------------------------------");
		System.out.println("    | Current Location:   ("+location[0]+", "+location[1]+")");
		System.out.println("    ------------------------------------------");
		System.out.print("    | Items:               ");

		Item[] itemList = this.getItemList();

		for(int i=0; i < itemList.length; i++){
			System.out.print(itemList[i].getDescription()+" ");
		}
		System.out.println("    ------------------------------------------");
	}

	//	Add an item to the array list.
	public void addItem(Item it){
		items.add(it);
	}
	
	//	Remove an item from the array with item description specified by itemDesc.
	public void removeItem(String itemDesc){
		
		// 	Iterate through the array list
		Iterator itr = items.iterator();
		Item it;	// Temporary variable
		
		//	Loop through all the items
		while(itr.hasNext()){
			it = (Item) itr.next();
			
			if(it.getDescription().equals("potion")){
				items.remove(it);
				break;
			}
		}
	}

	//  Get a list of the current items
	public Item[] getItemList(){

		//	If the items list is empty then print a message notifying the user and return an empty array.
		if (items.isEmpty()){
			System.out.println("None");
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
	
	//	Get the maxDamageThreshold
	public int getMaxDamageThreshold(){
		return maxDamageThreshold;
	}
	
	//	Set the max damage threshold.
	public void setMaxDamageThreshold(int newThres){
		maxDamageThreshold = newThres;
	}
}
