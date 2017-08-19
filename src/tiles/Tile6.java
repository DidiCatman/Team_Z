package tiles;

import gfx.Assets;

public class Tile6 extends Tile {

	public Tile6(int id) {
		super(Assets.tile_double_hor, id, true);
	}
	
	public boolean isLocked(){
		return true;
	}

}
