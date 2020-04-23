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
	
	public String getDescription(){
		return description;
	}
}