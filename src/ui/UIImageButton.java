package ui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Handler;

public class UIImageButton extends UIObject{
	
	private Handler handler;
	private BufferedImage[] images;
	private BufferedImage current_image;
	private int x, y, width, height;
	private Rectangle rec;
	protected boolean active;
	private long lastClickTimer, clickCoolDown = 250, clickTimer = clickCoolDown;


	public UIImageButton(Handler handler, int x, int y, BufferedImage images[]){
		this.handler = handler;
		this.images = images;
		this.x = x;
		this.y = y;
		width = images[0].getWidth();
		height = images[0].getHeight();
		rec = new Rectangle(x, y, width, height);
		current_image = images[0];
		active = false;
	}
	
	public void tick(){
		//Handle Mouse Movement
		Point mouse = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
		
		//Handle hover effect of start button
		if(rec.contains(mouse)){
			current_image = images[1];
		}else{
			current_image = images[0];
		}
		
		if(handler.getMouseManager().isLeftPressed()){
			//handle click cooldown
			clickTimer += System.currentTimeMillis() - lastClickTimer;
			lastClickTimer = System.currentTimeMillis();
			if(clickTimer < clickCoolDown){
				return;
			}
			
			//handle action on start button
			if(rec.contains(mouse)){
				initAction();
			}
			
			//set click timer to zero when click is acepted event
			clickTimer = 0;
		}
		
		if(active){
			current_image = images[2];
		}
	}
	
	public void render(Graphics g){
		g.drawImage(current_image, x, y, width, height, null);
		
	}

	@Override
	public void initAction() {}
	
	//GETTERS & SETTERS
	public void setImages(BufferedImage[] img){
		images = img;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		if(active = false){
			current_image = images[0];
		}
	}
	
}
