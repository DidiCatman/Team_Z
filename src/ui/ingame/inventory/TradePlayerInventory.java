package ui.ingame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.items.Item;
import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;
import ui.UIImageButton;

public class TradePlayerInventory implements Settings{

	private Handler handler;
	private ArrayList<Player> players;
	private UIImageButton trade_btn[];
	private int player_id;

	public TradePlayerInventory(Handler handler){
		this.handler = handler;
		trade_btn = new UIImageButton[5];
		
		for(int i = 0; i < 5; i++){
			int index = i;
			trade_btn[i] = new UIImageButton(handler, 520, 100 + i * 32, Assets.trade_icon){
				@Override
				public void initAction(){
					//trade item if player has space in inventory
					if(handler.getGame().getGameState().getTurnPlayer().getItems().size() < MAXITEMS){
						Item item = players.get(player_id).getItems().get(index);
						handler.getGame().getGameState().getTurnPlayer().addItemToInventory(item);
						players.get(player_id).getItems().remove(item);
						handler.getGame().getGameState().getGUI().getInventory().setChangedItems(true);
					//player has no space
					}else{
						JOptionPane.showMessageDialog(null, "Inventory is full", "Warning", JOptionPane.OK_OPTION);
					}
				}
			};
		}
	}
	
	public void start(){
		players = handler.getGame().getGameState().getEntityManager().getPlayers();
		player_id = 0;
	}
	
	public void tick(){
		for(int i = 0; i < trade_btn.length; i++){
			trade_btn[i].tick();
		}
	}
	
	public void render(Graphics g){
		Player p = players.get(player_id);
		for(int i = 0; i < p.getItems().size(); i++){
			Item item = p.getItems().get(i);
			p.getItems().get(i).renderTexture(g, 90, 100 + i * 32, 28, 28);
			Text.drawString(g, String.valueOf("Rng: " + item.getRange()), 140, 120 + i * 32, false, Color.BLACK, Assets.font18);
			Text.drawString(g, String.valueOf("Nr: " + item.getHitEnemiesCount()), 240, 120 + i * 32, false, Color.BLACK, Assets.font18);
			Text.drawString(g, String.valueOf("Luk: " + item.getPercentToHit()), 325, 120 + i * 32, false, Color.BLACK, Assets.font18);
			Text.drawString(g, String.valueOf("Dmg: " + item.getDamage()), 440, 120 + i * 32, false, Color.BLACK, Assets.font18);
			trade_btn[i].render(g);
		}

		Text.drawString(g, p.getHeroName(), 360, 85, false, Color.BLACK, Assets.font28);
	}

	//GETTERS & SETTERS
	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	
}
