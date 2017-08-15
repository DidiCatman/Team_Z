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

	public Inventory(Handler handler){
		this.handler = handler;
		taskMenu = new TaskMenu(handler);
		
		initButtons();
	}
	
	private void initButtons() {
		settings = new UIImageButton(handler, 292, 560, Assets.settings){
			@Override
			public void initAction(){
				System.out.println(WORDS[handler.getGame().getLanguage()][2]);
			}
		};
	}

	public void tick(){
		settings.tick();
		taskMenu.tick();
	}
	
	public void render(Graphics g){
		//draw inventory backgrounds
		g.drawImage(Assets.ingame_inventar_background, 0, 0, null);
		g.drawImage(Assets.hands_inventar_background, 4, 505, Assets.hands_inventar_background.getWidth(), Assets.hands_inventar_background.getHeight(), null);
		g.drawImage(Assets.hands_inventar_background, 400, 505, Assets.hands_inventar_background.getWidth(), Assets.hands_inventar_background.getHeight(), null);
		g.drawImage(Assets.hands_inventar_background, WIDTH - Assets.hands_inventar_background.getWidth() - 4, 505, Assets.hands_inventar_background.getWidth(), Assets.hands_inventar_background.getHeight(), null);
		
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
		
		//render task menu
		taskMenu.render(g);
	}

	//GETTERS & SETTERS
	public TaskMenu getTaskMenu() {
		return taskMenu;
	}
}
