package tiles;

import gfx.Assets;

public class Tile5 extends Tile {

	public Tile5(int id) {
		super(Assets.tile_east, id, true);
	}
	
	public boolean isLocked(){
		return true;
	}

}
