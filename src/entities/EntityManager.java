package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.player.Player;
import entities.Zombies;
import main.Handler;
import main.Settings;

public class EntityManager implements Settings {
	
	private Handler handler;
	private ArrayList<Player> players;
	private ArrayList<Zombies> zombies;

	public EntityManager(Handler handler){
		this.handler = handler;
		players = new ArrayList<Player>();
		zombies = new ArrayList<Zombies>();
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
		renderZombies(g);
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
	
	private void renderZombies(Graphics g) {
		for(int i = 0; i < zombies.size(); i++){
			int count = 0;
			for(int x = 0; x < i; x++){
				if(zombies.get(x).getTile().equals(zombies.get(i).getTile())){
					count++;
				}
			}
				zombies.get(i).setXoffset(count * 16);
		}
		
		for(Zombies z: zombies){
			z.render(g);
		}
	}

	public void addPlayer(Player player){
		players.add(player);
	}
	
	public void addZombies(Zombies zombie){
		zombies.add(zombie);
	}

	/*
	 * GETTERS & SETTERS
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Zombies> getZombies() {
		return zombies;
	}
}
