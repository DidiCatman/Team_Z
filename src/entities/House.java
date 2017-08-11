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
	int door; // to store the door direction in the room (east west south north)
	int room;
	boolean open;
	
	public House(Handler handler, ArrayList<Room> rooms){
		this.handler = handler;
		this.rooms = rooms;
		open = false;
	}
	
	public void render(Graphics g){
		BufferedImage img = null;
		int x = 0, y = 0;
		if(door == 1){
			if(open)
				img = Assets.doors_hor_open;
			else
				img = Assets.doors_hor;
			x = rooms.get(room).getTilex() * TILESIZE + TILESIZE + handler.getWorld().getMap_x_offset() - img.getWidth()/2; 
			y = rooms.get(room).getTiley() * TILESIZE + TILESIZE/2 - img.getHeight()/2 + handler.getWorld().getMap_y_offset();
		}else if(door == 2){
			if(open)
				img = Assets.doors_hor_open;
			else
				img = Assets.doors_hor;
			x = rooms.get(room).getTilex() * TILESIZE + handler.getWorld().getMap_x_offset() - img.getWidth()/2;
			y = rooms.get(room).getTiley() * TILESIZE + TILESIZE/2 - img.getHeight()/2 + handler.getWorld().getMap_y_offset();
		}else if(door == 3){
			if(open)
				img = Assets.doors_ver_open;
			else
				img = Assets.doors_ver;
			x = rooms.get(room).getTilex() * TILESIZE + TILESIZE/2 - img.getWidth()/2 + handler.getWorld().getMap_x_offset(); 
			y = rooms.get(room).getTiley() * TILESIZE + TILESIZE - img.getHeight()/2 + handler.getWorld().getMap_y_offset();
		}else if(door == 4){
			if(open)
				img = Assets.doors_ver_open;
			else
				img = Assets.doors_ver;
			x = rooms.get(room).getTilex() * TILESIZE + TILESIZE/2 - img.getWidth()/2 + handler.getWorld().getMap_x_offset();
			y = rooms.get(room).getTiley() * TILESIZE  - img.getHeight() + handler.getWorld().getMap_y_offset();
		}
		
		g.drawImage(img, x, y, null);
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
	
}