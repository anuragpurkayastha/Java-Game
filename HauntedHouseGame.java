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

	//	Method to print the game houseMap and show Player location.
	//	Arguments: the map array object and the Player object.
	public void printGameMap(Tile[][] map, Player player){

		//	Get the player location in [row,col] format.
		int[] playerLocation = player.getLocation();

		//	Map title
		System.out.println("\n  House Map\n");
		//	Legend
		System.out.print("  Legend:       1 - Game tile\n		0 - Empty tile\n		X - Current location\n"
						+"      		W - Destination\n");

		/*	Loop through each row of the 2D matrix and print a "1" if the tile
			is a valid game tile, 0 otherwise. */
			System.out.println("");
			System.out.print(" ");

		// Loop through each row. Since the map is such that the bottom row is the first element,
		// start from the array at the last index of the 2D array.
		for(int i = (map.length - 1); i >= 0; i--){

			// Loop through each column
			for(int j = 0; j < map[i].length; j++){

				// If at player location, then print X
				if(i == playerLocation[0] && j == playerLocation[1]){
					System.out.print(" X ");
				}
				//	Else if at the "exit" location (which is the last element), then print "W".
				else if(i == (map.length-1) && j == (map[i].length - 1)){
					System.out.print(" W ");
				}
				//	Else if at other locations, print a "1" or "0"
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

	//	Method to prompt for user to press Enter to continue
	public void promptEnterToContinue(){
		//	Press enter to continue
		System.out.print("\n  Press \"ENTER\" to continue...");
		try{
			System.in.read();
		}
		catch(Exception e){
		}
	}

	/*=======================================================================================================
					MAIN METHOD
	=========================================================================================================*/
	public static void main(String[] args){

		/*-----------------		Variables		------------------------------------------------------------*/
		final int max_row = 5;	// 	Max number of rows
		final int max_col = 5;	// 	Max number of columns
		Tile[][] houseMap = new Tile[max_row][max_col];	// 	The houseMap which is made of Tile objects
		Scanner userInput = new Scanner(System.in);		// 	Scanner to read user input
		HauntedHouseGame game = new HauntedHouseGame();	// 	Create an instance of the game.
		int userOption = 0;			// 	Variable to hold the userOption from the list of options in the menu
		boolean quitGame = false;	// 	Has the user chosen to quit game?
		int playerLocationRow;		// 	row of player's current location
		int playerLocationCol;		// 	column of player's current location
		Player mainCharacter = new Player();	// 	Create a player
		Monster currentMonster = null;			// 	Monster in the current tile (if any).
		int fightRandomNumber;		// 	Random number to use in fights
		int currentTileCol;			//	Column co-ordinate of current tile
		int currentTileRow;			//	Row co-ordinate of current tile
		boolean hasWon = false;		//	Has the player reached the exit?

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

		// Set the fourth row of the map (row = 3) as invalid tiles apart from the tile at (row = 2,col = 2) (hallway).
		for(int i = 0; i < max_col; i++){

			// If the index is col =2, then skip
			if(i == 2){
				continue;
			}

			// Otherwise set the tile as invalid
			houseMap[3][i].setInvalidGameTile();
		}

		// Add an item or monster to the tiles specified according to the game map.

		//	ITEMS (description)								Co-ordinates
		houseMap[0][0].addEntity(new Item("potion"));	//	(0,0)
		houseMap[0][4].addEntity(new Item("lamp"));		//	(0,4)
		houseMap[2][0].addEntity(new Item("sword"));	//	(2,0)
		houseMap[2][4].addEntity(new Item("key"));		//	(2,4)
		houseMap[4][0].addEntity(new Item("potion"));	//	(4,0)

		//	MONSTERS (description, HP, maxDamage, maxDamageThreshold)		Co-ordinates
		houseMap[0][1].addEntity(new Monster("ghost", 50, 25, 6));		//	(0,1)
		houseMap[0][3].addEntity(new Monster("zombie", 50, 25, 6));		//	(0,3)
		houseMap[2][1].addEntity(new Monster("skeleton", 75, 30, 7));	// 	(2,1)
		houseMap[2][3].addEntity(new Monster("vampire", 75, 30, 7));	//	(2,3)
		houseMap[4][1].addEntity(new Monster("ghost", 100, 35, 8));		//	(4,1)
		houseMap[4][3].addEntity(new Monster("zombie", 100, 35, 8));	//	(4,3)
		/*#######################	End Game Setup	#######################################################	*/

		//	Intro to the game
		System.out.println("\n		<<	THE HAUNTED HOUSE	>>");
		System.out.println("\n  After spending the afternoon exploring a haunted house with some friends, you find yourself trapped inside."
						+" You hear weird noises throughout the house. Your mission is to find the way out of the house to safety."
						+" You may encounter monsters lurking throughout the house on your way to the exit. Make sure to utilise any items"
						+" you might come across to help you defeat the monsters. Good luck!\n");

		System.out.println("  A map of the house is shown below. Your objective is to get to the exit marked 'W'. Your current position is shown by the 'X' on the map. Areas marked '1' are areas where you can move to and areas marked '0' are inaccessible. You can view this map again by selecting the appropriate menu item on the game menu.");

		//	Print the game map at the start.
		game.printGameMap(houseMap, mainCharacter);

		System.out.println("");

		game.promptEnterToContinue();

		System.out.println("==================================================================");

		// Play the game while user is still alive, user has not chosen to quit the game and player has not won yet.
		while(mainCharacter.isAlive() && !quitGame && !hasWon){

			//	Get the current location of the player.
			playerLocationRow = mainCharacter.getLocation()[0];
			playerLocationCol = mainCharacter.getLocation()[1];

			//	If the current position of the player is (4,4) then player has won.
			if(playerLocationRow == 4 && playerLocationCol == 4){
				hasWon = true;
			}
			//	Otherwise, continue with the game
			else{
				//	Get the tile at the location where the player is currently at.
				Tile currentTile = houseMap[playerLocationRow][playerLocationCol];

				// Print a description of the current location (tile)
				currentTile.printDescription();

				// Get the objects or monsters located in the current tile
				Entity[] currentEntities = currentTile.getEntities();

				// If the currentTile has an entity, then process it.
				if(currentEntities.length > 0){

					// If the object in the current tile is an item, then pick up the item and store it.
					if(currentEntities[0].getType().equals("item")){
						mainCharacter.addItem((Item) currentEntities[0]);	//	Add item to list of items held
						currentTile.removeEntity(currentEntities[0]);	//	Remove the item from the tile.

						//	Modify player statistics if item is sword or potion.
						switch(currentEntities[0].getDescription()){
							//	If sword, increase maxDamage and maxDamageThreshold
							case "sword":
								System.out.println("\n  Your maximum damage has increased to 75HP!");
								mainCharacter.setMaxDamage(75);
								//	And also increase the maximum damage threshold to 9.
								mainCharacter.setMaxDamageThreshold(9);
								break;
							//	If potion, then print an information message.
							case "potion":
								System.out.println("\n  Use the potion to restore health by 30HP when your health is low!");
								break;
							default:
								break;
						}
					}
					// Else if the entity object is a monster then cast it as a monster and set the currentMonster variable.
					else if(currentEntities[0].getType().equals("monster")){
						currentMonster = (Monster) currentEntities[0];
					}
				}

				//	Print the player's current statistics.
				System.out.println("\n  Your current statistics are: ");
				mainCharacter.getStats();

				//	Print the game menu
				game.printGameMenu();

				System.out.print("\n  Please choose a number from the options above: ");

				//	Get the user's choice.
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
					//	1. Move north
					case 1:
						// If there is a monster don't move. Player must defeat it.
						if(currentMonster != null){
							System.out.println("  There is a monster! You have to defeat it!");
						}
						// Else move north. (Same for other movement options).
						else{
							System.out.println("  You have chosen to move north!");

							// Get the row and column of the current tile.
							currentTileRow = currentTile.getLocation()[0];
							currentTileCol = currentTile.getLocation()[1];

							// First check if the tile to move to is out of bounds and indices are valid. Since moving north, just check row.
							if(((currentTileRow + 1) < max_row) && houseMap[currentTileRow + 1][currentTileCol].isValidGameTile()){

								//	Hallways located at [1,2] and [3,2] require items to pass through. Check each one.

								// Check on column 2.
								if(currentTileCol == 2){
									// 	Switch case statement to check the new row co-ordinate.
									switch(currentTileRow + 1){
										// 	Hallway 1 at (1,2) - requires a lamp
										case 1:
											// 	If the player has the lamp item, then update the location of the player.
											if(mainCharacter.hasItem("lamp")){
												mainCharacter.setLocation(currentTileRow + 1, currentTileCol);
											}
											//	Else display an error message and do nothing.
											else{
												System.out.println("  You don't have a lamp! This dark hallway requires a lamp to get through!");
											}
											break;
										// 	Hallway 2 at (3,2)- requires a key
										case 3:
											// 	If the player has the key item, then update the location of the player.
											if(mainCharacter.hasItem("key")){
												mainCharacter.setLocation(currentTileRow + 1, currentTileCol);
											}
											//	Else display an error message and do nothing.
											else{
												System.out.println("  You don't have a key! This dark hallway requires a key to open the door!");
											}
											break;
										//	Default is to update the location if new tile is not a hallway.
										default:
											mainCharacter.setLocation(currentTileRow + 1, currentTileCol);
											break;
									}
								}
							}
							else{
								System.out.println("\n  You can't move there!");
							}
						}
						break;
					//	2. Move east
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
					//	3. Move south
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
					//	4. Move west
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
					//	5. Use the potion
					case 5:
						System.out.print("  You have chosen to use potion!");

						// 	Look through the items character is carrying to see if there is a potion.
						//	If player has potion, increase player HP by 30 points.
						if(mainCharacter.hasItem("potion")){
							mainCharacter.setHP(mainCharacter.getHP() + 30);
							System.out.print(" Your health has been restored by 30HP!\n");
							//	Remove item from player when it has been used.
							mainCharacter.removeItem("potion");
						}
						else{
							System.out.println("  Sorry, you don't have a potion!");
						}
						break;
					//	6. Fight
					case 6:
						if(currentMonster == null){
							System.out.println("  There is no monster here!");
							break;
						}
						System.out.println("  You have chosen to fight!");

						// If monster is alive, then character has to fight and defeat it.
						if (currentMonster.isAlive()){

							//	Print the HP stats of player and monster
							System.out.println("\n  FIGHT ROUND");
							System.out.println("  Monster's HP: " + currentMonster.getHP());
							System.out.println("  Your HP: " + mainCharacter.getHP());

							// Player's turn
							//	Generate the player's random number.
							fightRandomNumber = ThreadLocalRandom.current().nextInt(10);

							//	If the player's random number is less than their threshold, then player
							//	inflicts maxDamage on monster.
							if(fightRandomNumber < mainCharacter.getMaxDamageThreshold()){
								System.out.println("  You hit the monster with damage of " + mainCharacter.getMaxDamage()+"HP!");
								//	Reduce monster's HP equal to player's maxDamage stat.
								currentMonster.setHP(currentMonster.getHP() - mainCharacter.getMaxDamage());

								// If the player's hit has killed the monster, then remove the monster from the tile and notify the user that they have defeated the monster.
								if(!currentMonster.isAlive()){
									System.out.println("  You have defeated the monster!");
									currentTile.removeEntity(currentMonster);
									currentMonster = null;
								}
							}
							//	Otherwise, if the player's random number is greater than the threshold, then player
							//	has missed.
							else{
								System.out.println("  You missed!");
							}

							// Monster's turn, if it is still alive.
							if(currentMonster != null){
								//	Generate the monster's random number
								fightRandomNumber = ThreadLocalRandom.current().nextInt(10);

								//	Similar method to player's turn.
								if(fightRandomNumber < currentMonster.getMaxDamageThreshold()){
									System.out.println("  Monster hit you with damage of " + currentMonster.getMaxDamage()+"HP!");
									mainCharacter.setHP(mainCharacter.getHP() - currentMonster.getMaxDamage());
								}
								else{
									System.out.println("  Monster missed!");
								}
							}
						}
						break;
					//	7. Show the house map.
					case 7:
						System.out.println("  You have chosen to view the map!");
						game.printGameMap(houseMap, mainCharacter);
						break;
					//	8. Quit Game.
					case 8:
						System.out.println("  You have chosen to quit! :(\n");
						quitGame = true;
						break;
					default:
						break;
				}

				game.promptEnterToContinue();
				System.out.println("==================================================================");
			}
		}

		/*	END GAME STATES 	*/

		//	If player is still alive.
		if(!mainCharacter.isAlive()){
			System.out.println("\n		YOU DIED! BETTER LUCK NEXT TIME!\n");
		}
		//	If player has chosen to quit game
		else if(quitGame){
			System.out.println("  See you next time!\n");
		}
		//	Otherwise, player must have won.
		else{
			System.out.println("\n  CONGRATULATIONS YOU MADE IT OUT ALIVE!!!! :D\n");
		}
	}
}
