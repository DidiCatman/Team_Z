package tiles;

import gfx.Assets;

public class Tile5 extends Tile {

	public Tile5(int id) {
		super(Assets.tile_west, id, true);
	}
	
	public boolean isClosed(){
		return true;
	}

}
