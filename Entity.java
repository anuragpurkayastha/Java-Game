/*
	ENTITY CLASS

	Name:	Anurag Purkayastha
	Student ID:	s3805894
	
	This class implements the monsters and items that are contained in each game tile.
	A game tile has only ONE entity.
*/

//	This is an abstract class which is implemented by the Item and Monster classes.
public abstract class Entity{

	// Entity variables
	private String description;	// Description of entity.
	private String type;		// Type of entity (item or monster).

	/*
		Constructor

		Arguments:
			- String type: Type of entity.
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
