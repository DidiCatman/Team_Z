package entities.player;

import java.awt.Graphics;
import java.awt.Point;

import main.Handler;
import tiles.Tile;

public class PlayerActions {
	
	private Handler handler;
	private int tilex, tiley;
	
	public PlayerActions(Handler handler){
		this.handler = handler;
	}
	
	public void tick(){
		if(handler.getGame().getGameState().isShowMoves()){
			tickMovableTiles();
			tickSelector();
			System.out.println("show moves");
		}
		
		if(handler.getGame().getGameState().isShowAttacks()){
			System.out.println("show attacks");
		}
		
		if(handler.getGame().getGameState().isShowItems()){
			System.out.println("show items");
		}
	}
	
	private void tickSelector() {
		
		
	}

	private void tickMovableTiles() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g){
		if(handler.getGame().getGameState().isShowMoves()){
			
		}
		
		if(handler.getGame().getGameState().isShowAttacks()){
			
		}
		
		if(handler.getGame().getGameState().isShowItems()){
			
		}
	}

}
