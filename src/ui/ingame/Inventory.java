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

public class Inventory implements Settings, Translations{
	
	private Handler handler;
	private UIImageButton settings;
	private TaskMenu taskMenu;
	private PlayerMenu playerMenu;
	private Hand leftHand, rightHand, body;

	public Inventory(Handler handler){
		this.handler = handler;
		taskMenu = new TaskMenu(handler);
		playerMenu = new PlayerMenu(handler);
		
		initButtons();
	}
	
	private void initButtons() {
		settings = new UIImageButton(handler, 292, 560, Assets.settings){
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
		settings.tick();
		taskMenu.tick();
		playerMenu.tick();
		leftHand.tick();
		body.tick();
		rightHand.tick();
	}
	
	public void start(){
		playerMenu.start();
	}
	
	public void render(Graphics g){
		//draw inventory background
		g.drawImage(Assets.ingame_inventar_background, 0, 0, null);
		
		//draw active inventory places
		leftHand.render(g);
		body.render(g);
		rightHand.render(g);
		
		//draw buttons
		settings.render(g);
		
		//draw hero name text
		Player player = handler.getGame().getGameState().getTurnPlayer();
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
		playerMenu.render(g);
	}

	//GETTERS & SETTERS
	public TaskMenu getTaskMenu() {
		return taskMenu;
	}

	public PlayerMenu getPlayerMenu() {
		return playerMenu;
	}
}
