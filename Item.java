/*
	ITEMS
	
	Description:
		Creates Item objects (swords, keys, potions).
*/
public class Item extends Entity{
	
		public Item(String description){
			// All Item objects will be of type "item".
			super("item",description);
		}
}