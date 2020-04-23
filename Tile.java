/*
	TILE CLASS
*/

// Import packages
import java.util.ArrayList;	// To implement the ArrayList
import java.util.Iterator;	// Iterator for array list.

public class Tile{
	
	// Variables
	private int[] location = new int[2];	// Location of the Tile in [x,y] format.
	private ArrayList<Entity> entities = new ArrayList<Entity>();	// ArrayList to contain the objects located in the tile.
	private boolean isValidTile = true;	// Is the tile a valid tile through which the player can go? Default is yes.
	
	/*	Constructor
	
		Arguments:
			- int x: x-coordinate of the tile
			- int y: y-coordinate of the tile	*/
	public Tile(int x,int y){
		
		// Initialise the Tile object with the initial co-ordinates.
		location[0] = x;
		location[1] = y;
	}
	
	// Add an Entity object (item, enemy or player) to the tile
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	// Remove entity from the tile
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	// Return the list of entities
	public Entity[] getEntities(){
		
		//	If the entities list is empty return an empty array.
		if (entities.isEmpty()){
			return new Entity[]{};
		}
		
		// Otherwise, if the tile has entities then print convert each object from the ArrayList to Entity class.
		Entity[] entityList=new Entity[entities.size()];
		int i=0;
		
		//	Iterate through the entities.
		Iterator itr = entities.iterator();
		
		while(itr.hasNext()){
			entityList[i] = (Entity) itr.next();
			i++;
		}
		
		return entityList;
	}
	
	// Return the co-ordinates of the Tile
	public int[] getLocation(){
		return location;
	}
	
	// Return to check if the tile is a valid game tile
	public boolean isValidGameTile(){
		return isValidTile;
	}
	
	// Set tile to invalid
	public void setIsValidGameTile(boolean value){
		isValidTile = false;
	}
	
	//	Print a description of the tile
	public void printDescription(){
		
		// tileDescription
		System.out.println("  DESCRIPTION OF CURRENT LOCATION");
		
		Entity[] entities = this.getEntities();	// Get entities in the tile
		String[] entityTypes = new String[entities.length]; // Construct an array of Strings with each element being "<entityType>:<entity_description>"
		
		if(entities.length == 0){
			System.out.println("  There is nothing of interest here!");
		}
		// Populate the string array
		for(int i=0; i < entityTypes.length; i++){
			if(!entities[i].getType().equals("player")){
				System.out.print("  You have come across a "+entities[i].getDescription()+" "+entities[i].getType()+".\n");
				
				if(entities[i].getType().equals("item")){
					System.out.println("  You have picked up and stored the item.");
				}
			}
		}
		
		System.out.println("  Location: ("+location[0]+", "+location[1]+")");
	}
}