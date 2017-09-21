package ui.ingame.inventory;

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
	private int itemCounter[];
	private long lastActionTimer, actionCooldown = 200, actionTimer = actionCooldown;
	private boolean set;
	private int place;
	
	public Hand(Handler handler, int x, int y, int inv_place){
		this.handler = handler;
		this.background_img = Assets.hands_inventar_background;
		this.x = x; 
		this.y = y;
		this.width = Assets.hands_inventar_background.getWidth();
		this.height = Assets.hands_inventar_background.getHeight();
		this.bounds = new Rectangle(x, y, width, height);
		this.set = false;
		this.itemCounter = new int[MAXPLAYERNUMBER];
		this.item = new Item[MAXPLAYERNUMBER];
		this.place = inv_place;
	}
	
	public void tick(){
		if(!set){
			int size = handler.getGame().getGameState().getEntityManager().getPlayers().size();
			item = new Item[size];
			itemCounter = new int[size];
			for(int i = 0; i < size; i++){
				item[i] = null;
				itemCounter[i] = 0;
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
			for(int i = itemCounter[p.getId()]; i < p.getItems().size(); ){
				System.out.println("i: " + i + " | itemCounter: " + itemCounter);				
				
				//if item is not in use
				if(p.getItems().get(i).getInv_place() == 0){
					if(item[p.getId()] != null)
						item[p.getId()].setInv_place(0);
					item[p.getId()] = p.getItems().get(i);
					item[p.getId()].setInv_place(place);
					itemCounter[p.getId()] = i;
					System.out.println("item " + p.getItems().get(i).getName() + " was set for hand");
					handler.getGame().getGameState().getGUI().getInventory().setChangedItems(true);
					return;
				//if item is in use
				}else{
					i++;
				}
			}
			
			//if item cant be selected (iterated through list without result)
			p.getItems().get(itemCounter[p.getId()]).setInv_place(0);
			itemCounter[p.getId()] = 0;
			item[p.getId()] = null;
			handler.getGame().getGameState().getGUI().getInventory().setChangedItems(true);
		}
	}
	
	public void render(Graphics g){
		g.drawImage(background_img, x, y, width, height, null);
		int id = handler.getGame().getGameState().getTurnPlayer().getId();
		
		if(item[id] != null){
			item[id].renderTexture(g, x + 15, y + 15, 64, 64);
		}
	}
	
	public void setItemToZero(int playerID){
		itemCounter[playerID] = handler.getGame().getGameState().getTurnPlayer().getItems().size();
		item[playerID] = null;
	}

	//GETTERS & SETTERS
	public Item[] getItem() {
		return item;
	}

	public int[] getItemCounter() {
		return itemCounter;
	}

}
