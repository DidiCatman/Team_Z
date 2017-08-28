package tiles;

import gfx.Assets;

public class Tile3 extends Tile {

	public Tile3(int id) {
		super(Assets.tile_west, id, true);
	}
	
	public boolean isLocked(){
		return true;
	}

}
