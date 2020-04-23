/*
	ENTITY CLASS
	
	This class implements the monsters and objects that are contained in each game tile.
	A game tile only has ONE entity.
*/

public abstract class Entity{
	
	// Entity variables
	private String description;	// Description of entity.
	private String type;		// Type of entity (item, player or monster).
	
	/*
		Constructor
		
		Arguments:
			- String description: Description of the entity.
	*/
	public Entity(String type, String description){
		this.type = type;
		this.description = description;
	}
	
	//	Get the description of the Entity
	public String getDescription(){
		return description;
	}
	
	//	Get the type of the item
	public String getType(){
		return type;
	}
}