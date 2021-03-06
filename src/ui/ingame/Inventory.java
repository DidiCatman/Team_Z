package ui.ingame;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;
import main.Translations;
import ui.MyUIImageButton;

public class Inventory implements Settings, Translations{
	
	private Handler handler;
	private MyUIImageButton settings;

	public Inventory(Handler handler){
		this.handler = handler;
		
		initButtons();
	}
	
	private void initButtons() {
		settings = new MyUIImageButton(handler, 300, 562, Assets.settings){
			@Override
			public void initAction(){
				System.out.println(WORDS[handler.getGame().getLanguage()][2]);
			}
		};
	}

	public void tick(){
		settings.tick();
	}
	
	public void render(Graphics g){
		//draw inventory backgrounds
		g.drawImage(Assets.ingame_inventar_background, 0, 500, null);
		g.drawImage(Assets.hands_inventar_background, 0, 500, Assets.hands_inventar_background.getWidth(), Assets.hands_inventar_background.getHeight(), null);
		g.drawImage(Assets.hands_inventar_background, WIDTH - Assets.hands_inventar_background.getWidth(), 500, Assets.hands_inventar_background.getWidth(), Assets.hands_inventar_background.getHeight(), null);
		
		//draw buttons
		settings.render(g);
		
		//draw turns
		String turns = String.valueOf("Turns: " + handler.getGame().getGameState().getTurns());
		Text.drawString(g, turns, 195, 530, false, Color.BLACK, Assets.font28);
	}
}
