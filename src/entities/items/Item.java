package entities.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Assets;

public class Item {
	
	//STATIC STUFF
	public static Item[] items = new Item[4];
	public static Item sword = new Item(0, Assets.sword, "Sword", 0, 1, 70, 1);
	public static Item bow = new Item(1, Assets.bow, "Bow", 1, 1, 75, 1);
	public static Item axe = new Item(2, Assets.axe, "Axe", 0, 1, 70, 2);
	public static Item magic_scroll = new Item(3, Assets.magic_scroll, "Scroll", 1, 1, 70, 1);
	
	//CLASS VARIABLES
	protected final int id;
	protected BufferedImage texture;
	protected String name;
	protected int range, hitEnemiesCount, percentToHit, damage;
	protected int inv_place; // 0 = unset, 1 = leftHand, 2 = body, 3 = rightHand
	
	public Item(int id, BufferedImage img, String name, int range, int hitEnemiesCount, int percentToHit, int damage){
		this.id = id;
		this.texture = img;
		this.name = name;
		this.range = range;
		this.hitEnemiesCount = hitEnemiesCount;
		this.percentToHit = percentToHit;
		this.damage = damage;
		this.inv_place = 0;
		
		items[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void renderTexture(Graphics g, int x, int y, int width, int height){
		g.drawImage(texture, x, y, width, height, null);
	}

	//GETTERS && SETTERS
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static Item[] getItems() {
		return items;
	}

	public int getRange() {
		return range;
	}

	public int getHitEnemiesCount() {
		return hitEnemiesCount;
	}

	public int getPercentToHit() {
		return percentToHit;
	}

	public int getDamage() {
		return damage;
	}

	public int getInv_place() {
		return inv_place;
	}

	public void setInv_place(int inv_place) {
		this.inv_place = inv_place;
	}
}
