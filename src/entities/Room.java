package entities;

import java.awt.Point;

import main.Handler;

public class Room {
	
	@SuppressWarnings("unused")
	private Handler handler;
	private int tilex, tiley;
	private int id;
	private boolean looted;
	//private ArrayList<Item> items;

	public Room(Handler handler, int x, int y, int id){
		this.handler = handler;
		this.tilex = x;
		this.tiley = y;
		this.id = id;
		looted = false;
	}
	
	public void tick(){
		
	}

	//GETTERS & SETTERS
	public boolean isLooted() {
		return looted;
	}

	public void setLooted(boolean looted) {
		this.looted = looted;
	}

	public int getTilex() {
		return tilex;
	}

	public int getTiley() {
		return tiley;
	}

	public int getId() {
		return id;
	}
	
	public Point getTilePoint(){
		return new Point(tilex, tiley);
	}
}
