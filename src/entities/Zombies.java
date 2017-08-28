package entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import entities.Entity;
import main.Handler;

public class Zombies extends Entity {
	
	private int id, xoffset;
	
	public Zombies(Handler handler, int tilex, int tiley, int maxHealth, int id, BufferedImage image) {
		super(handler, tilex, tiley, maxHealth, image);
		this.id = id;
		xoffset = id * 16;
	}

	public void tick() {		
	}

	public void render(Graphics g) {	
		int xoff = handler.getWorld().getMap_x_offset();
		g.drawImage(image, tilex * TILESIZE + xoff + xoffset, tiley * TILESIZE, null);
	}
	
	public int getID() {
		return id;
	}
	
	public Point getTile(){
		return new Point(tilex,tiley);
	}
	
	public void setXoffset(int xoffset) {
		this.xoffset = xoffset;
	}
}