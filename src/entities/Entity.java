package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.Settings;

public abstract class Entity implements Settings{
	
	protected Handler handler;
	protected int tilex, tiley;
	@SuppressWarnings("unused")
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
	
}
