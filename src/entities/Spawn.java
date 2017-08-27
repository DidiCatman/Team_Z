package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Assets;
import main.Handler;
import main.Settings;

public class Spawn implements Settings {
	private Handler handler;
	boolean active;
	private int tilex, tiley;
	private String pos;
	
	public Spawn(Handler handler, int tilex, int tiley, String pos){
		this.setHandler(handler);
		active = true;
		this.tilex = tilex - 1;
		this.tiley = tiley - 1;
		this.pos = pos;
	}
	
	public void tick(){
		System.out.println("NIY - Spawn enemy zombies");
	}
	
	public void render(Graphics g){
		BufferedImage img = null;
		int x = 0, y = 0;
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		if(active){
			if(pos == "north"){
			img = Assets.zombi_spawn_active_hor;
			x = tilex * TILESIZE + xoff + TILESIZE/2 - img.getWidth()/2;
			y = tiley * TILESIZE  + yoff;
			}
			if(pos == "south"){
			img = Assets.zombi_spawn_active_hor;
			x = tilex * TILESIZE + xoff + TILESIZE/2 - img.getWidth()/2;
			y = tiley * TILESIZE  + yoff + TILESIZE - img.getHeight();
			}
			if(pos == "west"){
			img = Assets.zombi_spawn_active_ver;
			x = tilex * TILESIZE + xoff;
			y = tiley * TILESIZE  + yoff + TILESIZE/2 - img.getHeight()/2;
			}
			if(pos == "east"){
			img = Assets.zombi_spawn_active_ver;
			x = tilex * TILESIZE + xoff + TILESIZE - img.getWidth();
			y = tiley * TILESIZE  + yoff + TILESIZE/2 - img.getHeight()/2;
			}
			g.drawImage(img, x, y, null);
		}
	}

	public boolean getSpawnState(){
		return active;
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
