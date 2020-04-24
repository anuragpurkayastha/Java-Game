/*
	ITEMS
	
	Description:
		Creates Item objects (swords, keys, potions).
*/
public class Item extends Entity{
	
		private int healHP;
		
		public Item(String description){
			// All Item objects will be of type "item".
			super("item",description);
		}
	
		public Item(String type,String description, int healHP){
			super(type,description);
			this.healHP = healHP;
		}
		
		public int getHealHP(){
			return healHP;
		}
}