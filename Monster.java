/*
	MONSTER CLASS
	
	Name:	Anurag Purkayastha
	Student ID:	s3805894
*/

public class Monster extends Entity{
	
	// Class variables
	private int HP;	// Monster's HP
	private int maxDamage;	// Maximum damage monster can inflict on player
	private int maxDamageThreshold;	// Maximum damage threshold
	
	/*	Constructor
		Arguments:
			- description - e.g "zombie", "skeleton", "ghost"
			- HP - HP of monster
			- maxDamage - maximum damage that can be inflicted by monster
			- maxDamageThreshold - the probability of monster inflicting maximum damage.
	*/
	public Monster(String description, int HP, int maxDamage, int maxDamageThreshold){
		super("monster", description);
		this.HP = HP;
		this.maxDamage = maxDamage;
		this.maxDamageThreshold = maxDamageThreshold;
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
	
	//	Get the maxDamageThreshold
	public int getMaxDamageThreshold(){
		return maxDamageThreshold;
	}
	
	//	Get maximum damage HP
	public int getMaxDamage(){
		return maxDamage;
	}
}