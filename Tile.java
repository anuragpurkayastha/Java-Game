/*
	TILE CLASS
	
	Description:
	This class implements a Tile object. A Tile object represents one grid on the MAP.
	A tile contains at most one ENTITY (enemy, item) at start of game.
*/

// Import packages
import java.util.ArrayList;	// To implement the ArrayList
import java.util.Iterator;

public class Tile{
	
	// Variables
	private int[] location = new int[2];	// Location of the Tile in [x,y] format.
	private ArrayList<Entity> entities = new ArrayList<Entity>();	// ArrayList to contain the objects located in the tile.
	private Entity ent;	// Entity (item, monster or player).
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
		
		//	If the entities list is empty then print a message notifying the user and return an empty array.
		if (entities.isEmpty()){
			System.out.println("There are no entities of interest!");
			return new Entity[]{};
		}
		
		// Otherwise, if the tile has entities then print convert each object from the ArrayList to Entity class.
		Entity[] entityList=new Entity[entities.size()];
		int i=0;
		
		//	Iterate through the entities.
		Iterator itr = entities.iterator();
		
		while(itr.hasNext()){
			entityList[i] = (Entity) itr.next();
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
}