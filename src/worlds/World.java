package worlds;

import java.awt.Graphics;

import main.Handler;
import main.Settings;
import tiles.Tile;
import utils.Utils;

public class World implements Settings{

	private Handler handler;
	private int width, height;
	private int spawn_x, spawn_y;
	private int[][] tiles;
	private int map_x_offset, map_y_offset;
	
	public World(Handler handler, String path, int lvl){
		this.handler = handler;
		
		loadWorld(path);
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
	
}