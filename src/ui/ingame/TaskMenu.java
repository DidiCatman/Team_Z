package ui.ingame;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import gfx.Assets;
import main.Handler;
import main.Settings;
import ui.UIImageButton;

public class TaskMenu implements Settings {
	
	private Handler handler;
<<<<<<< HEAD
	private UIImageButton move, attack, search, open, endTurn, inventory;
=======
	private UIImageButton move, attack, search, open, 
	endTurn, trade;
>>>>>>> small redesign with endTurn and trade button

	public TaskMenu(Handler handler){
		this.handler = handler;
		initButtons();
	}
	
	private void initButtons() {
		move = new UIImageButton(handler, 525, 515, Assets.move){
			@Override
			public void initAction(){
				if(handler.getGame().getGameState().isShowInventory() == true){
					leftInventoryWarning();
				}
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
				handler.getGame().getGameState().setShowInventory(false);
				attack.setActive(false);
				search.setActive(false);
				open.setActive(false);
				inventory.setActive(false);
			}
		};
		attack = new UIImageButton(handler, 600, 515, Assets.attack){
			@Override
			public void initAction(){
				if(handler.getGame().getGameState().isShowInventory() == true){
					leftInventoryWarning();
				}
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
				handler.getGame().getGameState().setShowInventory(false);
				move.setActive(false);
				search.setActive(false);
				open.setActive(false);
				inventory.setActive(false);
				System.out.println("NIY - attack");
			}
		};
<<<<<<< HEAD
=======
		trade = new UIImageButton(handler, 675, 515, Assets.trade){
			@Override
			public void initAction(){				
				handler.getGame().getGameState().setShowMoves(false);
				handler.getGame().getGameState().setShowAttacks(false);
				handler.getGame().getGameState().setShowItems(false);
				move.setActive(false);
				attack.setActive(false);
				search.setActive(false);
				
				System.out.println("NIY - trade ");
			}
		};
>>>>>>> small redesign with endTurn and trade button
		
		search = new UIImageButton(handler, 525, 555, Assets.search){
			@Override
			public void initAction(){
				if(handler.getGame().getGameState().isShowInventory() == true){
					leftInventoryWarning();
				}
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
				handler.getGame().getGameState().setShowInventory(false);
				move.setActive(false);
				attack.setActive(false);
				open.setActive(false);
				inventory.setActive(false);
			}
		};
		open = new UIImageButton(handler, 600, 555, Assets.open_doors){
			@Override
			public void initAction(){
				if(handler.getGame().getGameState().isShowInventory() == true){
					leftInventoryWarning();
				}
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
				handler.getGame().getGameState().setShowInventory(false);
				move.setActive(false);
				attack.setActive(false);
				search.setActive(false);
				inventory.setActive(false);
			}
		};
		endTurn = new UIImageButton(handler, 675, 555, Assets.endTurn){
			@Override
			public void initAction(){
				if(handler.getGame().getGameState().isShowInventory() == true){
					leftInventoryWarning();
				}
				handler.getGame().getGameState().setShowMoves(false);
				handler.getGame().getGameState().setShowAttacks(false);
				handler.getGame().getGameState().setShowItems(false);
				handler.getGame().getGameState().setShowInventory(false);
				move.setActive(false);
				attack.setActive(false);
				search.setActive(false);
				inventory.setActive(false);
				handler.getGame().getGameState().endTurn();
			}
		};
		
		inventory = new UIImageButton(handler, WIDTH - Assets.inventory[0].getWidth() - 3, 470, Assets.inventory){
			@Override
			public void initAction(){
				if(handler.getGame().getGameState().isShowInventory() == false){
					handler.getGame().getGameState().setShowInventory(true);
					handler.getGame().getGameState().getGUI().getInventory().makeBackup();
					inventory.setActive(true);
				}else{
					leftInventoryWarning();
					inventory.setActive(false);
					handler.getGame().getGameState().setShowInventory(false);
					handler.getGame().getGameState().getGUI().getInventory().getPlayerInventory().setActive(false);
				}
				
				handler.getGame().getGameState().setShowMoves(false);
				handler.getGame().getGameState().setShowAttacks(false);
				handler.getGame().getGameState().setShowItems(false);
				handler.getGame().getGameState().getGUI().getInventory().setChangedItems(false);
				move.setActive(false);
				attack.setActive(false);
				search.setActive(false);
			}
		};
		endTurn = new UIImageButton(handler, 675, 555, Assets.endTurn){
			@Override
			public void initAction(){				
				handler.getGame().getGameState().setShowMoves(false);
				handler.getGame().getGameState().setShowAttacks(false);
				handler.getGame().getGameState().setShowItems(false);
				move.setActive(false);
				attack.setActive(false);
				search.setActive(false);
				handler.getGame().getGameState().endTurn();
			}
		};
	}
	
	private void leftInventoryWarning(){
		if(handler.getGame().getGameState().getGUI().getInventory().isChangedItems()){
			int result = JOptionPane.showConfirmDialog(null, 
					   "Are you happy with your settings?", null, JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				handler.getGame().getGameState().getTurnPlayer().decreaseActionPoints();
			}else{
				handler.getGame().getGameState().getGUI().getInventory().restoreBackup();
			}
			handler.getGame().getGameState().getGUI().getInventory().setChangedItems(false);
		}
	}

	public void tick(){
		move.tick();
		attack.tick();
		search.tick();
		open.tick();
<<<<<<< HEAD
		endTurn.tick();
		inventory.tick();
=======
		trade.tick();
		endTurn.tick();
>>>>>>> small redesign with endTurn and trade button
	}
	
	public void render(Graphics g){
		move.render(g);
		attack.render(g);
		search.render(g);
		open.render(g);
<<<<<<< HEAD
		endTurn.render(g);
		inventory.render(g);
=======
		trade.render(g);
		endTurn.render(g);
>>>>>>> small redesign with endTurn and trade button
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

	public UIImageButton getEndTurn() {
		return endTurn;
	}

	public UIImageButton getInventory() {
		return inventory;
	}
	
}
