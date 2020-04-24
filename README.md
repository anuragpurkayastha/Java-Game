#############################################################################
				THE HAUNTED HOUSE GAME
#############################################################################

--------------------------------
	DESCRIPTION
--------------------------------
The player finds themselves stuck in a haunted house after exploring it with some friends. The friends were able to get out but the entrance is locked before the player can get out. The mission is to navigate through the house to find a way out. On the way the player will encounter ghosts, skeletons and zombies which will have to be defeated in order to successfully exit the house, and win the game. Items are placed throughout the house to help in the mission.

--------------------------------
	OBJECTIVE
--------------------------------
Defeat the monsters and find the exit out of the house.

--------------------------------
	HOW TO PLAY
--------------------------------
Run the JAVA file named HauntedHouseGame.java. Text based menu from which you can make choices to make the next move in the game.

--------------------------------
	GAME DESIGN
--------------------------------

MAP DESIGN
----------
The game (house) map consists of a 5 x 5 grid map of the haunted house. The house map locations are denoted by the indices on the x and y axes.

Each grid can contain an ENTITY (object) being PLAYER, ITEM or ENEMY. The player traverses through hallways to get to the next rooms, and eventually the exit.

The house map is made up of TILEs. The housemap will be a 2-dimensional Tile array with each cell containing a TILE object.
The housemap has the form of an array of array objects:
	Tile map[][] = [{tile1, tile2, … , tilen}, … , {tile1, tile2, … , tilen}].
Each element in the parent array represents 1 row in the map.
For example, map[0][i] represents the i-th element in the array in position 0. The array in position 0 of the parent array represents the tiles on the bottom row according to the grid. The bottom-left grid at co-ordinates (0,0) is the element map[0][0].
