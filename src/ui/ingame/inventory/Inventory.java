package ui.ingame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;
import ui.UIImageButton;

public class Inventory implements Settings{
	
	private Handler handler;
	private PlayerInventory player_inv;
	private TradePlayerInventory trade_player_inv;
	private boolean trade;
	private boolean changedItems;
	private ArrayList<UIImageButton> trade_bounds;
	private ArrayList<Player> trade_players;
	//Backup variables
	private Hand backup_left, backup_body, backup_right;
	private ArrayList<Player> backup_players;

	public Inventory(Handler handler){
		this.handler = handler;
		player_inv = new PlayerInventory(handler);
		trade_player_inv = new TradePlayerInventory(handler);
		trade = false;
		changedItems = false;
	}

	public void start(){
		trade_player_inv.start();
		trade_bounds = new ArrayList<UIImageButton>();
	}

	public void tick(){
		//tick trade menu
		if(handler.getGame().getGameState().isShowInventory()){
			Player p = handler.getGame().getGameState().getTurnPlayer();
			trade_bounds = new ArrayList<UIImageButton>();
			trade_players = new ArrayList<Player>();
			for(int i = 0, x = 0; i < handler.getGame().getGameState().getEntityManager().getPlayers().size(); i++){
				if(handler.getGame().getGameState().getEntityManager().getPlayers().get(i) != p){
					Player p_temp = handler.getGame().getGameState().getEntityManager().getPlayers().get(i);
					if(p.getTile().equals(p_temp.getTile())){
						int id = i;
						int xx = x;
						trade_bounds.add(new UIImageButton(handler, 65 + x * 120, 380, Assets.trade_hero){
							@Override
							public void initAction(){
								if(trade){
									trade = false;
									trade_bounds.get(xx).setActive(false);
								}else{
									trade = true;
									trade_bounds.get(xx).setActive(true);
									trade_player_inv.setPlayer_id(id);
								}
							}
						});
						x++;
						trade_players.add(p_temp);
					}
				}
			}
			
			for(UIImageButton btn: trade_bounds){
				btn.tick();
			}
			
			if(!trade){
				player_inv.tick();
			}else{
				trade_player_inv.tick();
			}
		}
	}
	
	public void render(Graphics g){
		if(handler.getGame().getGameState().isShowInventory()){
			g.drawImage(Assets.inventar_background, 50, 50, null);
			Text.drawString(g, "Inventory", 60, 78, false, Color.BLACK, Assets.font28);
			
			for(int i = 0; i < trade_bounds.size(); i++){
				trade_bounds.get(i).render(g);
				Player p = trade_players.get(i);
				Text.drawString(g, p.getHeroName(), 68 + i * 120, 380 + 18, false, Color.black, Assets.font18);
			}

			if(!trade){
				player_inv.render(g);
			}else{
				trade_player_inv.render(g);
			}
		}
	}
	
	public void makeBackup(){
		backup_left = player_inv.getLeftHand();
		backup_body = player_inv.getBody();
		backup_right = player_inv.getRightHand();
		backup_players = handler.getGame().getGameState().getEntityManager().getPlayers();
	}
	
	public void restoreBackup(){
		player_inv.setLeftHand(backup_left);
		player_inv.setBody(backup_body);
		player_inv.setRightHand(backup_right);
		handler.getGame().getGameState().getEntityManager().setPlayers(backup_players);
	}

	//GETTERS & SETTERS
	public PlayerInventory getPlayerInventory() {
		return player_inv;
	}
	
	public boolean isChangedItems() {
		return changedItems;
	}

	public void setChangedItems(boolean changedItems) {
		this.changedItems = changedItems;
	}
}
