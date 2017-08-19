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
	
	public Spawn(Handler handler, int tilex, int tiley){
		this.setHandler(handler);
		active = true;
		this.tilex = tilex;
		this.tiley = tiley;
	}
	
	public void tick(){
		System.out.println("NIY - Spawn enemy zombies");
	}
	
	public void render(Graphics g){
		BufferedImage img = null;
		int x = 0, y = 0;
		if(active){
			img = Assets.zombi_spawn_active;
			x = tilex * TILESIZE + TILESIZE/2 - img.getWidth()/2;
			y = tiley * TILESIZE  + TILESIZE - img.getHeight();
		}
		
		g.drawImage(img, x, y, null);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
