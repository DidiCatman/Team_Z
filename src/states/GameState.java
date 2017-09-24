package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

import entities.EntityManager;
import entities.spawn.Spawn;
import entities.spawn.SpawnManager;
import entities.zombies.Zombies;
import entities.zombies.PathFinder;
import entities.zombies.Type;
import entities.buildings.HouseManager;
import entities.items.ItemManager;
import entities.player.Player;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import main.Settings;
import main.Translations;
import ui.ingame.GUI;
import worlds.World;

public class GameState extends State implements Settings, Translations{
	
	private World world;
	private int counter;
	private int start_tilex, start_tiley;
	private int[] spawnzone_x, spawnzone_y, spawnposition;
	private Random rnd = new Random();
	private Point nextStep;
	
	private EntityManager entityManager;
	private HouseManager houseManager;
	private SpawnManager spawnManager;
	private ItemManager itemManager;
	private GUI gui;
	private PathFinder pathFinder;
	
	private int turns;
	private boolean[] turnEnded;
	private boolean hasSearched;
	private boolean showMoves, showAttacks, showSearchables, showOpenDoors, showInventory, showTradeInventor;
	
	public GameState(Handler handler){
		super(handler);
		counter = 0;
		entityManager = new EntityManager(handler);
		gui = new GUI(handler);
		world = new World(this.handler, "res/worlds/world1.txt", counter);
		this.handler.setWorld(world);
		start_tilex = handler.getWorld().getSpawn_x();
		start_tiley = handler.getWorld().getSpawn_y();
				
		houseManager = new HouseManager(handler);
		spawnManager = new SpawnManager(handler);
		itemManager = new ItemManager(handler);
		
		pathFinder = new PathFinder(handler);
		
		turns = 0;
		turnEnded = new boolean[entityManager.getPlayers().size()];
		hasSearched = false;
		showMoves = false;
		showAttacks = false;
		showSearchables = false;
		showOpenDoors = false;
		showInventory = false;
		showTradeInventor = false;
	}
	
	@Override
	public void tick() {
		world.tick();
		entityManager.tick();
		spawnManager.tick();
		gui.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		houseManager.render(g);
		spawnManager.render(g);
		entityManager.render(g);
		gui.render(g);
		
		//draw numeric value for each tile (for debugging)
//		int number = 0;
//		for(int y = 0; y < handler.getWorld().getHeight(); y++){
//			for(int x = 0; x < handler.getWorld().getWidth(); x++){
//				Text.drawString(g, String.valueOf(number), x * TILESIZE + handler.getWorld().getMap_x_offset() + TILESIZE/2, y * TILESIZE + handler.getWorld().getMap_y_offset() + TILESIZE/2, true, Color.BLACK, Assets.font18);
//				number++;
//			}
//		}
	}
	
	//set start values to the game
	public void start(){
		turnEnded = new boolean[entityManager.getPlayers().size()];
		Arrays.fill(turnEnded, Boolean.FALSE);
		world.loadHouses();
		pathFinder.makeGraph();
		pathFinder.makeConnections();
		
		spawnzone_x = handler.getWorld().getSpawnzone_x();
		spawnzone_y = handler.getWorld().getSpawnzone_y();
		spawnposition = handler.getWorld().getSpawnposition();		
		for(int i = 0; i < handler.getWorld().getSpawnnumber(); i++){
			createSpawn(spawnzone_x[i],spawnzone_y[i],spawnposition[i]);
		}
		handler.getGame().getGameState().getGUI().start();
	}
	
	//add zombie-spawn to tile xy
	public void createSpawn(int x, int y, int pos){
		Spawn s = new Spawn(handler, x, y, pos);
		spawnManager.addSpawn(s);
	}
	
	//spawn zombie at tile xy	
	public void spawn(int tilex, int tiley){
		int r = rnd.nextInt(Type.getType().length); // rnd selection of zombie id
		int number = rnd.nextInt(3); //rnd selection of number of zombies
		System.out.println("spawn " + number + " " + Type.getType()[r].getName() + " zombies @ x:" + tilex + " y:" + tiley);
		for(int i = 0; i < number; i++){
		addZombies(tilex, tiley, Type.getType()[r]);
		}
	}
	
	//add player from the choosePlayerMenu
	public void addPlayer(int hero){
		int id = entityManager.getPlayers().size();
		Player p = new Player(handler, start_tilex, start_tiley, DEFAULT_PLAYER_HEALTH, hero, HERONAMES[hero], id, Assets.heroes[hero]);
		entityManager.addPlayer(p);
	}
	
	//add zombie to spawnpoint
	public void addZombies(int tilex, int tiley, Type type){
		int id = entityManager.getZombies().size();
		Zombies z = new Zombies(handler, tilex, tiley, id, type);
		entityManager.addZombies(z);
	}
	
	public void moveZombies(){
		/* calculate next step for all zombies to noisy tile
		 * NIY - go to player on sight
		 * NIY - behavior when there are two path of equal length 
		 */
		for(Zombies z: entityManager.getZombies()){
			for(int i = 0; i < z.getType().getActions(); i++){
				if(!z.getTile().equals(getNoisyTile())){
				nextStep = pathFinder.findPath(z.getTile(), getNoisyTile());
				z.move(nextStep.x, nextStep.y);
				}else{
					System.out.println("NIY - zombie #" + (z.getID() + 1) + " attacks");
				}
			}
		}
		
		//only for debugging and pathFinding 4 all tiles
//		for(int y = 0; y < handler.getWorld().getHeight(); y++){
//			for(int x = 0; x < handler.getWorld().getWidth(); x++){
//				pathFinder.findPath(new Point(x,y), getNoisyTile());
//			}
//		}
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
	
	// return tile with most noise
	public Point getNoisyTile(){
		Point noisyTile = new Point(start_tilex, start_tiley); // NIY - just returns player spawnpoint
		return noisyTile;
	}
	
	private void initNextRound(){
		hasSearched = false;
		
		moveZombies();
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
				showInventory = false;
				showTradeInventor = false;
				
				return;
			}
		}

		showMoves = false;
		gui.getTaskMenu().getMove().setActive(false);
		showAttacks = false;
		gui.getTaskMenu().getAttack().setActive(false);
		showSearchables = false;
		gui.getTaskMenu().getSearch().setActive(false);
		showOpenDoors = false;
		gui.getTaskMenu().getOpenDoors().setActive(false);
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

	public boolean isShowInventory() {
		return showInventory;
	}

	public void setShowInventory(boolean showInventory) {
		this.showInventory = showInventory;
		if(showInventory){
			gui.getInventory().setActive();
		}
	}

	public boolean isShowTradeInventory() {
		return showTradeInventor;
	}

	public void setShowTradeInventor(boolean showTradeInventor) {
		this.showTradeInventor = showTradeInventor;
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
	
	public PathFinder getPathFinder(){
		return pathFinder;
	}
	
	public HouseManager getHouseManager(){
		return houseManager;
	}
	
	public GUI getGUI() {
		return gui;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

}
