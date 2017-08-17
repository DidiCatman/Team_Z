package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import main.Handler;

public class HouseManager {
	
	@SuppressWarnings("unused")
	private Handler handler;
	private ArrayList<House> houses;

	public HouseManager(Handler handler){
		this.handler = handler;
		houses = new ArrayList<House>();
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		for(House h: houses){
			h.render(g);
		}
	}
	
	public void addHouse(House h){
		houses.add(h);
	}
	
	//GETTERS & SETTERS
	public ArrayList<House> getHouses() {
		return houses;
	}
	
}
