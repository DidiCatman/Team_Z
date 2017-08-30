package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import entities.Zombies;
import main.Handler;
import main.Settings;

public class EntityManager implements Settings {
	
	private Handler handler;
	private ArrayList<Player> players;
	private ArrayList<Zombies> zombies, temparray;

	public EntityManager(Handler handler){
		this.handler = handler;
		players = new ArrayList<Player>();
		zombies = new ArrayList<Zombies>();
		temparray = new ArrayList<Zombies>();
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
	
	private void renderZombies(Graphics g){
		int xoff = handler.getWorld().getMap_x_offset(), yoff = handler.getWorld().getMap_y_offset();
		temparray.clear();
		for(Zombies z: zombies){
			int count = 0;
			int id = z.getID();
			int number[] = countZombies(z.getTilex(),z.getTiley());		
			temparray.add(z);
			if(number[id] <= 5){
				for(Zombies t: temparray){
					z.setXoffset(count * 16);
					if(t.getTile().equals(z.getTile()) && t.getID() == id){
						count++;
					}
				}
			}else{
				Text.drawString(g, String.valueOf(number[id]), z.getTilex() * TILESIZE + xoff + 16, z.getTiley() * TILESIZE + yoff + 30 + id * 16, false, Color.BLACK, Assets.font18);
				z.setXoffset(0);
			}
		}
		for(Zombies z: zombies){
			z.render(g);
		}
	}
	
	public int[] countZombies(int tilex, int tiley){
		int[] number = new int[3];
		for(Zombies z: zombies){
			if (z.getTilex() == tilex && z.getTiley() == tiley){
				number[z.getID()]++;
			}
		}
		return number;
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
  
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
