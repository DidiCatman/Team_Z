package entities.player.actions;

import java.awt.Graphics;

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
			System.out.println("show items");
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
