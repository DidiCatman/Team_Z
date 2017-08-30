package ui.ingame;

import java.awt.Color;
import java.awt.Graphics;

import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;
import main.Translations;
import ui.UIImageButton;
import ui.ingame.inventory.Inventory;

public class GUI implements Settings, Translations{
	
	private Handler handler;
	private UIImageButton settings_btn;
	private TaskMenu taskMenu;
	private PlayerMenu playerMenu;
	private Inventory inventory;
	private Hand leftHand, rightHand, body;

	public GUI(Handler handler){
		this.handler = handler;
		taskMenu = new TaskMenu(handler);
		playerMenu = new PlayerMenu(handler);
		inventory = new Inventory(handler);
		
		initButtons();
	}
	
	private void initButtons() {
		settings_btn = new UIImageButton(handler, 292, 560, Assets.settings){
			@Override
			public void initAction(){
				System.out.println(WORDS[handler.getGame().getLanguage()][2]);
			}
		};
		
		leftHand = new Hand(handler, 4, 505);
		rightHand = new Hand(handler, 400, 505);
		body = new Hand(handler, WIDTH - Assets.hands_inventar_background.getWidth() - 4, 505);
	}

	public void tick(){
		settings_btn.tick();
		taskMenu.tick();
		playerMenu.tick();
		inventory.tick();
		leftHand.tick();
		body.tick();
		rightHand.tick();
	}
	
	public void start(){
		playerMenu.start();
		inventory.start();
	}
	
	public void render(Graphics g){
		Player player = handler.getGame().getGameState().getTurnPlayer();
		
		//draw inventory background
		g.drawImage(Assets.ingame_gui_background, 0, 0, null);
		
		//draw active inventory places
		Text.drawString(g, "Left Hand", 14, 525, false, Color.black, Assets.font18);
		if(inventory.getPlayerInventory().getLeftHand().getItem()[player.getId()] != null){
			inventory.getPlayerInventory().getLeftHand().getItem()[player.getId()].renderTexture(g, 4, 530, 64, 64);
		}
		Text.drawString(g, "Body", 380, 525, false, Color.black, Assets.font18);
		if(inventory.getPlayerInventory().getBody().getItem()[player.getId()] != null){
			inventory.getPlayerInventory().getBody().getItem()[player.getId()].renderTexture(g, 370, 530, 64, 64);
		}
		Text.drawString(g, "Right Hand", WIDTH - Assets.hands_inventar_background.getWidth() +6, 525, false, Color.black, Assets.font18);
		if(inventory.getPlayerInventory().getRightHand().getItem()[player.getId()] != null){
			inventory.getPlayerInventory().getRightHand().getItem()[player.getId()].renderTexture(g, WIDTH - Assets.hands_inventar_background.getWidth() - 4, 530, 64, 64);
		}
		
		//draw buttons
		settings_btn.render(g);
		
		//draw hero name text
		String name = String.valueOf("#" + (player.getId() + 1) + " " + player.getHeroName());
		Text.drawString(g, name, 155, 530, false, Color.BLACK, Assets.font28);
		
		//draw action points text
		String actions = String.valueOf("Actions: " + handler.getGame().getGameState().getTurnPlayer().getActionCounter());
		Text.drawString(g, actions, 155, 555, false, Color.BLACK, Assets.font28);
		
		//render health bar
		int health = handler.getGame().getGameState().getTurnPlayer().getHealth();
		for(int i = 0; i < DEFAULT_PLAYER_HEALTH; i++){
			if(i <= health){
				g.drawImage(Assets.heart, 158 + i * 42, 560, null);
			}else{
				g.drawImage(Assets.heart_empty, 158 + i * 42, 560, null);
			}
		}
		
		//render menus
		taskMenu.render(g);
		inventory.render(g);
		playerMenu.render(g);
	}

	//GETTERS & SETTERS
	public TaskMenu getTaskMenu() {
		return taskMenu;
	}

	public PlayerMenu getPlayerMenu() {
		return playerMenu;
	}

	public Inventory getInventory() {
		return inventory;
	}
}
