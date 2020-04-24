/*
	MAIN GAME FILE
*/

/*	Import packages	*/
import java.util.ArrayList;	// Array list
import java.util.Scanner;	// Scanner for user input

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
			for(int j = 0; j < map[0].length; j++){

				// If at player location, then print X
				if(i == playerLocation[1] && j == playerLocation[0]){
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

	public static void main(String[] args){

		// Variables
		final int max_x = 5;	// Max x-coordinate
		final int max_y = 5;	// Max y-coordinate
		Tile[][] houseMap = new Tile[max_x][max_y];	// The houseMap which is made of Tile objects
		Scanner userInput = new Scanner(System.in);	// Scanner to read user input
		HauntedHouseGame game = new HauntedHouseGame();	// Create an instance of the game.
		int userOption = 0;		// Variable to hold the userOption from the list of options in the menu
		boolean quitGame = false;	// Has the user chosen to quit game?
		int playerLocationX;	// X-coordinate of player's location
		int playerLocationY;	// Y-coordinate of player's location
		Player mainCharacter = new Player();	// Create a player

		/*#######################	GAME SETUP	#######################################################	*/
		// Initialise the houseMap with Tile objects.
		// Loop through each index of the 2-D array and create a new Tile object with the location of each corresponding to the current index.
		for(int i=0; i < max_x; i++){
			for(int j=0; j < max_y; j++){
				houseMap[i][j] = new Tile(i,j);
			}
		}

		// Set the second row of the map (y=1) as valid tiles apart from the tile at (x=2,y=1) (hallway).
		for(int i=0; i < max_x; i++){
			// If the index is x=2, then skip
			if(i == 2){
				continue;
			}

			// Otherwise set the tile as invalid
			houseMap[1][i].setIsValidGameTile(false);
		}

		// Set the fourth row of the map (y=3) as valid tiles apart from the tile at (x=2,y=3) (hallway).
		for(int i=0; i < max_x; i++){
			// If the index is x=2, then skip
			if(i == 2){
				continue;
			}

			// Otherwise set the tile as invalid
			houseMap[3][i].setIsValidGameTile(false);
		}

		// Add an item or monster to the specified tiles according to the game map.

		//		ITEMS
		houseMap[0][0].addEntity(new Item("item","potion"));
		houseMap[4][0].addEntity(new Item("item","lamp"));
		houseMap[0][2].addEntity(new Item("item","sword"));
		houseMap[4][2].addEntity(new Item("item","key"));
		houseMap[0][4].addEntity(new Item("item","potion"));
		/*#######################	End Game Setup	#######################################################	*/

		//	Intro to the game
		System.out.println("\n		<<	THE HAUNTED HOUSE	>>");
		System.out.println("\n  After spending the afternoon exploring a haunted house with some friends, you find yourself trapped inside."
						+" You hear weird noises throughout the house. Your mission is to find the way out of the house to safety."
						+" You may encounter monsters lurking throughout the house on your way to the exit. Make sure to utilise any items"
						+" you might come across to help you defeat the monsters. Good luck!\n");

		// Play the game while user is still alive
		while(mainCharacter.isAlive() && !quitGame){

			playerLocationX = mainCharacter.getLocation()[0];
			playerLocationY = mainCharacter.getLocation()[1];

			Tile currentTile = houseMap[playerLocationX][playerLocationY];

			// Print a description of the current tile
			currentTile.printDescription();

			// Get the objects or monsters located in the current tile
			Entity[] currentEntities = currentTile.getEntities();

			if(currentEntities.length >0){

				// If the object in the current tile is an item, then pick up the item and store it.
				if(currentEntities[0].getType().equals("item")){
					mainCharacter.addItem((Item) currentEntities[0]);
				}
			}

			System.out.println("\n  Your current statistics are: ");
			mainCharacter.getCurrentStats();

			game.printGameMenu();

			System.out.print("\n  Please choose a number from the options above: ");

			try{
				userOption = userInput.nextInt();
				System.out.println("");
			}
			catch (Exception e){
				System.out.println("\n  Oops! You did not type in a valid number. Please enter an integer.");
				userInput.next();
			}

			if(userOption > 7 || userOption < 1){
				System.out.println("\n  Oops! You chose an option that doesn't exist! Please try again.");
			}

			// Switch statement to decide next game step based on user option.
			switch(userOption){
				case 1:
					System.out.println("  You have chosen to move north!");
					break;
				case 2:
					System.out.println("  You have chosen to move east!");
					break;
				case 3:
					System.out.println("  You have chosen to move south!");
					break;
				case 4:
					System.out.println("  You have chosen to move west!");
					break;
				case 5:
					System.out.println("  You have chosen to use potion!");

					// Look through the items character is carrying to see if there is a potion
					if(mainCharacter.hasItem("potion")){
						mainCharacter.setHP(mainCharacter.getCurrentHP() + 30);
					}
					else{
						System.out.println("  Sorry, you don't have a potion!");
					}
					break;
				case 6:
					System.out.println("  You have chosen to fight!");
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

			System.out.println("------------------------------------------------------------------");
		}

		//	Place the character at location (2,0) at the start of the game.
		//mainCharacter.move(2,0);

		/*	Add the player to the tile corresponding to the players location
		Tile playerTile = houseMap[gamePlayer.getLocation()[0]][gamePlayer.getLocation()[1]];
		playerTile.addEntity(gamePlayer);*/

	}
}
