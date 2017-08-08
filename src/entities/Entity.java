package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.Settings;

public abstract class Entity implements Settings{
	
	private Handler handler;
	private int tilex, tiley;
	@SuppressWarnings("unused")
	private int health, maxHealth;
	private BufferedImage image;
	
	public Entity(Handler handler, int tilex, int tiley, int maxHealth, BufferedImage image){
		this.handler = handler;
		this.tilex = tilex;
		this.tiley = tiley;
		this.maxHealth = maxHealth;
		health = maxHealth;
		this.image = image;
	}
	
	public abstract void tick();
	
	public void render(Graphics g){
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		g.drawImage(image, tilex * TILESIZE + xoff, tiley * TILESIZE + yoff, null);
	}
	
}
