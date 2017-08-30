package ui.ingame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.items.Item;
import entities.player.Player;
import gfx.Assets;
import main.Handler;
import main.Settings;

public class Hand implements Settings{
	
	private BufferedImage background_img;
	private Handler handler;
	private int x, y, width, height;
	private Rectangle bounds;
	private Item[] item;
	private int itemCounter;
	private long lastActionTimer, actionCooldown = 100, actionTimer = actionCooldown;
	private boolean set;
	
	public Hand(Handler handler, int x, int y){
		this.handler = handler;
		this.background_img = Assets.hands_inventar_background;
		this.x = x; 
		this.y = y;
		this.width = Assets.hands_inventar_background.getWidth();
		this.height = Assets.hands_inventar_background.getHeight();
		this.bounds = new Rectangle(x, y, width, height);
		this.set = false;
		this.itemCounter = 0;
		this.item = new Item[MAXPLAYERNUMBER];
	}
	
	public void tick(){
		if(!set){
			int size = handler.getGame().getGameState().getEntityManager().getPlayers().size();
			item = new Item[size];
			for(int i = 0; i < size; i++){
				item[i] = null;
			}
			set = true;
		}
		
		//handle action cooldown
		actionTimer += System.currentTimeMillis() - lastActionTimer;
		lastActionTimer = System.currentTimeMillis();
		if(actionTimer < actionCooldown)
			return;
		
		if(handler.getMouseManager().isLeftPressed()){
			Point mouse = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
			if(bounds.contains(mouse)){
				changeItem();
			}
		}
		
		actionTimer = 0;
	}
	
	private void changeItem(){
		Player p = handler.getGame().getGameState().getTurnPlayer();
		
		//if inventory is not empty
		if(!p.getItems().isEmpty()){
			/*
			 * mit ner for loop gegenchecken...
			 */
			//check if counter + 1 is greater or equal than items.size
			if(itemCounter + 1 >= p.getItems().size()){
				if((itemCounter + 1) == p.getItems().size()){
					//set item to null
					itemCounter = p.getItems().size();
					item[p.getId()] = null;
				}else{
					itemCounter = 0;
					item[p.getId()] = p.getItems().get(0);
				}
			}else{
				itemCounter++;
				item[p.getId()] = p.getItems().get(itemCounter);
			}
		}
	}
	
	public void render(Graphics g){
		g.drawImage(background_img, x, y, width, height, null);
		int id = handler.getGame().getGameState().getTurnPlayer().getId();
		
		if(item[id] != null){
			item[id].renderTexture(g, x + 15, y + 15, 64, 64);
		}
	}

}
