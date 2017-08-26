package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gfx.Assets;
import main.Handler;
import main.Settings;

public class House implements Settings{

	private Handler handler;
	private ArrayList<Room> rooms;
	private int door; // to store the door direction in the room (east west south north)
	private int room; // to store the room with the door
	private boolean open;
	
	public House(Handler handler, ArrayList<Room> rooms){
		this.handler = handler;
		this.rooms = rooms;
		open = false;
	}
	
	public void render(Graphics g){
		BufferedImage img = null;
		int x = 0, y = 0;
		if(door == DOOR_EAST){
			if(open)
				img = Assets.doors_hor_open;
			else
				img = Assets.doors_hor;
			x = rooms.get(room).getTilex() * TILESIZE + TILESIZE + handler.getWorld().getMap_x_offset() - img.getWidth()/2; 
			y = rooms.get(room).getTiley() * TILESIZE + TILESIZE/2 - img.getHeight()/2 + handler.getWorld().getMap_y_offset();
		}else if(door == DOOR_WEST){
			if(open)
				img = Assets.doors_hor_open;
			else
				img = Assets.doors_hor;
			x = rooms.get(room).getTilex() * TILESIZE + handler.getWorld().getMap_x_offset() - img.getWidth()/2;
			y = rooms.get(room).getTiley() * TILESIZE + TILESIZE/2 - img.getHeight()/2 + handler.getWorld().getMap_y_offset();
		}else if(door == DOOR_SOUTH){
			if(open)
				img = Assets.doors_ver_open;
			else
				img = Assets.doors_ver;
			x = rooms.get(room).getTilex() * TILESIZE + TILESIZE/2 - img.getWidth()/2 + handler.getWorld().getMap_x_offset(); 
			y = rooms.get(room).getTiley() * TILESIZE + TILESIZE - img.getHeight()/2 + handler.getWorld().getMap_y_offset();
		}else if(door == DOOR_NORTH){
			if(open)
				img = Assets.doors_ver_open;
			else
				img = Assets.doors_ver;
			x = rooms.get(room).getTilex() * TILESIZE + TILESIZE/2 - img.getWidth()/2 + handler.getWorld().getMap_x_offset();
			y = rooms.get(room).getTiley() * TILESIZE  - img.getHeight()/2 + handler.getWorld().getMap_y_offset();
		}
		
		g.drawImage(img, x, y, null);
	}
	
	public void openHouse(){
		open = true;
		for(Room r: rooms){
			handler.getWorld().getTile(r.getTilex(), r.getTiley()).openRoom();
		}
	}

	//GETTERS & SETTERS
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public void setHandler(Handler handler){
		this.handler = handler;
	}

	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
}
