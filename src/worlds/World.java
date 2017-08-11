package worlds;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gfx.Assets;
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
	
	private boolean selector;
	
	public World(Handler handler, String path, int lvl){
		this.handler = handler;
		selector = false;
		
		loadWorld(path);
	}
	
	public void tick(){
		selector = handler.getGame().getGameState().isShowMoves();
	}
	
	public void render(Graphics g){		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				Tile t = getTile(x, y);
				t.render(g, x * MAPTILESIZE + map_x_offset, y * MAPTILESIZE + map_y_offset);
				
				//Draw selector
				if(selector){
					Point p = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
					Rectangle rec = new Rectangle(x * TILESIZE + map_x_offset, y * TILESIZE + map_y_offset, TILESIZE, TILESIZE);
					if(rec.contains(p)){
						g.drawImage(Assets.selector, x * TILESIZE + map_x_offset, y * TILESIZE + map_y_offset, null);
					}
				}
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
		
		if(width == 3){
			map_x_offset = 40;
		}else if(width == 2){
			map_x_offset = 160;
		}
		
		map_y_offset = 10;
		
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