package tiles;

import gfx.Assets;

public class Tile3 extends Tile {

	public Tile3(int id) {
		super(Assets.tile_east, id, true);
	}
	
	public boolean isClosed(){
		return true;
	}

}
