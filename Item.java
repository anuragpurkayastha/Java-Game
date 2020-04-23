/*
	ITEMS
	
	Description:
		Creates Item objects (swords, keys, potions).
*/
public class Item extends Entity{
	
		private int healHP;
		
		public Item(String type,String description){
			super(type,description);
		}
	
		public Item(String type,String description, int healHP){
			super(type,description);
			this.healHP = healHP;
		}
		
		public int getHealHP(){
			return healHP;
		}
}