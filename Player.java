public class Player extends Entity{
	
	private String playerName;
	private int[] location;
	
	public Player(String name){
		super("character","player");
		this.playerName = name;
		location = new int[] {2,0};
	}
	
	public int[] getLocation(){
		return location;
	}
}