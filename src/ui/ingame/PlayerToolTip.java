package ui.ingame;

import java.awt.Color;
import java.awt.Graphics;

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
		g.drawImage(Assets.tooltip_background, 50, 50, null);
		
		//draw text
		Text.drawString(g, player.getHeroName(), 70, 90, false, Color.BLACK, Assets.font28);
		Text.drawString(g, String.valueOf("Health: " + player.getHealth()), 70, 130, false, Color.BLACK, Assets.font28);
	}

	//GETTERS & SETTERS
	public void setPlayer(Player player) {
		this.player = player;
	}
}
