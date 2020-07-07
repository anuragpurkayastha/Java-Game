/*
	ITEMS

	Name:	Anurag Purkayastha
	Student ID:	s3805894
	
	Description:
		Creates Item objects (swords, keys, potions, lamps).
*/
public class Item extends Entity{

		public Item(String description){
			
			// All Item objects will be of type "item".
			super("item",description);
		}
}
