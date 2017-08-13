package entities;

import java.awt.Graphics;
import java.awt.Point;
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
		//get positions of players
		Point[] positions = new Point[players.size()];
		for(int i = 0; i < players.size(); i++){
			positions[i] = players.get(i).getTile();
		}
		
		//detect same positions
		
		//render all players
		for(Player p: players){
			if(p.equals(handler.getGame().getGameState().getTurnPlayer()))
				continue;
			p.render(g);
		}
		
		//render active player on top in tile middle
		handler.getGame().getGameState().getTurnPlayer().renderActive(g);
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
}
