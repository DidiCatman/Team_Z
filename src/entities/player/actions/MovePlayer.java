package entities.player.actions;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import entities.House;
import entities.Room;
import entities.player.Player;
import gfx.Assets;
import main.Handler;
import main.Settings;

public class MovePlayer implements Settings{
	
	private Handler handler;
	private ArrayList<Point> movableTiles;
	private int width;
	private int height;

	public MovePlayer(Handler handler){
		this.handler = handler;
		width = handler.getWorld().getWidth();
		height = handler.getWorld().getHeight();
		movableTiles = new ArrayList<Point>();
	}
	
	public void tick(){
		getMovableTiles();
		tickSelector();
	}
	
	private void getMovableTiles() {
		/* get surrounding tiles and check tiles for:
		 * 
		 * case 1: player is on street and tile is street tile
		 * case 2: player is on street and tile is house tile with door
		 * case 3: player is on house tile and tile is house tile
		 * case 4: player is on house tile with door and tile is street
		 */
		
		movableTiles = new ArrayList<Point>();
		Player p = handler.getGame().getGameState().getTurnPlayer();
		ArrayList<Point> tiles = new ArrayList<Point>();
		tiles = getSurroundingTiles();
		
		for(Point pt: tiles){
			//if player is on street (case 1 & 2)
			if(handler.getWorld().getTile(p.getTilex(), p.getTiley()).getId() == TILE_ROAD){
				//if tile is street (case 1)
				if(handler.getWorld().getTile(pt.x, pt.y).getId() == TILE_ROAD){
					movableTiles.add(pt);
				//else if tile is house (case 2)
				}else{
					//check if house has door with correct door side
					House h = null;
					ArrayList<House> houses = handler.getGame().getGameState().getHouseManager().getHouses();
					for(House house: houses){
						for(Room room: house.getRooms()){
							if(room.getTilePoint().equals(pt)){
								h = house;
							}
						}
					}
					
					if(h.isOpen()){
						Room r = h.getRooms().get(h.getRoom());
						if(r.getTilePoint().equals(pt)){
							if(h.getDoor() == DOOR_EAST){
								if(pt.x + 1 == p.getTilex() && pt.y == p.getTiley())
									movableTiles.add(pt);
							}else if(h.getDoor() == DOOR_WEST){
								if(pt.x - 1 == p.getTilex() && pt.y == p.getTiley())
									movableTiles.add(pt);
							}else if(h.getDoor() == DOOR_SOUTH){
								if(pt.y + 1 == p.getTiley() && pt.x == p.getTilex())
									movableTiles.add(pt);
							}else if(h.getDoor() == DOOR_NORTH){
								if(pt.y - 1 == p.getTiley() && pt.x == p.getTilex())
									movableTiles.add(pt);
							}
						}
					}
				}
			//else if player is in house (case 3 & 4)
			}else{
				//get house tiles
				House h_t = null; //house from tile
				House h_p = null; //house from player
				Room r_p = null;  //room from player
				ArrayList<House> houses = handler.getGame().getGameState().getHouseManager().getHouses();
				for(House house: houses){
					for(Room room: house.getRooms()){
						if(room.getTilePoint().equals(pt)){
							h_t = house;
						}
						if(room.getTilePoint().equals(p.getTile())){
							h_p = house;
							r_p = room;
						}
					}
				}

				//case 3:
				if(handler.getWorld().getTile(pt.x, pt.y).getId() != TILE_ROAD){
					//check if player and tile are in same house
					if(h_p != null && h_t != null){
						//check if tiles are in same house
						if(h_t.equals(h_p)){
							movableTiles.add(pt);
						}
					}
				//case 4:
				}else{
					if(h_p != null && r_p != null){
						//check if player is on door tile from house
						if(h_p.getRooms().get(h_p.getRoom()).equals(r_p)){
							//Detect east door at player tile
							if(h_p.getDoor() == DOOR_EAST){
								if(p.getTilex() + 1 == pt.x && p.getTiley() == pt.y){
									movableTiles.add(pt);
								}
							//Detect east door at player tile
							}else if(h_p.getDoor() == DOOR_WEST){
								if(p.getTilex() - 1 == pt.x && p.getTiley() == pt.y){
									movableTiles.add(pt);
								}
							//Detect south door at player tile
							}else if(h_p.getDoor() == DOOR_SOUTH){
								if(p.getTiley() + 1 == pt.y && p.getTilex() == pt.x){
									movableTiles.add(pt);
								}
							//Detect north door at player tile
							}else if(h_p.getDoor() == DOOR_NORTH){
								if(p.getTiley() - 1 == pt.y && p.getTilex() == pt.x){
									movableTiles.add(pt);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private ArrayList<Point> getSurroundingTiles(){
		Player p = handler.getGame().getGameState().getTurnPlayer();
		ArrayList<Point> points = new ArrayList<Point>();
		
		if(p.getTilex() - 1 >= 0)
			points.add(new Point(p.getTilex() - 1, p.getTiley()));
		if(p.getTilex() + 1 < width)
			points.add(new Point(p.getTilex() + 1, p.getTiley()));
		if(p.getTiley() - 1 >= 0)
			points.add(new Point(p.getTilex(), p.getTiley() - 1));
		if(p.getTiley() + 1 < height)
			points.add(new Point(p.getTilex(), p.getTiley() + 1));
		
		return points;
	}

	private void tickSelector(){
		if(handler.getMouseManager().isLeftPressed()){
			Point p = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
			
			int x = p.x/TILESIZE;
			int y = p.y/TILESIZE;
			
			//get tile rectangle
			Rectangle rec = new Rectangle(x * TILESIZE + handler.getWorld().getMap_x_offset(), y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE);
			if(rec.contains(p)){
				if(x >= 0 && x < handler.getWorld().getWidth() && y >= 0 && y < handler.getWorld().getHeight()){
					Point temp = new Point(x,y);
					if(movableTiles.contains(temp)){
						handler.getGame().getGameState().getTurnPlayer().setTilex(temp.x);
						handler.getGame().getGameState().getTurnPlayer().setTiley(temp.y);
						handler.getGame().getGameState().getTurnPlayer().decreaseActionPoints();
						return;
					}
				}
			}
		}
	}
	
	public void render(Graphics g){
		renderShowMoveSelector(g);
		renderShowMoveFields(g);
	}
	
	private void renderShowMoveSelector(Graphics g){
		Point p = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
		
		int x = p.x/TILESIZE;
		int y = p.y/TILESIZE;
		
		//get tile rectangle
		Rectangle rec = new Rectangle(x * TILESIZE + handler.getWorld().getMap_x_offset(), y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE);
		if(rec.contains(p)){
			if(x >= 0 && x < handler.getWorld().getWidth() && y >= 0 && y < handler.getWorld().getHeight()){
				g.drawImage(Assets.selector, x * TILESIZE + handler.getWorld().getMap_x_offset(), y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE, null);
			}
		}
	}
	
	private void renderShowMoveFields(Graphics g){
		for(Point p: movableTiles){
			g.drawImage(Assets.movable_tile, p.x * TILESIZE + handler.getWorld().getMap_x_offset(), p.y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE, null);
		}
	}
}
