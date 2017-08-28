package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import main.Handler;

public class SpawnManager {

	private Handler handler;
	private ArrayList<Spawn> spawn;
	
	public SpawnManager(Handler handler){
		this.setHandler(handler);
		spawn = new ArrayList<Spawn>();
	}
	
	public void tick(){
		for(Spawn s: spawn){
			s.tick();
		}
	}
	
	public void render(Graphics g){
		for(Spawn h: spawn){
			h.render(g);
		}
	}
	
	public void addSpawn(Spawn h){
		spawn.add(h);
	}
	
	public ArrayList<Spawn> getSpawn(){
		return spawn;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
