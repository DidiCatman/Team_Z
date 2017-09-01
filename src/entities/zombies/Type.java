package entities.zombies;

import java.awt.image.BufferedImage;

import gfx.Assets;

public class Type {
	
	public static Type[] type = new Type[3];
	public static Type normal = new Type(0, "normal", 1, 1, 1, Assets.zombies[0]);
	public static Type tank = new Type(1, "tank", 2, 1, 1, Assets.zombies[1]);
	public static Type fast = new Type(2, "fast", 1, 2, 1, Assets.zombies[2]);
	
	protected String name;
	protected int id, health, actions, dmg;
	protected BufferedImage sprite;
	
	public Type(int id, String name, int health, int actions, int dmg, BufferedImage sprite){
		this.name = name;
		this.id = id;
		this.health = health;
		this.actions = actions;
		this.dmg = dmg;
		this.sprite = sprite;
		
		type[id] = this;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public static Type[] getType() {
		return type;
	}
	
	public int getHealth() {
		return health;
	}

	public int getActions() {
		return actions;
	}

	public int getDmg() {
		return dmg;
	}
	
	public BufferedImage getSprite(){
		return sprite;
	}
}
