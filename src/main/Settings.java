package main;

public interface Settings {
	
	public final static int WIDTH = 900;
	public final static int HEIGHT = 600;
	
	public final static int EN = 0;
	public final static int DE = 1;

	public final static int MAPTILESIZE = 243;
	public final static int TILESIZE = 81;
	public final static int ENTITYSIZE = 16;

	public final static int DEFAULT_ATTACK = 1;
	public final static int DEFAULT_ACTIONS = 3;
	public final static int DEFAULT_ZOMBIES_ACTIONS = 1;
	public final static int DEFAULT_PLAYER_HEALTH = 3;
	public final static int DEFAULT_ZOMBIES_HEALTH = 1;
		
	public final static int MAXPLAYERNUMBER = 6;
	public final static int MAXITEMS = 5;

	public final static int TILE_VOID = 0;
	public final static int TILE_SINGLE = 1;
	public final static int TILE_NORTH = 2;
	public final static int TILE_EAST = 3;
	public final static int TILE_SOUTH = 4;
	public final static int TILE_WEST = 5;
	public final static int TILE_DOUBLE_HOR = 6;
	public final static int TILE_DOUBLE_VER = 7;
	public final static int TILE_ROAD = 8;
	
	public final static int DOOR_EAST = 1;
	public final static int DOOR_WEST = 2;
	public final static int DOOR_SOUTH = 3;
	public final static int DOOR_NORTH = 4;
}
