package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import entities.zombies.Type;
import entities.zombies.Zombies;
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
			int type_id = z.getType().getId();
			int number[] = countZombies(z.getTilex(),z.getTiley());		
			temparray.add(z);
			//set xoffset to all zombies on same tile if less than 6 zombies
			if(number[type_id] <= 5){
				for(Zombies t: temparray){
					z.setXoffset(count * 16);
					if(t.getTile().equals(z.getTile()) && t.getType().getId() == type_id){
						count++;
					}
				}
			//otherwise set xoffset to 0 and render number of zombies next to zombie
			}else{
				Text.drawString(g, String.valueOf(number[type_id]), z.getTilex() * TILESIZE + xoff + 16, z.getTiley() * TILESIZE + yoff + 30 + type_id * 16, false, Color.BLACK, Assets.font18);
				z.setXoffset(0);
			}
		}
		//render zombies
		for(Zombies z: zombies){
			z.render(g);
		}
	}
	
	//count the number of zombies on tile (return number for different types as integer array)
	public int[] countZombies(int tilex, int tiley){
		int[] number = new int[Type.getType().length];
		for(Zombies z: zombies){
			if (z.getTilex() == tilex && z.getTiley() == tiley){
				number[z.getType().getId()]++;
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
