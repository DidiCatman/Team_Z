package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Settings;

public class Tile implements Settings{
	
	//STATIC STUFF HERE
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0); //als void tile beim laden der welt genutzt
	public static Tile tile_single = new Tile1(1);
	public static Tile tile_north = new Tile2(2);
	public static Tile tile_east = new Tile3(3);
	public static Tile tile_south = new Tile4(4);
	public static Tile tile_west = new Tile5(5);
	public static Tile tile_double_hor = new Tile6(6);
	public static Tile tile_double_ver = new Tile7(7);
	public static Tile tile_road = new Tile8(8);
	
	public static Tile tile_road_vertical = new Tile9(9);
	public static Tile tile_road_horizontal = new Tile10(10);
	public static Tile tile_road_left_bottom = new Tile11(11);
	public static Tile tile_road_left_top = new Tile12(12);
	public static Tile tile_road_top_right = new Tile13(13);
	public static Tile tile_road_top_bottom = new Tile14(14);
	public static Tile tile_road_cross_left = new Tile15(15);
	public static Tile tile_road_cross_right = new Tile16(16);
	public static Tile tile_road_cross_all = new Tile17(17);
	
	//CLASS
	protected BufferedImage texture;
	protected final int id;
	protected boolean locked;
	
	public Tile(BufferedImage texture, int id, boolean lock){
		this.texture = texture;
		this.id = id;
		this.locked = lock;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILESIZE, TILESIZE, null);
	}
	
	public boolean isLocked(){
		return this.locked;
	}
	
	//GETTERS & SETTERS
	public int getId(){
		return id;
	}
	
	public void openRoom(){
		this.locked = false;
	}
}
