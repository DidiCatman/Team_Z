package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
		initDoor();
	}

	private void initDoor() {
		//check for doorable tiles
		boolean doorable[] = getDoorableRooms();
		int ran = new Random().nextInt(doorable.length);
		//not the best way...
		while(!doorable[ran]){
			ran = new Random().nextInt(doorable.length);
		}
		
		//choose door from random selected room
		room = ran;
		boolean[] pos = detectDoorPossibility(rooms.get(ran));
		int ran2 = new Random().nextInt(pos.length);
		while(!pos[ran2]){
			ran2 = new Random().nextInt(pos.length);
		}
		door = ran2;
	}

	private boolean[] getDoorableRooms() {
		boolean res[] = new boolean[rooms.size()];
		for(int i = 0; i < rooms.size(); i++){
			res[i] = false;
			Room r = rooms.get(i);
			//if tile id equals road tile
			boolean[] pos = detectDoorPossibility(r);
			for(int x = 0; x < pos.length; x++){
				if(pos[x]){
					res[i] = true;
				}
			}
		}
		return res;
	}
	
	private boolean[] detectDoorPossibility(Room r){
		boolean res[] = new boolean[4];
		Arrays.fill(res, Boolean.FALSE);
		//if tile id equals road tile
		if((r.getTilex() + 1) < 
				handler.getWorld().getWidth()){
			if(handler.getWorld().getTile(r.getTilex() + 1, r.getTiley()).getId() == 8){
				res[0] = true;
			}
		}else if((r.getTilex() - 1) < 0){
			if(handler.getWorld().getTile(r.getTilex() - 1, r.getTiley()).getId() == 8){
				res[1] = true;
			}
		}else if((r.getTiley() + 1) < handler.getWorld().getHeight()){
			if(handler.getWorld().getTile(r.getTilex(), r.getTiley() + 1).getId() == 8){
				res[2] = true;
			}
		}
		else if((r.getTiley() - 1) < 0){
			if(handler.getWorld().getTile(r.getTilex(), r.getTiley() - 1).getId() == 8){
				res[3] = true;
			}
		}
		return res;
	}
	
	public void render(Graphics g){
		BufferedImage img = null;
		int x = 0, y = 0;
		if(door == 0){
			if(open)
				img = Assets.doors_hor_open;
			else
				img = Assets.doors_hor;
			x = rooms.get(room).getTilex() * TILESIZE + TILESIZE; 
			y = rooms.get(room).getTiley() * TILESIZE + TILESIZE/2 - img.getHeight()/2;
		}else if(door == 1){
			if(open)
				img = Assets.doors_hor_open;
			else
				img = Assets.doors_hor;
			x = rooms.get(room).getTilex() * TILESIZE;
			y = rooms.get(room).getTiley() * TILESIZE + TILESIZE/2 - img.getHeight()/2;
		}
		
		if(door == 2){
			if(open)
				img = Assets.doors_ver_open;
			else
				img = Assets.doors_ver;
			x = rooms.get(room).getTilex() * TILESIZE + TILESIZE/2 - img.getHeight()/2; 
			y = rooms.get(room).getTiley() * TILESIZE + TILESIZE;
		}else if(door == 3){
			if(open)
				img = Assets.doors_ver_open;
			else
				img = Assets.doors_ver;
			x = rooms.get(room).getTilex() * TILESIZE + TILESIZE/2 - img.getHeight()/2;
			y = rooms.get(room).getTiley() * TILESIZE;
		}
		
		g.drawImage(img, x, y, null);
	}

	//GETTERS & SETTERS
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
}
