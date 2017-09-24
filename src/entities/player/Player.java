package entities.player;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Entity;
import entities.items.Item;
import entities.player.actions.PlayerActions;
import gfx.Animation;
import gfx.Assets;
import main.Handler;
import main.Translations;

public class Player extends Entity implements Translations{
	
	private String heroname;
	private int id;
	private int xoffset;
	private int actionCounter;
	private PlayerActions actions;
	private ArrayList<Item> items;
	private Animation activeHeroAnimation;

	public Player(Handler handler, int tilex, int tiley, int maxHealth, int hero, String name, int id, BufferedImage image) {
		super(handler, tilex, tiley, maxHealth, image);
		this.heroname = name;
		this.id = id;
		this.actions = new PlayerActions(handler);
		this.actionCounter = DEFAULT_ACTIONS;
		this.xoffset = id * 12;
		this.items = new ArrayList<Item>();
		items.add(new Item(0, Assets.sword, "Sword", 0, 1, 70, 1));
		items.add(new Item(1, Assets.bow, "Bow", 1, 1, 75, 1));
		items.add(new Item(2, Assets.axe, "Axe", 0, 1, 70, 2));
		items.add(new Item(3, Assets.magic_scroll, "Scroll", 1, 1, 70, 1));
		activeHeroAnimation = new Animation(300, Assets.activeHeroAnims[id]);
	}

	@Override
	public void tick() {
		actions.tick();
		activeHeroAnimation.tick();
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
		g.drawImage(activeHeroAnimation.getCurrentFrame(), tilex * TILESIZE + xoff + TILESIZE/2 - activeHeroAnimation.getCurrentFrame().getWidth()/2, tiley * TILESIZE + yoff + TILESIZE/2 - activeHeroAnimation.getCurrentFrame().getHeight()/2, activeHeroAnimation.getCurrentFrame().getWidth(), activeHeroAnimation.getCurrentFrame().getHeight(), null);
		
		actions.render(g);
	}
	
	public void decreaseActionPoints(){
		actionCounter--;
		if(actionCounter <= 0){
			handler.getGame().getGameState().endTurn();
		}
	}
	
	public void addItemToInventory(Item i){
		if(items.size() < MAXITEMS){
			items.add(i);
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

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
