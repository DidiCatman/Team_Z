package ui.ingame;

import java.awt.Color;
import java.awt.Graphics;

import entities.items.Item;
import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import main.Handler;

public class PlayerToolTip {

	@SuppressWarnings("unused")
	private Handler handler;
	private Player player;
	
	public PlayerToolTip(Handler handler){
		this.handler = handler;
	}
	
	public void tick(){}
	
	public void render(Graphics g){
		//draw background
		g.drawImage(Assets.tooltip_background, 60, 60, null);
		
		//draw text
		Text.drawString(g, player.getHeroName(), 70, 90, false, Color.BLACK, Assets.font28);
		Text.drawString(g, String.valueOf("Health: " + player.getHealth()), 70, 120, false, Color.BLACK, Assets.font23);
		
		//draw inventory
		Text.drawString(g, "Inventory:", 70, 160, false, Color.BLACK, Assets.font28);
		if(player.getItems() != null){
			for(int i = 0; i < player.getItems().size(); i++){
				Item item = player.getItems().get(i);
				Text.drawString(g, item.getName(), 70, 190 + i * 45, false, Color.BLACK, Assets.font23);
				item.renderTexture(g, 175, 165 + i * 45, 32, 32);
				Text.drawString(g, String.valueOf("Rng: " + item.getRange()), 230, 190 + i * 45, false, Color.BLACK, Assets.font23);
				Text.drawString(g, String.valueOf("Nr: " + item.getHitEnemiesCount()), 330, 190 + i * 45, false, Color.BLACK, Assets.font23);
				Text.drawString(g, String.valueOf("Luk: " + item.getPercentToHit()), 415, 190 + i * 45, false, Color.BLACK, Assets.font23);
				Text.drawString(g, String.valueOf("Dmg: " + item.getDamage()), 530, 190 + i * 45, false, Color.BLACK, Assets.font23);
			}
		}
	}

	//GETTERS & SETTERS
	public void setPlayer(Player player) {
		this.player = player;
	}
}
