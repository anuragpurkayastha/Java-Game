/*
	MONSTER CLASS
*/

public class Monster extends Entity{
	
	// Class variables
	private int HP;	// Monster's HP
	private int maxDamage;	// Maximum damage monster can inflict on player
	
	/*	Constructor
		Arguments:
			- description - e.g "zombie", "skeleton", "ghost"
			- HP - HP of monster
			- maxDamage - maximum damage that can be inflicted by monster
	*/
	public Monster(String description, int HP, int maxDamage){
		super("monster", description);
		this.HP = HP;
		this.maxDamage = maxDamage;
	}
	
	//	Set the HP of monster
	public void setHP(int newHPval){
		HP = newHPval;
	}
	
	//	Get the HP of monster
	public int getHP(){
		return HP;
	}
	
	//	Check to see if monster is alive (HP greater than 0).
	public boolean isAlive(){
		return (HP > 0);
	}
}