package entities.player.actions;

import java.awt.Graphics;

import main.Handler;
import main.Settings;
import ui.ingame.inventory.Inventory;

public class PlayerActions implements Settings{
	
	private Handler handler;
	
	private MovePlayer movePlayer;
	private OpenDoor openDoor;
	private SearchItems searchItems;
	private Inventory inventory;
	
	public PlayerActions(Handler handler){
		this.handler = handler;
		movePlayer = new MovePlayer(handler);
		openDoor = new OpenDoor(handler);
		searchItems = new SearchItems(handler);
		inventory = new Inventory(handler);
	}
	
	public void start(){
		inventory.start();
	}
	
	public void tick(){
		if(handler.getGame().getGameState().isShowMoves()){
			movePlayer.tick();
		}		
		if(handler.getGame().getGameState().isShowAttacks()){
			System.out.println("NIY - show attacks");
		}
		
		if(handler.getGame().getGameState().isShowSearchables()){
			searchItems.tick(); 
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
			searchItems.render(g);
		}
		if(handler.getGame().getGameState().isShowOpenDoors()){
			openDoor.render(g);
		}
	}
	
}
