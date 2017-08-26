package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import main.Handler;
import worlds.World;

public class HouseManager {
	
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
	
	public void initDoors(){
		ArrayList<House> houses = handler.getGame().getGameState().getHouseManager().getHouses();
		World w = handler.getWorld();
		for(int i = 0; i < houses.size(); i++){
			for(int r = 0; r < houses.get(i).getRooms().size(); r++){
				int tx = houses.get(i).getRooms().get(r).getTilex();
				int ty = houses.get(i).getRooms().get(r).getTiley();
				
				//detect east tile
				if(tx + 1 < w.getWidth() && w.getTile(tx + 1, ty).getId() == 8){
					houses.get(i).setRoom(r);
					houses.get(i).setDoor(1);
				//detect east tile	
				}else if(tx - 1 >= 0 && w.getTile(tx - 1, ty).getId() == 8){
					houses.get(i).setRoom(r);
					houses.get(i).setDoor(2);
				//detect north tile
				}else if(ty + 1 < w.getHeight() && w.getTile(tx, ty + 1).getId() == 8){
					houses.get(i).setRoom(r);
					houses.get(i).setDoor(3);
				} //detect south tile
				else if(ty - 1 >= 0 && w.getTile(tx, ty - 1).getId() == 8){
					houses.get(i).setRoom(r);
					houses.get(i).setDoor(4);
				}
			}
		}
	}
	
	//GETTERS & SETTERS
	public ArrayList<House> getHouses() {
		return houses;
	}
	
}
