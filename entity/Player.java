package entity;

public class Player {

	private int playerId;
	private String name;
	private String position;
	private String starter; // in lieu of boolean; enter value < 10 char, ie "starter", "backup"


	public Player(int playerId, String name, String position, String starter) {
		this.setPlayerId(playerId);
		this.setName(name);
		this.setPosition(position);
		this.setStarter(starter);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPlayerId() {
		return playerId;
	}


	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getStarter() {
		return starter;
	}


	public void setStarter(String starter) {
		this.starter = starter;
	}
}


