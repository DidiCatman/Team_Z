package states;

import java.awt.Graphics;
import java.util.Arrays;

import entities.EntityManager;
import entities.Spawn;
import entities.SpawnManager;
import entities.Zombies;
import entities.buildings.HouseManager;
import entities.items.ItemManager;
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
	private ItemManager itemManager;
	private IngameUI ingameUI;
	
	private int turns;
	private boolean[] turnEnded;
	private boolean hasSearched;
	private boolean showMoves, showAttacks, showSearchables, showOpenDoors;
	
	public GameState(Handler handler){
		super(handler);
		counter = 0;
		entityManager = new EntityManager(handler);
		ingameUI = new IngameUI(handler);
		world = new World(this.handler, "res/worlds/world1.txt", counter);
		this.handler.setWorld(world);
		start_tilex = handler.getWorld().getSpawn_x();
		start_tiley = handler.getWorld().getSpawn_y();
		
		houseManager = new HouseManager(handler);
		spawnManager = new SpawnManager(handler);
		itemManager = new ItemManager(handler);
		
		turns = 0;
		turnEnded = new boolean[entityManager.getPlayers().size()];
		hasSearched = false;
		showMoves = false;
		showAttacks = false;
		showSearchables = false;
		showOpenDoors = false;
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
	
	//add zombie-spawn to tile xy
	public void addSpawn(int x, int y, int pos){
		Spawn s = new Spawn(handler, x, y, pos);
		spawnManager.addSpawn(s);
	}
	
	//add player from the choosePlayerMenu
	public void addPlayer(int hero){
		int id = entityManager.getPlayers().size();
		Player p = new Player(handler, start_tilex, start_tiley, DEFAULT_PLAYER_HEALTH, hero, HERONAMES[hero], id, Assets.heroes[hero]);
		entityManager.addPlayer(p);
	}
	
	//add zombie to spawnpoint
	public void addZombies(int tilex, int tiley, int id){
		Zombies z = new Zombies(handler, tilex, tiley, DEFAULT_ZOMBIES_HEALTH, id, Assets.zombies[id]);
		entityManager.addZombies(z);
	}
	
	//calculate zombie movement
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
		initNextRound();
		turns++;
		return (Player) entityManager.getPlayers().get(0);
	}
	
	private void initNextRound(){
		calculateEnemySteps();
		hasSearched = false;
		spawnManager.spawn();
		
		Arrays.fill(turnEnded, Boolean.FALSE);
		for(Player p: entityManager.getPlayers()){
			p.setActionCounter(DEFAULT_ACTIONS);
		}
	}
	
	//set turn values ready for next turn
	public void endTurn(){
		for(int i = 0; i < turnEnded.length; i++){
			if(turnEnded[i] == false){
				turnEnded[i] = true;

				hasSearched = false;
				showMoves = false;
				showAttacks = false;
				showSearchables = false;
				showOpenDoors = false;
				
				return;
			}
		}

		showMoves = false;
		handler.getGame().getGameState().getIngameUI().getInventory().getTaskMenu().getMove().setActive(false);
		showAttacks = false;
		handler.getGame().getGameState().getIngameUI().getInventory().getTaskMenu().getAttack().setActive(false);
		showSearchables = false;
		handler.getGame().getGameState().getIngameUI().getInventory().getTaskMenu().getSearch().setActive(false);
		showSearchables = false;
		handler.getGame().getGameState().getIngameUI().getInventory().getTaskMenu().getOpenDoors().setActive(false);
		hasSearched = false;
	}

	//GETTERS & SETTERS
	public int getTurns(){
		return this.turns;
	}

	public boolean isShowMoves() {
		return showMoves;
	}

	public void setShowMoves(boolean var) {
		this.showMoves = var;
	}

	public boolean isShowAttacks() {
		return showAttacks;
	}

	public void setShowAttacks(boolean var) {
		this.showAttacks = var;
	}

	public boolean isShowSearchables() {
		return showSearchables;
	}

	public void setShowItems(boolean var) {
		this.showSearchables = var;
	}

	public boolean isShowOpenDoors() {
		return showOpenDoors;
	}

	public void setShowOpenDoors(boolean var) {
		this.showOpenDoors = var;
	}

	public boolean hasSearched() {
		return hasSearched;
	}

	public void setHasSearched(boolean hasSearched) {
		this.hasSearched = hasSearched;
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

	public ItemManager getItemManager() {
		return itemManager;
	}

}
