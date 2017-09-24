package entities.zombies;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import main.Handler;
import main.Settings;

public class PathFinder implements Settings{
	
	private Handler handler;
	private int width, height;
	private int n;
	private LinkedList<Integer> adjList[];
	private Queue<Integer> q = new LinkedList<Integer>();
	private ArrayList<Integer> path, tempPath, houseID;
	
	public PathFinder(Handler handler){
		this.handler = handler;
		width = handler.getWorld().getWidth();
		height = handler.getWorld().getHeight();
		path = new ArrayList<Integer>();
		tempPath = new ArrayList<Integer>();
		houseID = new ArrayList<Integer>(Arrays.asList(TILE_NORTH, TILE_EAST, TILE_SOUTH, TILE_WEST, TILE_DOUBLE_HOR, TILE_DOUBLE_VER));
	}
	
	//creating adjacency list for each vertex
	@SuppressWarnings("unchecked")
	public void makeGraph(){
		n = width * height;
		adjList = new LinkedList[n];
		
		for (int i = 0; i < n; i++){
			adjList[i] = new LinkedList<Integer>();
		}
		
		System.out.println("created adjacency list with " + n + " vertices");
	}
	
	//create connections between verices
	public void makeConnections(){
		for (int i = 0; i < width * height; i++){
			int x = i % width;
			int y = i / width;
			int id = handler.getWorld().getTile(x, y).getId();
			//connect street tiles
			if(id == TILE_ROAD){
				//east
				if(i + 1 < (y + 1) * width && handler.getWorld().getTile(x + 1, y).getId() == TILE_ROAD){
					addEdgeToGraph(i, i + 1);
				}
				//south
				if(i + width < width * height && handler.getWorld().getTile(x, y + 1).getId() == TILE_ROAD){
					addEdgeToGraph(i, i + width);
				}
				//west
				if(i - 1 >= y * width && handler.getWorld().getTile(x - 1, y).getId() == TILE_ROAD){
					addEdgeToGraph(i, i - 1);
				}
				//north
				if(i - width >= 0 && handler.getWorld().getTile(x, y - 1).getId() == TILE_ROAD){
					addEdgeToGraph(i, i - width);
				}
			}
			//connect house tiles
			if(houseID.contains(id)){
				//east
				if(i + 1 < (y + 1) * width && houseID.contains(handler.getWorld().getTile(x + 1, y).getId())){
					if(id == TILE_EAST || id == TILE_DOUBLE_HOR){
						addEdgeToGraph(i, i + 1);
					}
				}
				//south
				if(i + width < width * height && houseID.contains(handler.getWorld().getTile(x, y + 1).getId())){
					if(id == TILE_SOUTH || id == TILE_DOUBLE_VER){
						addEdgeToGraph(i, i + width);
					}
				}
				//west
				if(i - 1 >= y * width && houseID.contains(handler.getWorld().getTile(x - 1, y).getId())){
					if(id == TILE_WEST || id == TILE_DOUBLE_HOR){
						addEdgeToGraph(i, i - 1);
					}
				}
				//north
				if(i - width >= 0 && houseID.contains(handler.getWorld().getTile(x, y - 1).getId())){
					if(id == TILE_NORTH || id == TILE_DOUBLE_VER){
						addEdgeToGraph(i, i - width);
					}
				}
			}
		}
	}
	
	//adding edges to graph
	public void addEdgeToGraph(int u, int v){
		adjList[u].add(v);
//		System.out.println("connect " + u + " to " + v);
	}
	
	//Breath-first travesal function
	public void BFtraversal(int v, int w, boolean[] visited){
		path.clear();
		tempPath.clear();
		q.clear();
		
		q.add(v); //add start vertex to queue
		visited[v] = true;
		
		int k;
		
		while(!q.isEmpty()){
			k = q.poll(); //remove first element from queue
			
			path.add(k); //add vertex to path list
			
			if(k == w){
				break; //break loop when end vertex is reached
			}
			//iterate through adjacency list of vertex k
			Iterator<Integer> i = adjList[k].listIterator();
			int j;
			
			while(i.hasNext()){
				j = i.next();
				if(visited[j] != true){
					//if child found without visiting then child will be added to queue
					q.add(j);
					visited[j] = true;
				}
			}
		}
		
		//backtrace the path if more than 1 vertex; ignore other branches
		if(path.size() > 1){
			tempPath.add(path.get(path.size() - 1));
			int j = path.get(path.size() - 1);
			
			for(int i = path.size() - 2; i >= 0; i--){
				if(adjList[j].contains(path.get(i))){
					tempPath.add(path.get(i));
					j = path.get(i);
				}
			}
			
			path.clear();
			
			//invert tempPath list and write to path
			for(int i = tempPath.size() - 1; i >= 0; i--){
				path.add(tempPath.get(i));
			}
		}

//		for(Integer i : path){
//			System.out.print(i + " ");
//		}
//		System.out.print("\n");
	}
	
	//Breath-first search
	public void BFsearch(int v, int w){
		boolean visited[] = new boolean[n];
		
		BFtraversal(v, w, visited);
	}
	
	public void connectTiles(Point tile1, Point tile2){
		int v = tile1.x + tile1.y * width;
		int w = tile2.x + tile2.y * width;
		
		addEdgeToGraph(v, w);
		addEdgeToGraph(w, v);
	}
	
	public Point findPath(Point start, Point end){
		int v = start.x + start.y * width;
		int w = end.x + end.y * width;
		
//		System.out.println("find path for zombies @ Tile " + start.x + "," + start.y);
		
		BFsearch(v, w);
		
		int x = start.x;
		int y = start.y;
		if(path.size() > 1){
			x = path.get(1) % width;
			y = path.get(1) / width;
		}
		
		return new Point(x,y);
	}

}
