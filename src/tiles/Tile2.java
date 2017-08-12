package tiles;

import gfx.Assets;

public class Tile2 extends Tile {

	public Tile2(int id) {
		super(Assets.tile_north, id, true);
	}
	
	public boolean isClosed(){
		return true;
	}

}
