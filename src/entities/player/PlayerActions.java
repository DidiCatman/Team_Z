package entities.player;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import gfx.Assets;
import main.Handler;
import main.Settings;
import tiles.Tile;

public class PlayerActions implements Settings{
	
	private Handler handler;
	private ArrayList<Rectangle> movableTiles;
	
	public PlayerActions(Handler handler){
		this.handler = handler;
		this.movableTiles = new ArrayList<Rectangle>();
	}
	
	public void tick(){
		if(handler.getGame().getGameState().isShowMoves()){
			tickSelector();
		}
		
		if(handler.getGame().getGameState().isShowAttacks()){
			System.out.println("show attacks");
		}
		
		if(handler.getGame().getGameState().isShowItems()){
			System.out.println("show items");
		}
	}
	
	private void tickSelector(){
		if(handler.getMouseManager().isLeftPressed()){
			for(int y = 0; y < handler.getWorld().getHeight() * MINIMAPTILES; y++){
				for(int x = 0; x < handler.getWorld().getWidth() * MINIMAPTILES; x++){
					//Draw selector
					Point p = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
					Rectangle rec = new Rectangle(x * TILESIZE + handler.getWorld().getMap_x_offset(), y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE);
					if(rec.contains(p)){
						Tile t = handler.getWorld().getTile(x/3, y/3);
						
						System.out.println(t.getMinitiles()[x%3][y%3]);
					}
				}
			}
		}
	}

	public void render(Graphics g){
		if(handler.getGame().getGameState().isShowMoves()){
			renderShowMoveSelector(g);
			renderShowMoveFields(g);
		}
		
		if(handler.getGame().getGameState().isShowAttacks()){
			
		}
		
		if(handler.getGame().getGameState().isShowItems()){
			
		}
	}
	
	private void renderShowMoveSelector(Graphics g){
		for(int y = 0; y < handler.getWorld().getHeight() * MINIMAPTILES; y++){
			for(int x = 0; x < handler.getWorld().getWidth() * MINIMAPTILES; x++){
				//Draw selector
				Point p = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
				Rectangle rec = new Rectangle(x * TILESIZE + handler.getWorld().getMap_x_offset(), y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE);
				if(rec.contains(p)){
					g.drawImage(Assets.selector, x * TILESIZE + handler.getWorld().getMap_x_offset(), y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE, null);
				}
			}
		}
	}
	
	private void renderShowMoveFields(Graphics g){
		Player p = handler.getGame().getGameState().getTurnPlayer();
		int xoff = handler.getWorld().getMap_x_offset();
		int yoff = handler.getWorld().getMap_y_offset();
		
		int[][] array = handler.getWorld().getTile(p.getTilex()/3, p.getTiley()/3).getMinitiles();

		for(int y = 0; y < MINIMAPTILES; y++){
			for(int x = 0; x < MINIMAPTILES; x++){
				if(array[x][y] == 0){
					int tilex = (p.getTilex()/3) * MAPTILESIZE + x * TILESIZE + xoff;
					int tiley = (p.getTiley()/3) * MAPTILESIZE + y * TILESIZE + yoff;
					System.out.println("Movable Tile @ " + tilex + " | " + tiley);
					movableTiles.add(new Rectangle(tilex, tiley, TILESIZE, TILESIZE));
				}
			}
		}
		
		for(Rectangle rec: movableTiles){
			g.drawImage(Assets.movable_tile, rec.x, rec.y, rec.width, rec.height, null);
		}
	}
	
}
