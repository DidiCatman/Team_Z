package entities.player.actions;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import entities.buildings.House;
import entities.buildings.Room;
import gfx.Assets;
import main.Handler;
import main.Settings;

public class OpenDoor implements Settings{
	
	private Handler handler;
	private ArrayList<Point> openableDoors;
	private boolean chooseDoor;

	public OpenDoor(Handler handler){
		this.handler = handler;
		openableDoors = new ArrayList<Point>();
		chooseDoor = false;
	}
	
	public void tick(){
		getLockedDoors();
		if(openableDoors.size() > 1){
			System.out.println("More than one door to choose");
			chooseDoor = true;
			//if mouse click check if door was choosen
			if(handler.getMouseManager().isLeftPressed()){
				Point mouse = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
				Rectangle rec[] = new Rectangle[openableDoors.size()];
				for(int i = 0; i < openableDoors.size(); i++){
					rec[i] = new Rectangle(
							(int) (openableDoors.get(i).getX() * TILESIZE + handler.getWorld().getMap_x_offset()), 
							(int) (openableDoors.get(i).getY() * TILESIZE + handler.getWorld().getMap_y_offset()), 
							TILESIZE, 
							TILESIZE);
				}
				for(int i = 0; i < openableDoors.size(); i++){
					if(rec[i].contains(mouse)){
						System.out.println("try to open door at [" + (int) rec[i].getX()/TILESIZE + "][" + (int) rec[i].getY()/TILESIZE + "]");
						chooseDoor = false;
						openDoor(openableDoors.get(i));
					}
				}
			}
		}else if(openableDoors.size() == 0){
			System.out.println("no door found to open ");
		}else{
			Point p = openableDoors.get(0);
			System.out.println("open door at [" + p.getX() + "][" + p.getY() + "]");
			openDoor(openableDoors.get(0));
		}
		System.out.println("openable doors: " + openableDoors.size());
	}
	
	private void getLockedDoors(){
		openableDoors = new ArrayList<Point>();
		Point player_pos = handler.getGame().getGameState().getTurnPlayer().getTile();
		ArrayList<House> houses = handler.getGame().getGameState().getHouseManager().getHouses();
		//check all rooms of all houses
		for(int h = 0; h < houses.size(); h++){
			if(!houses.get(h).isOpen()){
				//get room with door
				int room = houses.get(h).getRoom();
				Room r = houses.get(h).getRooms().get(room);
				//if door is on east side
				if(houses.get(h).getDoor() == 1){
					if(r.getTilex() + 1 == player_pos.getX() && r.getTiley() == player_pos.getY()){
						openableDoors.add(new Point(r.getTilex(), r.getTiley()));
					}
				//if door is on west side
				}else if(houses.get(h).getDoor() == 2){
					if(r.getTilex() - 1 == player_pos.getX() && r.getTiley() == player_pos.getY()){
						openableDoors.add(new Point(r.getTilex(), r.getTiley()));
					}
				//if door is on south side
				}else if(houses.get(h).getDoor() == 3){
					if(r.getTiley() + 1 == player_pos.getY() && r.getTilex() == player_pos.getX()){
						openableDoors.add(new Point(r.getTilex(), r.getTiley()));
					}
				//if door is on north side
				}else if(houses.get(h).getDoor() == 4){
					if(r.getTiley() - 1 == player_pos.getY() && r.getTilex() == player_pos.getX()){
						openableDoors.add(new Point(r.getTilex(), r.getTiley()));
					}
				}
			}
		}
	}
	
	private void openDoor(Point p){
		ArrayList<House> houses = handler.getGame().getGameState().getHouseManager().getHouses();
		for(int h = 0; h < houses.size(); h++){
			for(int r = 0; r < houses.get(h).getRooms().size(); r++){
				if(houses.get(h).getRooms().get(r).getTilePoint().equals(p)){
					if(houses.get(h).getRoom() == r){
						//action
						handler.getGame().getGameState().getHouseManager().getHouses().get(h).openHouse();
						handler.getGame().getGameState().getTurnPlayer().decreaseActionPoints();
						handler.getGame().getGameState().setShowOpenDoors(false);
						return;
					}
				}
			}
		}
	}
	
	public void render(Graphics g){
		if(chooseDoor){
			for(Point p: openableDoors){
				g.drawImage(Assets.movable_tile, p.x * TILESIZE + handler.getWorld().getMap_x_offset(), p.y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE, null);
			}
		}
	}
}
