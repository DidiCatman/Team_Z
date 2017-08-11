package ui.ingame;

import java.awt.Graphics;

import gfx.Assets;
import main.Handler;
import ui.UIImageButton;

public class TaskMenu {
	
	private Handler handler;
	private UIImageButton move, attack, item, end;

	public TaskMenu(Handler handler){
		this.handler = handler;
		initButtons();
	}
	
	private void initButtons() {
		move = new UIImageButton(handler, 455, 515, Assets.move){
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
				attack.setActive(false);
				item.setActive(false);
				System.out.println("NIY - move");
			}
		};
		attack = new UIImageButton(handler, 530, 515, Assets.attack){
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
				move.setActive(false);
				item.setActive(false);
				System.out.println("NIY - attack");
			}
		};
		item = new UIImageButton(handler, 455, 555, Assets.item){
			@Override
			public void initAction(){
				if(!handler.getGame().getGameState().isShowItems()){
					handler.getGame().getGameState().setShowItems(true);
					item.setActive(true);
				}else{
					handler.getGame().getGameState().setShowItems(false);
					item.setActive(false);
				}
				
				handler.getGame().getGameState().setShowMoves(false);
				handler.getGame().getGameState().setShowAttacks(false);
				move.setActive(false);
				attack.setActive(false);
				System.out.println("NIY - item");
			}
		};
		end = new UIImageButton(handler, 530, 555, Assets.end_turn){
			@Override
			public void initAction(){
				handler.getGame().getGameState().endTurn();
				
				handler.getGame().getGameState().setShowMoves(false);
				handler.getGame().getGameState().setShowAttacks(false);
				handler.getGame().getGameState().setShowItems(false);
				move.setActive(false);
				attack.setActive(false);
				item.setActive(false);
			}
		};
	}

	public void tick(){
		move.tick();
		attack.tick();
		item.tick();
		end.tick();
	}
	
	public void render(Graphics g){
		move.render(g);
		attack.render(g);
		item.render(g);
		end.render(g);
	}
	
}
