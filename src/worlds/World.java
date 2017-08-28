package worlds;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.House;
import entities.Room;
import main.Handler;
import main.Settings;
import tiles.Tile;
import utils.Utils;

public class World implements Settings{

	private Handler handler;
	private int width, height;
	private int spawn_x, spawn_y;
	private int[][] tiles;
	private int spawnnumber;
	private int[] spawnzone_x, spawnzone_y, spawnposition;
	private int map_x_offset, map_y_offset;
	
	public World(Handler handler, String path, int lvl){
		this.handler = handler;
		width = 9;
		height = 6;
		
		loadWorld(path);
		//printHouses();
	}
	
	public void tick(){
	}
	
	public void render(Graphics g){		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				Tile t = getTile(x, y);
				t.render(g, x * TILESIZE + map_x_offset, y * TILESIZE + map_y_offset);
			}
		}
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		
		if(t == null)
			return Tile.grassTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawn_x = Utils.parseInt(tokens[2]);
		spawn_y = Utils.parseInt(tokens[3]);

		map_x_offset = 10;
		map_y_offset = 8;
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
		
		spawnnumber = (tokens.length - (height * width + 4)) / 3;
		spawnzone_x = new int[spawnnumber];
		spawnzone_y = new int[spawnnumber];
		spawnposition = new int[spawnnumber];
		
		for(int i = 0; i < spawnnumber; i++){
			spawnzone_x[i] = Utils.parseInt(tokens[height * width + 4 + 3 * i]);
			spawnzone_y[i] = Utils.parseInt(tokens[height * width + 5 + 3 * i]);
			spawnposition[i] = Utils.parseInt(tokens[height * width + 6 + 3 * i]);
		}
		
//		for(int i = 0; i < spawnnumber; i++){
//		System.out.println("Spawn #" + (i + 1) + " @ x=" + spawnzone_x[i] + " and y=" + spawnzone_y[i] + " (pos:" + spawnposition[i] + ")");
//		}
	}
	
	public void loadHouses(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				ArrayList<Room> rooms = new ArrayList<Room>();
				//detect single house
				if(tiles[x][y] == 1){
					rooms.add(new Room(handler, x, y, 1));
				}
				
				
				if(tiles[x][y] == 4){		//detect south room
					rooms.add(new Room(handler, x, y, 4));
					if((y+1) < height){
						if(tiles[x][y+1] == 7){		//detect middle room
							rooms.add(new Room(handler, x, y+1, 7));
							rooms.add(new Room(handler, x, y+2, 2));
						}else if(tiles[x][y+1] == 2){		//detect north room
							rooms.add(new Room(handler, x, y+1, 2));
						}
					}
				}
				
				if(tiles[x][y] == 3){		//detect west room
					rooms.add(new Room(handler, x, y, 3));
					if((x+1) < width){
						if(tiles[x+1][y] == 6){		//detect middle room
							rooms.add(new Room(handler, x+1, y, 6));
							rooms.add(new Room(handler, x+2, y, 5));
						}else if(tiles[x+1][y] == 5){		//detect east room
							rooms.add(new Room(handler, x+1, y, 5));
						}
					}
				}
				handler.getGame().getGameState().getHouseManager().addHouse(new House(handler, rooms));
			}
		}
		
		handler.getGame().getGameState().getHouseManager().initDoors();
	}
	
	
	
	//GETTERS & SETTERS
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public int getMap_x_offset() {
		return map_x_offset;
	}

	public int getMap_y_offset() {
		return map_y_offset;
	}

	public int getSpawn_x() {
		return spawn_x;
	}

	public void setSpawn_x(int spawn_x) {
		this.spawn_x = spawn_x;
	}

	public int getSpawn_y() {
		return spawn_y;
	}

	public void setSpawn_y(int spawn_y) {
		this.spawn_y = spawn_y;
	}
	
	public int getSpawnnumber(){
		return spawnnumber;
	}
	
	public int[] getSpawnzone_x(){
		return spawnzone_x;
	}
	
	public int[] getSpawnzone_y(){
		return spawnzone_y;
	}
	
	public int[] getSpawnposition(){
		return spawnposition;
	}
}