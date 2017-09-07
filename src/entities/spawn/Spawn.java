package entities.spawn;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import entities.zombies.Type;
import gfx.Animation;
import gfx.Assets;
import main.Handler;
import main.Settings;

public class Spawn implements Settings {
	private Handler handler;
	boolean active;
	private int tilex, tiley;
	private int x, y;
	private int pos;
	private BufferedImage img[];
	private Animation animation;
	private Random rnd = new Random();
	
	public Spawn(Handler handler, int tilex, int tiley, int pos){
		this.setHandler(handler);
		active = true;
		this.tilex = tilex;
		this.tiley = tiley;
		this.pos = pos;
		x = 0;
		y = 0;
		img = null;
		getPosition();
		getImage();
		animation = new Animation(250, img);
	}
	
	private void getPosition(){
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		//north
		if(pos == 1){
			x = tilex * TILESIZE + xoff + TILESIZE/2;
			y = tiley * TILESIZE  + yoff;
		//east
		}else if(pos == 2){
			x = tilex * TILESIZE + xoff + TILESIZE;
			y = tiley * TILESIZE  + yoff + TILESIZE/2;
		//south
		}else if(pos == 3){
			x = tilex * TILESIZE + xoff + TILESIZE/2;
			y = tiley * TILESIZE  + yoff + TILESIZE;
		//west
		}else if(pos == 4){
			x = tilex * TILESIZE + xoff;
			y = tiley * TILESIZE  + yoff + TILESIZE/2;
		}
	}
	
	private void getImage(){
		//north
		if(pos == 1){
			img = Assets.spawner_hor;
			x -= img[0].getWidth()/2;
		}
		//east
		if(pos == 2){
			img = Assets.spawner_ver;
			x -= img[0].getWidth();
			y -= img[0].getHeight()/2;
		}
		//south
		if(pos == 3){
			img = Assets.spawner_hor;
			x -= img[0].getWidth()/2;
			y -= img[0].getHeight();
		}
		//west
		if(pos == 4){
			img = Assets.spawner_ver;
			y -= img[0].getHeight()/2;
		}
	}
	
	public void tick(){
		animation.tick();
	}
	
	public void spawn(){
		handler.getGame().getGameState().spawn(tilex, tiley);
		int r = rnd.nextInt(Type.getType().length); // rnd selection of zombie id
		int number = rnd.nextInt(3); //rnd selection of number of zombies
		System.out.println("Spawn " + number + " " + Type.getType()[r].getName() + " zombies @ x:" + tilex + " y:" + tiley);
		for(int i = 0; i < number; i++){
			handler.getGame().getGameState().addZombies(tilex, tiley, Type.getType()[r]);
		}
	}
	
	public void render(Graphics g){
		if(active){
			g.drawImage(animation.getCurrentFrame(), x, y, null);
		}
	}
	
	public boolean getSpawnState(){
		return active;
	}
	
	public int getTilex(){
		return tilex;
	}
	
	public int getTiley(){
		return tiley;
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
