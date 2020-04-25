/*
	MAIN GAME FILE
*/

/*	Import packages	*/
import java.util.ArrayList;	// Array list
import java.util.Scanner;	// Scanner for user input
import java.util.concurrent.ThreadLocalRandom;	// Random number generator

public class HauntedHouseGame{

	// Method to print the game menu with the options listed.
	public void printGameMenu(){
		System.out.println("\n  What is your next move?");
		System.out.println("  1. Move north");
		System.out.println("  2. Move east");
		System.out.println("  3. Move south");
		System.out.println("  4. Move west");
		System.out.println("  5. Use potion");
		System.out.println("  6. Fight");
		System.out.println("  7. Show Map");
		System.out.println("  8. Quit");
	}

	// Method to print the game houseMap and show Player location.
	public void printGameMap(Tile[][] map, Player player){

		//	Get the player Location
		int[] playerLocation = player.getLocation();

		System.out.println("\n  House Map\n");
		System.out.print("  Legend:       1 - Game tile\n		0 - Empty tile\n		X - Current location\n"
						+"      		W - Destination\n");
		/*	Loop through each row of the 2D matrix and print a "1" if the tile
			is a valid game tile, 0 otherwise. */
			System.out.println("");
			System.out.print(" ");

		// Loop through each row
		for(int i = (map.length - 1); i >= 0; i--){

			// Loop through each column
			for(int j = 0; j < map[i].length; j++){

				// If at player location, then print X
				if(i == playerLocation[0] && j == playerLocation[1]){
					System.out.print(" X ");
				}
				else if(i == (map.length-1) && j == (map[i].length - 1)){
					System.out.print(" W ");
				}
				else{
					// If the tile at index map[i][j] is valid then print "1", else "0".
					if(map[i][j].isValidGameTile()){
						System.out.print(" 1 ");
					}
					else{
						System.out.print(" 0 ");
					}
				}
			}
			System.out.print("\n ");
		}
	}

	/*=======================================================================================================
					MAIN METHOD
	=========================================================================================================*/
	public static void main(String[] args){

		/*-----------------		Variables		------------------------------------------------------------*/
		final int max_row = 5;	// 	Max row
		final int max_col = 5;	// 	Max col
		Tile[][] houseMap = new Tile[max_row][max_col];	// 	The houseMap which is made of Tile objects
		Scanner userInput = new Scanner(System.in);		// 	Scanner to read user input
		HauntedHouseGame game = new HauntedHouseGame();	// 	Create an instance of the game.
		int userOption = 0;			// 	Variable to hold the userOption from the list of options in the menu
		boolean quitGame = false;	// 	Has the user chosen to quit game?
		int playerLocationRow;		// 	row of player's current location
		int playerLocationCol;		// 	column of player's current location
		Player mainCharacter = new Player();	// 	Create a player
		Monster currentMonster = null;			// 	Monster in the current tile (if any).
		int playerRandomNumber;		// 	Random number to use in fights
		int monsterRandomNumber;	// 	Random number to use in fights
		int[] tileToMoveTo;			// 	Tile to move to when moving.
		int currentTileCol;			//	Column co-ordinate of current tile
		int currentTileRow;			//	Row co-ordinate of current tile
		int roundNumber = 1;			//	Round number used in fights

		/*#######################	GAME SETUP	#######################################################	*/
		// Initialise the houseMap with Tile objects.
		// Loop through each index of the 2-D array and create a new Tile object with the location of each corresponding to the current index.
		
		// Loop through rows
		for(int i=0; i < max_row; i++){
			
			// Loop through the columns in the row.
			for(int j=0; j < max_col; j++){
				
				// Create a new tile at position (row, column).
				houseMap[i][j] = new Tile(i,j);
				
			}
		}

		// Set the second row of the map (row = 1) as invalid tiles apart from the tile at (row = 1, col = 2) (hallway).
		for(int i=0; i < max_col; i++){
			
			// If the column = 2, then skip, as it needs to be a valid game tile.
			if(i == 2){
				continue;
			}

			// Otherwise set the tile as invalid
			houseMap[1][i].setInvalidGameTile();
		}

		// Set the fourth row of the map (row = 3) as invalid tiles apart from the tile at (row = 2,col = 3) (hallway).
		for(int i = 0; i < max_col; i++){
			
			// If the index is col =2, then skip
			if(i == 2){
				continue;
			}

			// Otherwise set the tile as invalid
			houseMap[3][i].setInvalidGameTile();
		}

		// Add an item or monster to the specified tiles according to the game map.

		//		ITEMS
		houseMap[0][0].addEntity(new Item("potion"));
		houseMap[0][4].addEntity(new Item("lamp"));
		houseMap[2][0].addEntity(new Item("sword"));
		houseMap[2][4].addEntity(new Item("key"));
		houseMap[4][0].addEntity(new Item("potion"));
		
		//		MONSTERS
		houseMap[0][1].addEntity(new Monster("ghost", 50, 25, 6));		//	Row 0
		houseMap[0][3].addEntity(new Monster("zombie", 50, 25, 6));
		houseMap[2][1].addEntity(new Monster("skeleton", 75, 30, 7));	// 	Row 2
		houseMap[2][3].addEntity(new Monster("vampire", 75, 30, 7));
		houseMap[2][3].addEntity(new Monster("vampire", 100, 35, 8));	//	Row 4
		houseMap[2][3].addEntity(new Monster("vampire", 100, 35, 8));	//	Row 4
		/*#######################	End Game Setup	#######################################################	*/

		//	Intro to the game
		System.out.println("\n		<<	THE HAUNTED HOUSE	>>");
		System.out.println("\n  After spending the afternoon exploring a haunted house with some friends, you find yourself trapped inside."
						+" You hear weird noises throughout the house. Your mission is to find the way out of the house to safety."
						+" You may encounter monsters lurking throughout the house on your way to the exit. Make sure to utilise any items"
						+" you might come across to help you defeat the monsters. Good luck!\n");

		// Play the game while user is still alive and user has not chosen to quit the game.
		while(mainCharacter.isAlive() && !quitGame){

			playerLocationRow = mainCharacter.getLocation()[0];
			playerLocationCol = mainCharacter.getLocation()[1];

			Tile currentTile = houseMap[playerLocationRow][playerLocationCol];

			// Print a description of the current tile
			currentTile.printDescription();

			// Get the objects or monsters located in the current tile
			Entity[] currentEntities = currentTile.getEntities();

			// If the currentTile has an entity, then process it.
			if(currentEntities.length > 0){

				// If the object in the current tile is an item, then pick up the item and store it.
				if(currentEntities[0].getType().equals("item")){
					mainCharacter.addItem((Item) currentEntities[0]);
					currentTile.removeEntity(currentEntities[0]);	// Remove the item from the tile.
				}
				// Else if the entity object is a monster then cast it as a monster and set the currentMonster variable.
				else if(currentEntities[0].getType().equals("monster")){
					currentMonster = (Monster) currentEntities[0];
				}
			}

			System.out.println("\n  Your current statistics are: ");
			mainCharacter.getStats();

			game.printGameMenu();

			System.out.print("\n  Please choose a number from the options above: ");

			try{
				userOption = userInput.nextInt();
				System.out.println("");
			}
			catch (Exception e){
				System.out.println("\n  Oops! You did not type in a valid number. Please enter an integer.");
				userInput.next();
				System.out.println("==================================================================");
				continue;
			}

			if(userOption > 8 || userOption < 1){
				System.out.println("\n  Oops! You chose an option that doesn't exist! Please try again.");
			}

			// Switch statement to decide next game step based on user option.
			switch(userOption){
				// 1. Move north
				case 1:
					// If there is a monster don't move
					if(currentMonster != null){
						System.out.println("  There is a monster! You have to defeat it!");
					}
					// Else move north. (Same for other movement options).
					else{
						System.out.println("  You have chosen to move north!");
						
						// Get the row and column of the current tile.
						currentTileRow = currentTile.getLocation()[0];
						currentTileCol = currentTile.getLocation()[1];
						
						// First check if the tile is out of bounds and indices are valid. Since moving north, just check row.
						if(((currentTileRow + 1) < max_row) && houseMap[currentTileRow + 1][currentTileCol].isValidGameTile()){
							mainCharacter.setLocation(currentTileRow + 1, currentTileCol);
						}
						else{
							System.out.println("\n  You can't move there!");
						}
					}
					break;
				// 2. Move east
				case 2:
					if(currentMonster != null){
						System.out.println("  There is a monster! You have to defeat it!");
					}
					else{
						System.out.println("  You have chosen to move east!");
						
						// Get the row and column of the current tile.
						currentTileRow = currentTile.getLocation()[0];
						currentTileCol = currentTile.getLocation()[1];
						
						// First check if the tile is out of bounds and indices are valid. Since moving east, just check column.
						if(((currentTileCol + 1) < max_col) && houseMap[currentTileRow][currentTileCol + 1].isValidGameTile()){
							mainCharacter.setLocation(currentTileRow, currentTileCol + 1);
						}
						else{
							System.out.println("\n  You can't move there!");
						}
					}
					break;
				// 3. Move south
				case 3:
					if(currentMonster != null){
						System.out.println("  There is a monster! You have to defeat it!");
					}
					else{
						System.out.println("  You have chosen to move south!");
						
						// Get the row and column of the current tile.
						currentTileRow = currentTile.getLocation()[0];
						currentTileCol = currentTile.getLocation()[1];
						
						// First check if the tile is out of bounds and indices are valid. Since moving south, just check row.
						if(((currentTileRow - 1) >= 0) && houseMap[currentTileRow - 1][currentTileCol].isValidGameTile()){
							mainCharacter.setLocation(currentTileRow - 1, currentTileCol);
						}
						else{
							System.out.println("\n  You can't move there!");
						}
					}
					break;
				// 4. Move west
				case 4:
					if(currentMonster != null){
						System.out.println("  There is a monster! You have to defeat it!");
					}
					else{
						System.out.println("  You have chosen to move west!");
						
						// Get the row and column of the current tile.
						currentTileRow = currentTile.getLocation()[0];
						currentTileCol = currentTile.getLocation()[1];
						
						// First check if the tile is out of bounds and indices are valid. Since moving west, just check column.
						if(((currentTileCol - 1) >= 0) && houseMap[currentTileRow][currentTileCol - 1].isValidGameTile()){
							mainCharacter.setLocation(currentTileRow, currentTileCol - 1);
						}
						else{
							System.out.println("\n  You can't move there!");
						}
					}
					break;
				//	Use the potion
				case 5:
					System.out.print("  You have chosen to use potion!");

					// Look through the items character is carrying to see if there is a potion
					if(mainCharacter.hasItem("potion")){
						mainCharacter.setHP(mainCharacter.getHP() + 30);
						System.out.print(" Your health has been restored by 30HP!\n");
						mainCharacter.removeItem("potion");
					}
					else{
						System.out.println("  Sorry, you don't have a potion!");
					}
					break;
				case 6:
					
					if(currentMonster == null){
						System.out.println("  There is no monster here!");
						break;
					}
					System.out.println("  You have chosen to fight!");
					
					// If monster is alive.
					if ( /*mainCharacter.isAlive() &&*/ currentMonster.isAlive()){
						
						System.out.println("\n  ROUND " + roundNumber);
						System.out.println("  Monster's HP: " + currentMonster.getHP());
						System.out.println("  Your HP: " + mainCharacter.getHP());
						
						// Players turn
						playerRandomNumber = ThreadLocalRandom.current().nextInt(10);
						//System.out.println("  Player's random number is: "+playerRandomNumber);
						if(playerRandomNumber < mainCharacter.getMaxDamageThreshold()){
							System.out.println("  You hit the monster with damage of " + mainCharacter.getMaxDamage()+"HP!");
							currentMonster.setHP(currentMonster.getHP() - mainCharacter.getMaxDamage());
						}
						else{
							System.out.println("  You missed!");
						}
						
						// Monster's turn
						monsterRandomNumber = ThreadLocalRandom.current().nextInt(10);
						//System.out.println("  Monster's random number is: "+monsterRandomNumber);
						if(monsterRandomNumber < currentMonster.getMaxDamageThreshold()){
							System.out.println("  Monster hit you with damage of " + currentMonster.getMaxDamage()+"HP!");
							mainCharacter.setHP(mainCharacter.getHP() - currentMonster.getMaxDamage());
						}
						else{
							System.out.println("  Monster missed!");
						}
						
						roundNumber++;
					}
					else{
						currentTile.removeEntity(currentMonster);
						currentMonster = null;
						System.out.println("  You have defeated the monster!");
						roundNumber = 1;
					}
					break;
				case 7:
					System.out.println("  You have chosen to view the map!");
					game.printGameMap(houseMap, mainCharacter);
					break;
				case 8:
					System.out.println("  You have chosen to quit! :(\n");
					System.out.println("  See you next time!");
					quitGame = true;
					break;
				default:
					break;
			}

			System.out.println("==================================================================");
		}
		
		if(!mainCharacter.isAlive()){
			System.out.println("YOU DIED! :(");
		}
		else if(!quitGame){
			System.out.println("\n  CONGRATULATIONS YOU MADE IT OUT ALIVE!!!! :D");
		}
	}
}
