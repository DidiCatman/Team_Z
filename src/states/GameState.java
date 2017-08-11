package states;

import java.awt.Graphics;
import java.util.Arrays;

import entities.EntityManager;
import entities.player.Player;
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
	private boolean showMoves, showAttacks, showItems;
	
	public GameState(Handler handler){
		super(handler);
		counter = 0;
		world = new World(handler, "res/worlds/world0.txt", counter);
		handler.setWorld(world);
		start_tilex = handler.getWorld().getSpawn_x();
		start_tiley = handler.getWorld().getSpawn_y();
		entityManager = new EntityManager(handler);
		ingameUI = new IngameUI(handler);
		taskMenu = new TaskMenu(handler);
		
		turns = 0;
		turnEnded = new boolean[entityManager.getPlayers().size()];
		showMoves = false;
		showAttacks = false;
		showItems = false;
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
	
	//set start values to the game
	public void start(){
		turnEnded = new boolean[entityManager.getPlayers().size()];
		Arrays.fill(turnEnded, Boolean.FALSE);
	}
	
	//add player from the choosePlayerMenu
	public void addPlayer(int hero){
		int id = entityManager.getPlayers().size();
		Player p = new Player(handler, start_tilex, start_tiley, DEFAULT_PLAYER_HEALTH, hero, HERONAMES[hero], id, Assets.heroes[hero]);
		entityManager.addPlayer(p);
	}
	
	//set turn values ready for next turn
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
	
	//get current player
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

	public boolean isShowMoves() {
		return showMoves;
	}

	public void setShowMoves(boolean showMoves) {
		this.showMoves = showMoves;
	}

	public boolean isShowAttacks() {
		return showAttacks;
	}

	public void setShowAttacks(boolean showAttacks) {
		this.showAttacks = showAttacks;
	}

	public boolean isShowItems() {
		return showItems;
	}

	public void setShowItems(boolean showItems) {
		this.showItems = showItems;
	}

}
