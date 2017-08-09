package states;

import java.awt.Graphics;
import java.util.Arrays;

import entities.EntityManager;
import entities.Player;
import gfx.Assets;
import main.Handler;
import main.Settings;
import main.Translations;
import ui.ingame.IngameUI;
import ui.ingame.TaskMenu;
import worlds.World;

public class GameState extends State implements Settings, Translations{
	
	private World world;
	private int counter;
	private int start_tilex, start_tiley;
	
	private EntityManager entityManager;
	private IngameUI ingameUI;
	private TaskMenu taskMenu;
	
	private int turns;
	private boolean[] turnEnded;
	
	public GameState(Handler handler){
		super(handler);
		counter = 0;
		world = new World(handler, "res/worlds/world0.txt", counter);
		handler.setWorld(world);
		entityManager = new EntityManager(handler);
		start_tilex = 2;
		start_tiley = 2;
		turns = 0;
		ingameUI = new IngameUI(handler);
		taskMenu = new TaskMenu(handler);
		turnEnded = new boolean[entityManager.getPlayers().size()];
	}
	
	@Override
	public void tick() {
		world.tick();
		entityManager.tick();
		ingameUI.tick();
		taskMenu.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		entityManager.render(g);
		ingameUI.render(g);
		taskMenu.render(g);
	}
	
	public void start(){
		turnEnded = new boolean[entityManager.getPlayers().size()];
		Arrays.fill(turnEnded, Boolean.FALSE);
	}
	
	public void addPlayer(int hero){
		int id = entityManager.getPlayers().size();
		Player p = new Player(handler, start_tilex, start_tiley, DEFAULT_PLAYER_HEALTH, hero, HERONAMES[hero], id, Assets.heroes[hero]);
		entityManager.addPlayer(p);
	}
	
	public void endTurn(){
		for(int i = 0; i < turnEnded.length; i++){
			if(turnEnded[i] == false){
				turnEnded[i] = true;
				return;
			}
		}
	}
	
	private void calculateEnemySteps(){
		System.out.println("NIY - Calculate enemy steps");
	}
	
	public Player getTurnPlayer(){
		for(int i = 0; i < turnEnded.length; i++){
			if(turnEnded[i] == false){
				return entityManager.getPlayers().get(i);
			}
		}
		
		//end of round
		calculateEnemySteps();
		Arrays.fill(turnEnded, Boolean.FALSE);
		turns++;
		return (Player) entityManager.getPlayers().get(0);
	}
	
	//GETTERS & SETTERS
	public int getTurns(){
		return this.turns;
	}

}
