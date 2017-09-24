package entities.player.actions;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.buildings.House;
import entities.buildings.Room;
import entities.items.Item;
import entities.player.Player;
import main.Handler;

public class SearchItems {

	private Handler handler;
	
	public SearchItems(Handler handler){
		this.handler = handler;
	}
	
	//tick method was used to search item or get message when player has already searched
	public void tick(){
		//when player hasn't seached before
		if(!handler.getGame().getGameState().hasSearched()){
			//check if player is in house
			Player player = handler.getGame().getGameState().getTurnPlayer();
			ArrayList<House> houses = handler.getGame().getGameState().getHouseManager().getHouses();
			for(House h: houses){
				for(Room r: h.getRooms()){
					if(player.getTilex() == r.getTilex() && player.getTiley() == r.getTiley()){
						Item item = handler.getGame().getGameState().getItemManager().randomItem();
						handler.getGame().getGameState().getTurnPlayer().addItemToInventory(item);
						handler.getGame().getGameState().setHasSearched(true);
						handler.getGame().getGameState().getTurnPlayer().decreaseActionPoints();
						System.out.println("random item: " + item.getName() + " | id: " + item.getId());
					}
				}
			}
		}else{
			System.out.println("NIY - message popup when item was searched before");
		}
		handler.getGame().getGameState().setShowItems(false);
	}
	
	public void render(Graphics g){
		
	}
}
