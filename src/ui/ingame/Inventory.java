package ui.ingame;

import java.awt.Color;
import java.awt.Graphics;

import entities.items.Item;
import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;

public class Inventory implements Settings{
	
	public Handler handler;
	private boolean active;
	private Hand leftHand, rightHand, body;

	public Inventory(Handler handler){
		this.handler = handler;
		active = false;
		
		leftHand = new Hand(handler, 90, 280, 1);
		body = new Hand(handler, 300, 280, 2);
		rightHand = new Hand(handler, 510, 280, 3);
	}
	
	public void tick(){
		if(handler.getGame().getGameState().isShowInventory()){
			active = true;
			leftHand.tick();
			body.tick();
			rightHand.tick();
		}else{
			active = false;
		}
	}
	
	public void render(Graphics g){
		if(active){
			g.drawImage(Assets.inventar_background, 50, 50, null);
			Text.drawString(g, "Inventory", 60, 78, false, Color.BLACK, Assets.font28);
			drawCurrentPlayerInventory(g);
			drawTradeMenu(g);
		}
	}
	
	private void drawCurrentPlayerInventory(Graphics g){
		Player p = handler.getGame().getGameState().getTurnPlayer();
		for(int i = 0; i < p.getItems().size(); i++){
			Item item = p.getItems().get(i);
			p.getItems().get(i).renderTexture(g, 90, 100 + i * 32, 28, 28);
			Text.drawString(g, String.valueOf("Rng: " + item.getRange()), 140, 120 + i * 32, false, Color.BLACK, Assets.font18);
			Text.drawString(g, String.valueOf("Nr: " + item.getHitEnemiesCount()), 240, 120 + i * 32, false, Color.BLACK, Assets.font18);
			Text.drawString(g, String.valueOf("Luk: " + item.getPercentToHit()), 325, 120 + i * 32, false, Color.BLACK, Assets.font18);
			Text.drawString(g, String.valueOf("Dmg: " + item.getDamage()), 440, 120 + i * 32, false, Color.BLACK, Assets.font18);
		}
		
		leftHand.render(g);
		body.render(g);
		rightHand.render(g);
	}
	
	private void drawTradeMenu(Graphics g){
		
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
}
