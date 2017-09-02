package entities.zombies;

import java.awt.Graphics;
import java.awt.Point;

import entities.Entity;
import main.Handler;

public class Zombies extends Entity {
	
	private int id, xoffset, yoffset;
	private Type type;
	
	public Zombies(Handler handler, int tilex, int tiley, int id, Type type){
		super(handler, tilex, tiley, type.getHealth(), type.getSprite());
		this.type = type;
		this.id = id;
		xoffset = 0;
		yoffset = 16 + type.getId() * 16;
	}

	public void tick(){		
	}
	
	public void move(){
		System.out.println("NIY - move zombie #" + (id + 1));
	}

	public void render(Graphics g){	
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		g.drawImage(image, tilex * TILESIZE + xoff + xoffset, tiley * TILESIZE + yoff + yoffset, null);
	}
	
	public Type getType(){
		return type;
	}
	
	public int getID(){
		return id;
	}
	
	public Point getTile(){
		return new Point(tilex,tiley);
	}
	
	public void setXoffset(int xoffset) {
		this.xoffset = xoffset;
	}
}