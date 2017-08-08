package states;

import java.awt.Graphics;

import gfx.Assets;
import main.Handler;
import main.Settings;
import ui.MyUIImageButton;

public class MenuState extends State implements Settings{
	
	private MyUIImageButton start_btn, quit_btn;

	public MenuState(Handler handler){
		super(handler);
		initButtons();
	}
	
	private void initButtons() {
		start_btn = new MyUIImageButton(handler, 50, 300, Assets.start_btn){
			@Override
			public void initAction(){
				State.setState(handler.getGame().gameOptionsState);
			}
		};
		
		quit_btn = new MyUIImageButton(handler, 50, 400, Assets.quit_btn){
			@Override
			public void initAction(){
				System.exit(0);
			}
		};
	}

	@Override
	public void tick() {
		start_btn.tick();
		quit_btn.tick();
	}

	@Override
	public void render(Graphics g) {
		//draw background
		g.drawImage(Assets.main_background, 0, 0, WIDTH, HEIGHT, null);
		
		//draw buttons
		start_btn.render(g);
		quit_btn.render(g);
	}
}
