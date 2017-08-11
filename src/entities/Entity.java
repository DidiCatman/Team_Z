package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.Settings;

public abstract class Entity implements Settings{
	
	protected Handler handler;
	protected int tilex, tiley;
	private int health, maxHealth;
	protected BufferedImage image;
	
	public Entity(Handler handler, int tilex, int tiley, int maxHealth, BufferedImage image){
		this.handler = handler;
		this.tilex = tilex;
		this.tiley = tiley;
		this.maxHealth = maxHealth;
		health = maxHealth;
		this.image = image;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	
	//not abstract
	
	//GETTERS & SETTERS
	public int getTilex() {
		return tilex;
	}

	public void setTilex(int tilex) {
		this.tilex = tilex;
	}

	public int getTiley() {
		return tiley;
	}

	public void setTiley(int tiley) {
		this.tiley = tiley;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	
}
