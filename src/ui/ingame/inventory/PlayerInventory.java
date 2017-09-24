/*
 * uniffecient storing of the trade_bounds and trade_player vars.
 */

package ui.ingame.inventory;

import java.awt.Color;
import java.awt.Graphics;

import entities.items.Item;
import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;
import ui.UIImageButton;

public class PlayerInventory implements Settings{
	
	public Handler handler;
	private boolean active;
	private Hand leftHand, rightHand, body;
	
	private UIImageButton trash[];

	public PlayerInventory(Handler handler){
		this.handler = handler;
		active = false;
		
		leftHand = new Hand(handler, 90, 280, 1);
		body = new Hand(handler, 300, 280, 2);
		rightHand = new Hand(handler, 510, 280, 3);
		
		trash = new UIImageButton[5];
		for(int i = 0; i < 5; i++){
			int index = i;
			trash[i] = new UIImageButton(handler, 520, 100 + i * 32, Assets.trash_can){
				@Override
				public void initAction(){
					Player p = handler.getGame().getGameState().getTurnPlayer();
					if(leftHand.getItem()[p.getId()] == p.getItems().get(index)){
						leftHand.setItemToZero(p.getId());
						handler.getGame().getGameState().getGUI().getInventory().setChangedItems(true);
					}
					if(body.getItem()[p.getId()] == p.getItems().get(index)){
						body.setItemToZero(p.getId());
						handler.getGame().getGameState().getGUI().getInventory().setChangedItems(true);
					}
					if(rightHand.getItem()[p.getId()] == p.getItems().get(index)){
						rightHand.setItemToZero(p.getId());
						handler.getGame().getGameState().getGUI().getInventory().setChangedItems(true);
					}
					p.getItems().remove(index);
				}
			};
		}
	}
	
	public void tick(){
		if(handler.getGame().getGameState().isShowInventory()){
			active = true;
			leftHand.tick();
			body.tick();
			rightHand.tick();
			for(int i = 0; i < trash.length; i++){
				trash[i].tick();
			}
		}else{
			active = false;
		}
	}
	
	public void render(Graphics g){
		if(active){			
			Player p = handler.getGame().getGameState().getTurnPlayer();
			for(int i = 0; i < p.getItems().size(); i++){
				Item item = p.getItems().get(i);
				p.getItems().get(i).renderTexture(g, 90, 100 + i * 32, 28, 28);
				Text.drawString(g, String.valueOf("Rng: " + item.getRange()), 140, 120 + i * 32, false, Color.BLACK, Assets.font18);
				Text.drawString(g, String.valueOf("Nr: " + item.getHitEnemiesCount()), 240, 120 + i * 32, false, Color.BLACK, Assets.font18);
				Text.drawString(g, String.valueOf("Luk: " + item.getPercentToHit()), 325, 120 + i * 32, false, Color.BLACK, Assets.font18);
				Text.drawString(g, String.valueOf("Dmg: " + item.getDamage()), 440, 120 + i * 32, false, Color.BLACK, Assets.font18);
				trash[i].render(g);
			}
			
			leftHand.render(g);
			body.render(g);
			rightHand.render(g);
		}
	}

	//GETTERS & SETTERS
	public Hand getLeftHand() {
		return leftHand;
	}

	public Hand getRightHand() {
		return rightHand;
	}

	public Hand getBody() {
		return body;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setLeftHand(Hand leftHand) {
		this.leftHand = leftHand;
	}

	public void setRightHand(Hand rightHand) {
		this.rightHand = rightHand;
	}

	public void setBody(Hand body) {
		this.body = body;
	}
}
