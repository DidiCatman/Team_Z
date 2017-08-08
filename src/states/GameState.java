package states;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import entities.Entity;
import entities.Player;
import gfx.Assets;
import main.Handler;
import main.Settings;
import ui.ingame.IngameUI;
import ui.ingame.TaskMenu;
import worlds.World;

public class GameState extends State implements Settings{
	
	private World world;
	private int counter;
	private int start_tilex, start_tiley;
	
	private ArrayList<Entity> players;
	private IngameUI ingameUI;
	private TaskMenu taskMenu;
	
	private int turns;
	private boolean[] turnEnded;
	
	public GameState(Handler handler){
		super(handler);
		counter = 0;
		world = new World(handler, "res/worlds/world0.txt", counter);
		handler.setWorld(world);
		start_tilex = 2;
		start_tiley = 2;
		turns = 0;
		players = new ArrayList<Entity>();
		ingameUI = new IngameUI(handler);
		taskMenu = new TaskMenu(handler);
	}
	
	@Override
	public void tick() {
		world.tick();
		for(Entity e: players){
			e.tick();
		}
		ingameUI.tick();
		taskMenu.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		for(Entity e: players){
			e.render(g);
		}
		ingameUI.render(g);
		taskMenu.render(g);
	}
	
	public void start(){
		turnEnded = new boolean[players.size()];
		Arrays.fill(turnEnded, Boolean.FALSE);
	}
	
	public void addPlayer(int hero){
		players.add(new Player(handler, start_tilex, start_tiley, DEFAULT_PLAYER_HEALTH, hero, Assets.heroes[hero])); 
	}
	
	//GETTERS & SETTERS
	public int getTurns(){
		return this.turns;
	}

}
