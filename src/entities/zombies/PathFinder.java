package entities.zombies;

import java.util.ArrayList;
import java.awt.Point;

import main.Handler;
import main.Settings;

public class PathFinder implements Settings{
	
	private Handler handler;
	private int width, height;
	private ArrayList<Point> path;
	
	public PathFinder(Handler handler){
		this.handler = handler;
		width = handler.getWorld().getWidth();
		height = handler.getWorld().getHeight();
		path = new ArrayList<Point>();
	}
	
	public void findPath(int tilex, int tiley){
		System.out.println("NIY - find path for zombies");
		path.add(new Point(tilex,tiley));
	}

}
