package entities.player.actions;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.buildings.House;
import entities.buildings.Room;
import entities.items.Item;
import entities.player.Player;
import main.Handler;
import main.Settings;

public class PlayerActions implements Settings{
	
	private Handler handler;
	
	private MovePlayer movePlayer;
	private OpenDoor openDoor;
	
	public PlayerActions(Handler handler){
		this.handler = handler;
		movePlayer = new MovePlayer(handler);
		openDoor = new OpenDoor(handler);
	}
	
	public void tick(){
		if(handler.getGame().getGameState().isShowMoves()){
			movePlayer.tick();
		}		
		if(handler.getGame().getGameState().isShowAttacks()){
			System.out.println("show attacks");
		}
		
		if(handler.getGame().getGameState().isShowSearchables()){
			if(!handler.getGame().getGameState().hasSearched()){//check if player is in house
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
				System.out.println("item was searched before");
			}
			handler.getGame().getGameState().setShowItems(false);
		}
		
		if(handler.getGame().getGameState().isShowOpenDoors()){
			openDoor.tick();
		}
	}
	
	public void render(Graphics g){
		if(handler.getGame().getGameState().isShowMoves()){
			movePlayer.render(g);
		}
		
		if(handler.getGame().getGameState().isShowAttacks()){
			
		}
		
		if(handler.getGame().getGameState().isShowSearchables()){
			
		}
		
		if(handler.getGame().getGameState().isShowOpenDoors()){
			openDoor.render(g);
		}
	}
	
}
