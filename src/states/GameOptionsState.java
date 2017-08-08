package states;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import main.Handler;
import main.Settings;
import ui.MyUIImageButton;
import ui.choosePlayerMenu.HeroChooser;
import ui.choosePlayerMenu.PlayerCount;

public class GameOptionsState extends State implements Settings{
	
	private PlayerCount playerCount;
	private HeroChooser heroChooser;
	private MyUIImageButton countries;

	public GameOptionsState(Handler handler) {
		super(handler);
		playerCount = new PlayerCount(handler);
		heroChooser = new HeroChooser(handler);
		
		countries = new MyUIImageButton(handler, WIDTH - 50, 20, Assets.en){
			@Override
			public void initAction(){
				if(handler.getGame().getLanguage() == 0){
					handler.getGame().setLanguage(1);
					countries.setImages(Assets.de);
				}else{
					handler.getGame().setLanguage(0);
					countries.setImages(Assets.en);
				}
			}
		};
	}

	@Override
	public void tick() {
		playerCount.tick();
		heroChooser.tick();
		
		countries.tick();
	}

	@Override
	public void render(Graphics g) {
		//draw background
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		playerCount.render(g);
		heroChooser.render(g);
		
		countries.render(g);
	}

	//GETTERS & SETTERS
	public PlayerCount getPlayerCount() {
		return playerCount;
	}

	public HeroChooser getHeroChooser() {
		return heroChooser;
	}
}
