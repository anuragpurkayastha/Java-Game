/*
	MAIN GAME FILE
*/
import java.util.ArrayList;
import java.util.Arrays;

public class HauntedHouseGame{
	
	public static void main(String[] args){
		
		// Variables
		final int max_x = 5;	// Max x-coordinate
		final int max_y = 5;	// Max y-coordinate
		Tile[][] houseMap = new Tile[max_x][max_y];	// The houseMap which is made of Tile objects
		
		// Initialise the houseMap with Tile objects.
		// Loop through each index of the 2-D array and create a new Tile object with the location of each corresponding to the current index.
		for(int i=0; i < max_x; i++){
			for(int j=0; j < max_y; j++){
				houseMap[i][j] = new Tile(i,j);
			}
		}
		
		/*
			GAME SETUP
		*/
		// Set the second row of the map (y=1) as valid tiles apart from the tile at (x=2,y=1) (hallway).
		for(int i=0; i < max_x; i++){
			// If the index is x=2, then skip
			if(i == 2){
				continue;
			}
			
			// Otherwise set the tile as invalid
			houseMap[i][1].setIsValidGameTile(false);
		}
		
		// Set the fourth row of the map (y=3) as valid tiles apart from the tile at (x=2,y=3) (hallway).
		for(int i=0; i < max_x; i++){
			// If the index is x=2, then skip
			if(i == 2){
				continue;
			}
			
			// Otherwise set the tile as invalid
			houseMap[i][3].setIsValidGameTile(false);
		}
		
		/*
			DEBUG - CHECK VALIDITY OF TILES ON A PARTICULAR ROW
		*/
		for(int i=0; i < max_x; i++){
			int y = 4;
			System.out.println("Tile at location :("+houseMap[i][y].getLocation()[0]+" ,"+houseMap[i][y].getLocation()[1]+") is isValid: "+houseMap[i][y].isValidGameTile());
		}
		
		//	Create a player
		Player gamePlayer = new Player("Anurag");
		
		//	Add the player to the tile corresponding to the players location
		Tile playerTile = houseMap[gamePlayer.getLocation()[0]][gamePlayer.getLocation()[1]];
		playerTile.addEntity(gamePlayer);
		
		// Print the entities at the tile
		Entity[] tileEntities = playerTile.getEntities();
		
		System.out.println(tileEntities[0].getDescription());
		
	}
}