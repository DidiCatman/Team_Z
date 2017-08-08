package ui.ingame;

import java.awt.Graphics;

import gfx.Assets;
import main.Handler;
import ui.MyUIImageButton;

public class TaskMenu {
	
	private Handler handler;
	private MyUIImageButton move, attack, item, end;

	public TaskMenu(Handler handler){
		this.handler = handler;
		initButtons();
	}
	
	private void initButtons() {
		move = new MyUIImageButton(handler, 455, 515, Assets.move){
			@Override
			public void initAction(){
				System.out.println("NIY - move");
			}
		};
		attack = new MyUIImageButton(handler, 530, 515, Assets.attack){
			@Override
			public void initAction(){
				System.out.println("NIY - attack");
			}
		};
		item = new MyUIImageButton(handler, 455, 555, Assets.item){
			@Override
			public void initAction(){
				System.out.println("NIY - item");
			}
		};
		end = new MyUIImageButton(handler, 530, 555, Assets.end_turn){
			@Override
			public void initAction(){
				System.out.println("NIY - end turn");
			}
		};
	}

	public void tick(){
		move.tick();
		attack.tick();
		item.tick();
		end.tick();
	}
	
	public void render(Graphics g){
		move.render(g);
		attack.render(g);
		item.render(g);
		end.render(g);
	}
	
}
