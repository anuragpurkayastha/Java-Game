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
	private boolean isValidTile;	// Is the tile a valid tile through which the player can go? Default is yes.

	/*	Constructor

		Arguments:
			- int row: row of the tile
			- int col: column of the tile	*/
	public Tile(int row,int col){

		// Initialise the Tile object with the initial co-ordinates.
		location[0] = row;
		location[1] = col;
		isValidTile = true;	//	Tile is valid by default.
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

		// Otherwise, if the tile has entities then convert each object from the ArrayList to Entity class.
		Entity[] entityList=new Entity[entities.size()];
		int i=0;	// Index of current entity object in entityList array.

		//	Iterate through the entities.
		Iterator itr = entities.iterator();

		//	While there is a next entity in the entities array list, then convert each one into an Entity object
		//	and add it to the entityList array.
		while(itr.hasNext()){
			entityList[i] = (Entity) itr.next();
			i++;	// Next index of entityList
		}

		return entityList;
	}

	// Return the co-ordinates of the Tile in (row,col) notation.
	public int[] getLocation(){
		return location;
	}


	// Return to check if the tile is a valid game tile
	public boolean isValidGameTile(){
		return isValidTile;
	}

	// Set tile to invalid
	public void setInvalidGameTile(){
		isValidTile = false;
	}

	//	Print a description of the tile
	public void printDescription(){

		// tileDescription
		System.out.println("  DESCRIPTION OF CURRENT LOCATION");

		Entity[] entities = this.getEntities();	// Get entities in the tile
		String[] entityTypes = new String[entities.length]; // Construct an array of Strings with each element being "<entityType>:<entity_description>"

		//	If there are no entities
		if(entities.length == 0){
			System.out.println("  There is nothing of interest here!");
		}

		// Populate the string array
		for(int i=0; i < entityTypes.length; i++){
			System.out.print("  You have come across a "+entities[i].getDescription()+" "+entities[i].getType()+"!\n");

			if(entities[i].getType().equals("item")){
				System.out.println("  You have picked up and stored the item.");
			}
		}
	}
}
