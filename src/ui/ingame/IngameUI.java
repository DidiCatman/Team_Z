package ui.ingame;

import java.awt.Graphics;

import main.Handler;

public class IngameUI {
	
	@SuppressWarnings("unused")
	private Handler handler;
	private Inventory inventory;

	public IngameUI(Handler handler){
		this.handler = handler;
		inventory = new Inventory(handler);
	}
	
	public void tick(){
		inventory.tick();
	}
	
	public void render(Graphics g){
		//draw inventory
		inventory.render(g);
	}
	
}
