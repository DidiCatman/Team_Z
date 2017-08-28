package states;

import java.awt.Graphics;
import java.util.Arrays;

import entities.EntityManager;
import entities.HouseManager;
import entities.Spawn;
import entities.SpawnManager;
import entities.player.Player;
import gfx.Assets;
import main.Handler;
import main.Settings;
import main.Translations;
import ui.ingame.IngameUI;
import worlds.World;

public class GameState extends State implements Settings, Translations{
	
	private World world;
	private int counter;
	private int start_tilex, start_tiley;
	private int[] spawnzone_x, spawnzone_y, spawnposition;
	
	private EntityManager entityManager;
	private HouseManager houseManager;
	private SpawnManager spawnManager;
	private IngameUI ingameUI;
	
	private int turns;
	private boolean[] turnEnded;
	private boolean showMoves, showAttacks, showSearchables;
	
	public GameState(Handler handler){
		super(handler);
		counter = 0;
		entityManager = new EntityManager(handler);
		ingameUI = new IngameUI(handler);
		world = new World(this.handler, "res/worlds/world1.txt", counter);
		handler.setWorld(world);
		start_tilex = handler.getWorld().getSpawn_x();
		start_tiley = handler.getWorld().getSpawn_y();
		
		houseManager = new HouseManager(handler);
		spawnManager = new SpawnManager(handler);
		
		turns = 0;
		turnEnded = new boolean[entityManager.getPlayers().size()];
		showMoves = false;
		showAttacks = false;
		showSearchables = false;
	}
	
	@Override
	public void tick() {
		world.tick();
		entityManager.tick();
		ingameUI.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		//render doors
		houseManager.render(g);
		spawnManager.render(g);
		entityManager.render(g);
		ingameUI.render(g);
	}
	
	//set start values to the game
	public void start(){
		turnEnded = new boolean[entityManager.getPlayers().size()];
		Arrays.fill(turnEnded, Boolean.FALSE);
		handler.getGame().getGameState().getIngameUI().getInventory().getPlayerMenu().start();
		world.loadHouses();
		
		spawnzone_x = handler.getWorld().getSpawnzone_x();
		spawnzone_y = handler.getWorld().getSpawnzone_y();
		spawnposition = handler.getWorld().getSpawnposition();		
		for(int i = 0; i < handler.getWorld().getSpawnnumber(); i++){
			addSpawn(spawnzone_x[i],spawnzone_y[i],spawnposition[i]);
		}
	}
	
	//add player from the choosePlayerMenu
	public void addPlayer(int hero){
		int id = entityManager.getPlayers().size();
		Player p = new Player(handler, start_tilex, start_tiley, DEFAULT_PLAYER_HEALTH, hero, HERONAMES[hero], id, Assets.heroes[hero]);
		entityManager.addPlayer(p);
	}
	
	//add zombie-spawn to tile xy
	public void addSpawn(int x, int y, int pos){
		Spawn s = new Spawn(handler, x, y, pos);
		spawnManager.addSpawn(s);
	}
	
	//set turn values ready for next turn
	public void endTurn(){
		for(int i = 0; i < turnEnded.length; i++){
			if(turnEnded[i] == false){
				turnEnded[i] = true;
				
				showMoves = false;
				showAttacks = false;
				showSearchables = false;
				
				return;
			}
		}

		showMoves = false;
		handler.getGame().getGameState().getIngameUI().getInventory().getTaskMenu().getMove().setActive(false);
		showAttacks = false;
		handler.getGame().getGameState().getIngameUI().getInventory().getTaskMenu().getAttack().setActive(false);
		showSearchables = false;
		handler.getGame().getGameState().getIngameUI().getInventory().getTaskMenu().getSearch().setActive(false);
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
		spawnManager.tick();
		Arrays.fill(turnEnded, Boolean.FALSE);
		for(Player p: entityManager.getPlayers()){
			p.setActionCounter(DEFAULT_ACTIONS);
		}
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

	public boolean isShowSearchables() {
		return showSearchables;
	}

	public void setShowItems(boolean showItems) {
		this.showSearchables = showItems;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public HouseManager getHouseManager(){
		return houseManager;
	}
	
	public IngameUI getIngameUI() {
		return ingameUI;
	}

}
