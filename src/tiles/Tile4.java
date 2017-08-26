package tiles;

import gfx.Assets;

public class Tile4 extends Tile {

	public Tile4(int id) {
		super(Assets.tile_south, id, true);
	}
	
	public boolean isClosed(){
		return true;
	}

}
