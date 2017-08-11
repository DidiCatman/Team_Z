package entities.player;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import entities.Entity;
import main.Handler;
import main.Translations;

public class Player extends Entity implements Translations{
	
	private String heroname;
	private int id;
	private PlayerActions actions;

	public Player(Handler handler, int tilex, int tiley, int maxHealth, int hero, String name, int id, BufferedImage image) {
		super(handler, tilex, tiley, maxHealth, image);
		this.heroname = name;
		this.id = id;
		actions = new PlayerActions(handler);
	}

	@Override
	public void tick() {
		actions.tick();
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
		
		actions.render(g);
	}

	//GETTERS & SETTERS
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
