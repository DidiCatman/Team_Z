package ui.ingame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import entities.items.Item;
import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;
import ui.UIImageButton;

public class Inventory implements Settings{
	
	public Handler handler;
	private boolean active;
	private Hand leftHand, rightHand, body;
	private Hand backup_left, backup_body, backup_right;
	private ArrayList<Player> players;
	private ArrayList<UIImageButton> trade_bounds;
	private ArrayList<Player> trade_players;
	
	private UIImageButton trash[];

	public Inventory(Handler handler){
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
					}
					if(body.getItem()[p.getId()] == p.getItems().get(index)){
						body.setItemToZero(p.getId());
					}
					if(rightHand.getItem()[p.getId()] == p.getItems().get(index)){
						rightHand.setItemToZero(p.getId());
					}
					p.getItems().remove(index);
				}
			};
		}
	}
	
	public void start(){
		players = handler.getGame().getGameState().getEntityManager().getPlayers();
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
			
			//tick trade menu
			Player p = handler.getGame().getGameState().getTurnPlayer();
			trade_bounds = new ArrayList<UIImageButton>();
			trade_players = new ArrayList<Player>();
			for(int i = 0, x = 0; i < players.size(); i++){
				if(players.get(i) != p){
					Player p_temp = handler.getGame().getGameState().getEntityManager().getPlayers().get(i);
					if(p.getTile().equals(p_temp.getTile())){
						trade_bounds.add(new UIImageButton(handler, 65 + x * 120, 380, Assets.trade_hero){
							@Override
							public void initAction(){
								if(p.getItems().size() < MAXITEMS){
									System.out.println("NIY - trade");
								}else{
									System.out.println("NIY - warning - to much items in inventory");
								}
							}
						});
						trade_players.add(p_temp);
					}
				}
			}
			
			for(UIImageButton btn: trade_bounds){
				btn.tick();
			}
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
			trash[i].render(g);
		}
		
		leftHand.render(g);
		body.render(g);
		rightHand.render(g);
	}
	
	private void drawTradeMenu(Graphics g){
		for(int i = 0; i < trade_bounds.size(); i++){
			trade_bounds.get(i).render(g);
			Player p = trade_players.get(i);
			Text.drawString(g, p.getHeroName(), 68 + i * 120, 380 + 18, false, Color.black, Assets.font18);
		}
	}
	
	public void makeBackup(){
		backup_left = leftHand;
		backup_body = body;
		backup_right = rightHand;
		players = handler.getGame().getGameState().getEntityManager().getPlayers();
	}
	
	public void restoreBackup(){
		leftHand = backup_left;
		body = backup_body;
		rightHand = backup_right;
		handler.getGame().getGameState().getEntityManager().setPlayers(players);
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
