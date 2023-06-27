package engine;

import java.util.ArrayList;

import model.world.Champion;

public class Player {
	public void setName(String name) {
		this.name = name;
	}


	public void setTeam(ArrayList<Champion> team) {
		this.team = team;
	}

	private String name;
	private ArrayList<Champion> team;
	private Champion leader;
	
	

	public Player(String name) {
		this.name = name;
		team = new ArrayList<Champion>();
		
	}


	public Champion getLeader() {
		return leader;
	}

	public void setLeader(Champion leader) {
		this.leader = leader;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Champion> getTeam() {
		return team;
	}


}
