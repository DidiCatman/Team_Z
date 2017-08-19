package entities.player;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import entities.Entity;
import entities.player.actions.PlayerActions;
import main.Handler;
import main.Translations;

public class Player extends Entity implements Translations{
	
	private String heroname;
	private int id;
	private int xoffset;
	private int actionCounter;
	private PlayerActions actions;

	public Player(Handler handler, int tilex, int tiley, int maxHealth, int hero, String name, int id, BufferedImage image) {
		super(handler, tilex, tiley, maxHealth, image);
		this.heroname = name;
		this.id = id;
		actions = new PlayerActions(handler);
		actionCounter = DEFAULT_ACTIONS;
		xoffset = id * 16;
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
	
	public void renderWithOffset(Graphics g) {
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		g.drawImage(image, tilex * TILESIZE + xoff + xoffset, tiley * TILESIZE + yoff, null);
	}
	
	public void renderActive(Graphics g) {
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		g.drawImage(image, tilex * TILESIZE + xoff + TILESIZE/2 - image.getWidth(), tiley * TILESIZE + yoff + TILESIZE/2 - image.getHeight(), image.getWidth() * 2, image.getHeight() * 2, null);
		
		actions.render(g);
	}
	
	public void decreaseActionPoints(){
		actionCounter--;
		if(actionCounter <= 0){
			handler.getGame().getGameState().endTurn();
		}
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

	public int getActionCounter() {
		return actionCounter;
	}

	public void setActionCounter(int actionCounter) {
		this.actionCounter = actionCounter;
	}

	public void setXoffset(int xoffset) {
		this.xoffset = xoffset;
	}

}
