package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Settings;
import utils.Utils;

public class Tile implements Settings{
	
	//STATIC STUFF HERE
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0); //als void tile beim laden der welt genutzt
	public static Tile tile1 = new Tile1(1);
	public static Tile tile2 = new Tile2(2);
	public static Tile tile3 = new Tile3(3);
	public static Tile tile4 = new Tile4(4);
	public static Tile tile5 = new Tile5(5);
	public static Tile tile6 = new Tile6(6);
	
	//CLASS
	protected BufferedImage texture;
	protected final int id;
	private int minitiles[][]; //jede maptile besteht aus 3x3 Minitiles
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
		
		//not implemented at void tile
		if(id != 0){
			String path = String.valueOf("res/tiles/tile" + id + ".txt");
			loadMiniTiles(path);
		}
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, MAPTILESIZE, MAPTILESIZE, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
	private void loadMiniTiles(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		minitiles = new int[MINIMAPTILES][MINIMAPTILES];
		for(int y = 0; y < MINIMAPTILES; y++){
			for(int x = 0; x < MINIMAPTILES; x++){
				minitiles[x][y] = Utils.parseInt(tokens[x + y * MINIMAPTILES]);
			}
		}
	}
}
