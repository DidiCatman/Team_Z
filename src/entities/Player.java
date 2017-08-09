package entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import main.Handler;
import main.Translations;

public class Player extends Entity implements Translations{
	
	private String heroname;
	private int id;

	public Player(Handler handler, int tilex, int tiley, int maxHealth, int hero, String name, int id, BufferedImage image) {
		super(handler, tilex, tiley, maxHealth, image);
		this.heroname = name;
		this.id = id;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void render(Graphics g) {
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		g.drawImage(image, tilex * TILESIZE + xoff, tiley * TILESIZE + yoff, null);
	}
	
	public void renderActive(Graphics g) {
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		g.drawImage(image, tilex * TILESIZE + xoff + TILESIZE/2 - image.getWidth()/2, tiley * TILESIZE + yoff + TILESIZE/2 - image.getHeight()/2, null);
	}

	public String getHeroName() {
		return heroname;
	}

	public void setHeroName(String hero) {
		this.heroname = hero;
	}

	public int getId() {
		return id;
	}
	
	public Point getTile(){
		return new Point(tilex, tiley);
	}

}
