package entities.spawn;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import gfx.Assets;
import main.Handler;
import main.Settings;

public class Spawn implements Settings {
	private Handler handler;
	boolean active;
	private int tilex, tiley;
	private int pos;
	private Random rnd = new Random();
	
	public Spawn(Handler handler, int tilex, int tiley, int pos){
		this.setHandler(handler);
		active = true;
		this.tilex = tilex;
		this.tiley = tiley;
		this.pos = pos;
	}
	
	public void tick(){
	}
	
	public void spawn(){
		int id = rnd.nextInt(3); // rnd selection of zombie id
		int number = rnd.nextInt(3); //rnd selection of number of zombies
		System.out.println("NIY - Spawn " + number + " zombies with id:" + id + " @ x:" + tilex + " y:" + tiley);
		for(int i = 0; i < number; i++){
		handler.getGame().getGameState().addZombies(tilex, tiley, id);
		}
	}
	
	public void render(Graphics g){
		BufferedImage img = null;
		int x = 0, y = 0;
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		if(active){
			//north
			if(pos == 1){
			img = Assets.zombi_spawn_active_hor;
			x = tilex * TILESIZE + xoff + TILESIZE/2 - img.getWidth()/2;
			y = tiley * TILESIZE  + yoff;
			}
			//east
			if(pos == 2){
			img = Assets.zombi_spawn_active_ver;
			x = tilex * TILESIZE + xoff + TILESIZE - img.getWidth();
			y = tiley * TILESIZE  + yoff + TILESIZE/2 - img.getHeight()/2;
			}
			//south
			if(pos == 3){
			img = Assets.zombi_spawn_active_hor;
			x = tilex * TILESIZE + xoff + TILESIZE/2 - img.getWidth()/2;
			y = tiley * TILESIZE  + yoff + TILESIZE - img.getHeight();
			}
			//west
			if(pos == 4){
			img = Assets.zombi_spawn_active_ver;
			x = tilex * TILESIZE + xoff;
			y = tiley * TILESIZE  + yoff + TILESIZE/2 - img.getHeight()/2;
			}
			g.drawImage(img, x, y, null);
		}
	}
	
	public boolean getSpawnState(){
		return active;
	}
	
	public int getTilex(){
		return tilex;
	}
	
	public int getTiley(){
		return tiley;
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
