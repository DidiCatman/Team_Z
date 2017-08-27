package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.player.Player;
import main.Handler;

public class EntityManager {
	
	private Handler handler;
	private ArrayList<Player> players;

	public EntityManager(Handler handler){
		this.handler = handler;
		players = new ArrayList<Player>();
	}
	
	public void tick(){
		for(Player p: players){
			p.tick();
		}
	}
	
	/*
	 * render methods
	 */
	public void render(Graphics g){
		renderPlayers(g);
	}
	
	private void renderPlayers(Graphics g) {		
		//set xoffset to all players
		for(int i = 0; i < players.size(); i++){
			int count = 0;
			//render active player
			if(players.get(i).equals(handler.getGame().getGameState().getTurnPlayer())){
				players.get(i).setXoffset(0);
				break;
			}
			for(int x = 0; x < i; x++){
				if(players.get(x).getTile().equals(players.get(i).getTile())){
					count++;
				}
			}
			players.get(i).setXoffset(count * 16);
		}
		
		//render players
		for(Player p: players){
			if(p.equals(handler.getGame().getGameState().getTurnPlayer())){
				p.renderActive(g);
			}else{
				p.renderWithOffset(g);
			}
		}
	}

	public void addPlayer(Player player){
		players.add(player);
	}

	/*
	 * GETTERS & SETTERS
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
