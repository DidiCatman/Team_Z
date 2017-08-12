package entities.player;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import gfx.Assets;
import main.Handler;
import main.Settings;

public class PlayerActions implements Settings{
	
	private Handler handler;
	private ArrayList<Point> movableTiles;
	
	public PlayerActions(Handler handler){
		this.handler = handler;
		movableTiles = new ArrayList<Point>();
	}
	
	public void tick(){
		if(handler.getGame().getGameState().isShowMoves()){
			getMovableTiles();
			tickSelector();
		}
		
		if(handler.getGame().getGameState().isShowAttacks()){
			System.out.println("show attacks");
		}
		
		if(handler.getGame().getGameState().isShowSearchables()){
			System.out.println("show items");
		}
	}
	
	private void getMovableTiles() {
		movableTiles = new ArrayList<Point>();
		int width = handler.getWorld().getWidth();
		int height = handler.getWorld().getHeight();
		
		Player p = handler.getGame().getGameState().getTurnPlayer();
		for(int y = 0; y < handler.getWorld().getHeight() * TILESIZE; y++){
			for(int x = 0; x < handler.getWorld().getWidth() * TILESIZE; x++){
				if(!handler.getWorld().getTile(x, y).isClosed()){
					if(p.getTilex() == x && p.getTiley() + 1 == y){
						if(p.getTiley() + 1 < height){
							movableTiles.add(new Point(x,y));
						}
					}else if(p.getTilex() == x && p.getTiley() - 1 == y){
						if(p.getTiley() - 1 >= 0){
							movableTiles.add(new Point(x,y));
						}
					}
					if(p.getTiley() == y && p.getTilex() + 1 == x){
						if(p.getTilex() + 1 < width){
							movableTiles.add(new Point(x,y));
						}
					}else if(p.getTiley() == y && p.getTilex() - 1 == x){
						if(p.getTilex() - 1 >= 0){
							movableTiles.add(new Point(x,y));
						}
					}
				}
			}
		}
	}

	private void tickSelector(){
		if(handler.getMouseManager().isLeftPressed()){
			Point p = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
			
			int x = p.x/TILESIZE;
			int y = p.y/TILESIZE;
			
			//get tile rectangle
			Rectangle rec = new Rectangle(x * TILESIZE + handler.getWorld().getMap_x_offset(), y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE);
			if(rec.contains(p)){
				if(x >= 0 && x < handler.getWorld().getWidth() && y >= 0 && y < handler.getWorld().getHeight()){
					Point temp = new Point(x,y);
					if(movableTiles.contains(temp)){
						handler.getGame().getGameState().getTurnPlayer().setTilex(temp.x);
						handler.getGame().getGameState().getTurnPlayer().setTiley(temp.y);
						handler.getGame().getGameState().getTurnPlayer().decreaseActionPoints();
						return;
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
		
		if(handler.getGame().getGameState().isShowSearchables()){
			
		}
	}
	
	private void renderShowMoveSelector(Graphics g){
		Point p = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
		
		int x = p.x/TILESIZE;
		int y = p.y/TILESIZE;
		
		//get tile rectangle
		Rectangle rec = new Rectangle(x * TILESIZE + handler.getWorld().getMap_x_offset(), y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE);
		if(rec.contains(p)){
			if(x >= 0 && x < handler.getWorld().getWidth() && y >= 0 && y < handler.getWorld().getHeight()){
				g.drawImage(Assets.selector, x * TILESIZE + handler.getWorld().getMap_x_offset(), y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE, null);
			}
		}
	}
	
	private void renderShowMoveFields(Graphics g){
		for(Point p: movableTiles){
			g.drawImage(Assets.movable_tile, p.x * TILESIZE + handler.getWorld().getMap_x_offset(), p.y * TILESIZE + handler.getWorld().getMap_y_offset(), TILESIZE, TILESIZE, null);
		}
	}
	
}
