package ui.ingame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import main.Handler;

public class PlayerMenu {
	
	private Handler handler;
	private Rectangle[] player_rec;
	private PlayerToolTip playerTooltip;
	private boolean activeTip;

	public PlayerMenu(Handler handler){
		this.handler = handler;
		playerTooltip = new PlayerToolTip(handler); 
		activeTip = false;
	}
	
	public void tick(){
		ArrayList<Player> players = handler.getGame().getGameState().getEntityManager().getPlayers();
		int count = 0;
		//order player rectangles
		for(int i = 0; i < players.size(); i++){
			player_rec[i].setBounds(750, 5 + count*70, 150, 70);
			count++;
		}
		
		//init tooltip function
		activeTip = false;
		for(int i = 0; i < players.size(); i++){
			Point mouse = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
			if(player_rec[i].contains(mouse)){
				//show hero tooltip
				playerTooltip.setPlayer(players.get(i));
				playerTooltip.tick();
				activeTip = true;
			}
		}
	}
	
	public void render(Graphics g){
		ArrayList<Player> players = handler.getGame().getGameState().getEntityManager().getPlayers();
		for(int i = 0; i < players.size(); i++){
			Player p = players.get(i);
			//render text
			Text.drawString(g, String.valueOf("#" + (p.getId()+1) + " " + p.getHeroName()), player_rec[i].x + 10, player_rec[i].y + 20, false, Color.BLACK, Assets.font18);
			Text.drawString(g, String.valueOf("Health: " + p.getHealth()), player_rec[i].x + 10, player_rec[i].y + 40, false, Color.BLACK, Assets.font18);
		}
		
		if(activeTip){
			playerTooltip.render(g);
		}
	}
	
	public void start(){
		player_rec = new Rectangle[handler.getGame().getGameState().getEntityManager().getPlayers().size()];
		for(int i = 0; i < player_rec.length; i++){
			player_rec[i] = new Rectangle(750, 20 + i*70, 150, 70);
		}
	}
}
