package tiles;

import gfx.Assets;

public class Tile1 extends Tile {

	public Tile1(int id) {
		super(Assets.tile_single, id, true);
	}
	
	public boolean isClosed(){
		return true;
	}

}
