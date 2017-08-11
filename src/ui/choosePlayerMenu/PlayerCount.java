package ui.choosePlayerMenu;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;
import main.Translations;
import states.State;
import ui.UIImageButton;

public class PlayerCount implements Settings, Translations{
	
	private Handler handler;
	private int playerCount;
	private long lastClickTimer, clickCoolDown = 250, clickTimer = clickCoolDown;

	private UIImageButton plus_btn, minus_btn, start_btn;
	
	public PlayerCount(Handler handler){
		this.handler = handler;
		playerCount = 2;
		initButtons();		
	}
	
	private void initButtons() {		
		plus_btn = new UIImageButton(handler, 150, 100, Assets.plus_btn){
			@Override
			public void initAction(){
				if(playerCount >= MAXPLAYERNUMBER){
					playerCount = 1;
				}else{
					playerCount++;
				}
			}
		};
		
		minus_btn = new UIImageButton(handler, 50, 100, Assets.minus_btn){
			@Override
			public void initAction(){
				if(playerCount <= 1){
					playerCount = MAXPLAYERNUMBER;
				}else{
					playerCount--;
				}
			}
		};
		
		start_btn = new UIImageButton(handler, 50, 500, Assets.start_btn){
			@Override
			public void initAction(){
				for(int i = 0; i < playerCount; i++){
					handler.getGame().getGameState().addPlayer(handler.getGame().getGameOptionsState().getHeroChooser().getSelectedHeroIndex()[i]);
				}
				handler.getGame().getGameState().start();
				State.setState(handler.getGame().gameState);
			}
		};
	}
	
	public void tick(){		
		if(handler.getMouseManager().isLeftPressed()){
			//handle click cooldown
			clickTimer += System.currentTimeMillis() - lastClickTimer;
			lastClickTimer = System.currentTimeMillis();
			if(clickTimer < clickCoolDown)
				return;
			
			//set click timer to zero when click is acepted event
			clickTimer = 0;
		}
		
		plus_btn.tick();
		minus_btn.tick();
		start_btn.tick();
	}
	public void render(Graphics g){
		//draw text
		Text.drawString(g, WORDS[handler.getGame().getLanguage()][1], 50, 85, false, Color.WHITE, Assets.font28);
		Text.drawString(g, String.valueOf(playerCount), 105, 125, false, Color.WHITE, Assets.font28);
		
		plus_btn.render(g);
		minus_btn.render(g);
		start_btn.render(g);
	}

	//GETTERS & SETTERS
	public int getPlayerCount() {
		return playerCount;
	}

}
