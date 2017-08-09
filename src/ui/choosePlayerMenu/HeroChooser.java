package ui.choosePlayerMenu;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;
import main.Translations;
import ui.MyUIImageButton;

public class HeroChooser implements Settings, Translations{
	
	private Handler handler;
	private int hero_num;
	private MyUIImageButton[] forward, backward;
	private int[] selectedHeroIndex;
	
	public HeroChooser(Handler handler){
		this.handler = handler;
		initButtons();
	}
	
	private void initButtons() {
		forward = new MyUIImageButton[MAXPLAYERNUMBER];
		backward = new MyUIImageButton[MAXPLAYERNUMBER];
		selectedHeroIndex = new int[MAXPLAYERNUMBER];
		
		for(int i = 0; i < MAXPLAYERNUMBER; i++){
			int x = i;
			forward[i] = new MyUIImageButton(handler, 630, 100 + i * 40, Assets.forward_btn){
				@Override
				public void initAction(){
					if(selectedHeroIndex[x] >= HERONAMES.length - 1){
						selectedHeroIndex[x] = 0;
					}else{
						selectedHeroIndex[x]++;
					}
				}
			};
		}
		
		for(int i = 0; i < MAXPLAYERNUMBER; i++){
			int x = i;
			backward[i] = new MyUIImageButton(handler, 435, 100 + i * 40, Assets.backward_btn){
				@Override
				public void initAction(){
					if(selectedHeroIndex[x] <= 0){
						selectedHeroIndex[x] = HERONAMES.length - 1;
					}else{
						selectedHeroIndex[x]--;
					}
				}
			};
			selectedHeroIndex[i] = 0;
		}
	}

	public void tick(){
		//get hero number from PlayerCount class
		hero_num = handler.getGame().getGameOptionsState().getPlayerCount().getPlayerCount();
		
		for(int i = 0; i < hero_num; i++){
			forward[i].tick();
			backward[i].tick();
		}
	}
	
	public void render(Graphics g){
		//draw text
		Text.drawString(g, WORDS[handler.getGame().getLanguage()][3], 300, 85, false, Color.WHITE, Assets.font28);
		
		for(int i = 0; i < hero_num; i++){
			Text.drawString(g, String.valueOf(WORDS[handler.getGame().getLanguage()][0] + (i + 1) + ":"), 300, 125 + i * 40, false, Color.WHITE, Assets.font28);
			backward[i].render(g);
			Text.drawString(g, HERONAMES[selectedHeroIndex[i]], 475, 125 + i * 40, false, Color.white, Assets.font28);
			forward[i].render(g);
		}
	}

	//GETTERS & SETTERS
	public int[] getSelectedHeroIndex() {
		return selectedHeroIndex;
	}
}
