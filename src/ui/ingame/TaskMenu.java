package ui.ingame;

import java.awt.Graphics;

import gfx.Assets;
import main.Handler;
import ui.UIImageButton;

public class TaskMenu {
	
	private Handler handler;
	private UIImageButton move, attack, search, open;

	public TaskMenu(Handler handler){
		this.handler = handler;
		initButtons();
	}
	
	private void initButtons() {
		move = new UIImageButton(handler, 600, 515, Assets.move){
			@Override
			public void initAction(){
				if(!handler.getGame().getGameState().isShowMoves()){
					handler.getGame().getGameState().setShowMoves(true);
					move.setActive(true);
				}else{
					handler.getGame().getGameState().setShowMoves(false);
					move.setActive(false);
				}
				
				handler.getGame().getGameState().setShowAttacks(false);
				handler.getGame().getGameState().setShowItems(false);
				handler.getGame().getGameState().setShowOpenDoors(false);
				attack.setActive(false);
				search.setActive(false);
				open.setActive(false);
			}
		};
		attack = new UIImageButton(handler, 675, 515, Assets.attack){
			@Override
			public void initAction(){
				if(!handler.getGame().getGameState().isShowAttacks()){
					handler.getGame().getGameState().setShowAttacks(true);
					attack.setActive(true);
				}else{
					handler.getGame().getGameState().setShowAttacks(false);
					attack.setActive(false);
				}
				
				handler.getGame().getGameState().setShowMoves(false);
				handler.getGame().getGameState().setShowItems(false);
				handler.getGame().getGameState().setShowOpenDoors(false);
				move.setActive(false);
				search.setActive(false);
				open.setActive(false);
				System.out.println("NIY - attack");
			}
		};
		search = new UIImageButton(handler, 600, 555, Assets.search){
			@Override
			public void initAction(){
				if(!handler.getGame().getGameState().isShowSearchables()){
					handler.getGame().getGameState().setShowItems(true);
					search.setActive(true);
				}else{
					handler.getGame().getGameState().setShowItems(false);
					search.setActive(false);
				}
				
				handler.getGame().getGameState().setShowMoves(false);
				handler.getGame().getGameState().setShowAttacks(false);
				handler.getGame().getGameState().setShowOpenDoors(false);
				move.setActive(false);
				attack.setActive(false);
				open.setActive(false);
				System.out.println("NIY - item");
			}
		};
		open = new UIImageButton(handler, 675, 555, Assets.open_doors){
			@Override
			public void initAction(){
				if(!handler.getGame().getGameState().isShowOpenDoors()){
					handler.getGame().getGameState().setShowOpenDoors(true);
					open.setActive(true);
				}else{
					handler.getGame().getGameState().setShowOpenDoors(false);
					open.setActive(false);
				}
				
				handler.getGame().getGameState().setShowMoves(false);
				handler.getGame().getGameState().setShowAttacks(false);
				handler.getGame().getGameState().setShowItems(false);
				move.setActive(false);
				attack.setActive(false);
				search.setActive(false);
				System.out.println("NIY - open");
			}
		};
	}

	public void tick(){
		move.tick();
		attack.tick();
		search.tick();
		open.tick();
	}
	
	public void render(Graphics g){
		move.render(g);
		attack.render(g);
		search.render(g);
		open.render(g);
	}

	//GETTERS & SETTERS
	public UIImageButton getMove() {
		return move;
	}

	public UIImageButton getAttack() {
		return attack;
	}

	public UIImageButton getSearch() {
		return search;
	}

	public UIImageButton getOpenDoors() {
		return open;
	}
	
}
