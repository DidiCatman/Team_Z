package gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

import main.Settings;
import main.Translations;

public class Assets implements Settings, Translations{
	
	private static final int width = 32, height = 32;
	
	//fonts
	public static Font font28, font23, font18;
	
	//images
	public static BufferedImage grass, wall;
	public static BufferedImage[] start_btn, quit_btn, minus_btn, plus_btn, dropdown_btn;
	public static BufferedImage[] back_btn, back_written_btn, forward_btn, backward_btn;
	public static BufferedImage[] heroes;
	public static BufferedImage[][] activeHeroAnims;

	public static BufferedImage[] zombies;
	public static BufferedImage[] settings, move, attack, search, open_doors, endTurn, trade, inventory, trash_can, trade_icon, trade_hero;

	public static BufferedImage[] en, de;
	public static BufferedImage[] spawner_hor, spawner_ver;
	public static BufferedImage tile_road, tile_single, tile_north, tile_east, tile_south, tile_west, tile_double_hor, tile_double_ver;
	public static BufferedImage tile_road_vertical, tile_road_horizontal, tile_road_left_bottom, tile_road_left_top, tile_road_top_right, tile_road_bottom_right, tile_road_cross_left, tile_road_cross_right, tile_road_cross_all; 
	public static BufferedImage selector, movable_tile, heart, heart_empty, doors_hor, doors_hor_open, doors_ver, doors_ver_open, zombi_spawn_active_hor, zombi_spawn_active_ver;
	public static BufferedImage intro_logo;
	public static BufferedImage main_background, ingame_gui_background, hands_inventar_background, tooltip_background, inventar_background;
	
	public static BufferedImage sword, bow, axe, magic_scroll;
	

	public static void init(){
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font23 = FontLoader.loadFont("res/fonts/slkscr.ttf", 23);
		font18 = FontLoader.loadFont("res/fonts/slkscr.ttf", 18);
		
		//init sheets
		SpriteSheet tiles_old_sheet = new SpriteSheet(ImageLoader.loadImage("/tiles/sheet_1.png"));
		SpriteSheet tiles_spritesheet = new SpriteSheet(ImageLoader.loadImage("/tiles/tilesheet.png"));
		SpriteSheet specialTiles_sheet = new SpriteSheet(ImageLoader.loadImage("/tiles/specialTiles.png"));
		SpriteSheet buttons_sheet = new SpriteSheet(ImageLoader.loadImage("/menues/buttons.png"));
		SpriteSheet buttons_hover_sheet = new SpriteSheet(ImageLoader.loadImage("/menues/buttons_hover.png"));
		SpriteSheet buttons_active_sheet = new SpriteSheet(ImageLoader.loadImage("/menues/buttons_active.png"));
		SpriteSheet countries_sheet = new SpriteSheet(ImageLoader.loadImage("/logo/countries.png"));
		SpriteSheet item_sheet = new SpriteSheet(ImageLoader.loadImage("/items/items.png"));
		SpriteSheet heroes_sheet = new SpriteSheet(ImageLoader.loadImage("/creatures/heroes/heroes_sprites.png"));

		SpriteSheet backgrounds = new SpriteSheet(ImageLoader.loadImage("/backgrounds/backgrounds.png"));
		SpriteSheet intro = new SpriteSheet(ImageLoader.loadImage("/logo/logo.jpg"));

		//init BufferdImages Arrays
		start_btn = new BufferedImage[2];
		quit_btn = new BufferedImage[2];
		minus_btn = new BufferedImage[2];
		plus_btn = new BufferedImage[2];
		dropdown_btn = new BufferedImage[2];
		back_btn = new BufferedImage[2];
		back_written_btn = new BufferedImage[2];
		forward_btn = new BufferedImage[2];
		backward_btn = new BufferedImage[2];
		heroes = new BufferedImage[HERONAMES.length];
		zombies = new BufferedImage[3];
		settings = new BufferedImage[3];
		move = new BufferedImage[3];
		attack = new BufferedImage[3];
		search = new BufferedImage[3];
		open_doors = new BufferedImage[3];
		endTurn = new BufferedImage[3];
		trade = new BufferedImage[3];
		inventory = new BufferedImage[3];
		trash_can = new BufferedImage[3];
		trade_icon = new BufferedImage[3];
		trade_hero = new BufferedImage[3];
		en = new BufferedImage[2];
		de = new BufferedImage[2];
		spawner_hor = new BufferedImage[3];
		spawner_ver = new BufferedImage[3];

		//init menu images
		start_btn[0] = buttons_sheet.crop(128, 0, 128, 64);
		start_btn[1] = buttons_hover_sheet.crop(128, 0, 128, 64);
		quit_btn[0] = buttons_sheet.crop(128, 64, 128, 64);
		quit_btn[1] = buttons_hover_sheet.crop(128, 64, 128, 64);
		
		back_written_btn[0] = buttons_sheet.crop(0, 0, 128, 64);
		back_written_btn[1] = buttons_hover_sheet.crop(0, 0, 128, 64);
		back_btn[0] = buttons_sheet.crop(0, 64, 64, 64);
		back_btn[1] = buttons_hover_sheet.crop(0, 64, 64, 64);
		dropdown_btn[0] = buttons_sheet.crop(64, 96, width, height);
		dropdown_btn[1] = buttons_hover_sheet.crop(64, 96, width, height);
		minus_btn[0] = buttons_sheet.crop(64, 64, width, height);
		minus_btn[1] = buttons_hover_sheet.crop(64, 64, width, height);
		plus_btn[0] = buttons_sheet.crop(96, 64, width, height);
		plus_btn[1] = buttons_hover_sheet.crop(96, 64, width, height);
		forward_btn[0] = buttons_sheet.crop(32, 128, width, height);
		forward_btn[1] = buttons_hover_sheet.crop(32, 128, width, height);
		backward_btn[0] = buttons_sheet.crop(0, 128, width, height);
		backward_btn[1] = buttons_hover_sheet.crop(0, 128, width, height);
		
		//init ingame images
		open_doors[0] = buttons_sheet.crop(0, 256, width * 2, height);
		open_doors[1] = buttons_hover_sheet.crop(0, 256, width * 2, height);
		open_doors[2] = buttons_active_sheet.crop(0, 256, width * 2, height);
		endTurn[0] = buttons_sheet.crop(width * 2, 160, width * 2, height);
		endTurn[1] = buttons_hover_sheet.crop(width * 2, 160, width * 2, height);
		endTurn[2] = buttons_active_sheet.crop(width * 2, 160, width * 2, height);
		trade[0] = buttons_sheet.crop(width * 2, 192, width * 2, height);
		trade[1] = buttons_hover_sheet.crop(width * 2, 192, width * 2, height);
		trade[2] = buttons_active_sheet.crop(width * 2, 192, width * 2, height);
		
		settings[0] = buttons_sheet.crop(96, 96, width, height);
		settings[1] = buttons_hover_sheet.crop(96, 96, width, height);
		settings[2] = buttons_active_sheet.crop(96, 96, width, height);
		inventory[0] = buttons_sheet.crop(0, 288, 144, height);
		inventory[1] = buttons_hover_sheet.crop(0, 288, 144, height);
		inventory[2] = buttons_active_sheet.crop(0, 288, 144, height);
		move[0] = buttons_sheet.crop(0, 160, width * 2, height);
		move[1] = buttons_hover_sheet.crop(0, 160, width * 2, height);
		move[2] = buttons_active_sheet.crop(0, 160, width * 2, height);
		attack[0] = buttons_sheet.crop(0, 192, width * 2, height);
		attack[1] = buttons_hover_sheet.crop(0, 192, width * 2, height);
		attack[2] = buttons_active_sheet.crop(0, 192, width * 2, height);
		search[0] = buttons_sheet.crop(0, 224, width * 2, height);
		search[1] = buttons_hover_sheet.crop(0, 224, width * 2, height);
		search[2] = buttons_active_sheet.crop(0, 224, width * 2, height);
		trash_can[0] = buttons_sheet.crop(64, 224, width, height);
		trash_can[1] = buttons_hover_sheet.crop(64, 224, width, height);
		trash_can[2] = buttons_active_sheet.crop(64, 224, width, height);
		trade_icon[0] = buttons_sheet.crop(96, 224, width, height);
		trade_icon[1] = buttons_hover_sheet.crop(96, 224, width, height);
		trade_icon[2] = buttons_active_sheet.crop(96, 224, width, height);
		trade_hero[0] = buttons_sheet.crop(128, 128, 100, 50);
		trade_hero[1] = buttons_hover_sheet.crop(128, 128, 100, 50);
		trade_hero[2] = buttons_active_sheet.crop(128, 128, 100, 50);
		heart = buttons_sheet.crop(64, 128, width, height);
		heart_empty = buttons_sheet.crop(96, 128, width, height);
		
		//init country logos
		en[0] = countries_sheet.crop(0, 0, width, height);
		en[1] = countries_sheet.crop(0, 32, width, height);
		de[0] = countries_sheet.crop(0, 64, width, height);
		de[1] = countries_sheet.crop(0, 96, width, height);
		
		//init tiles
		grass = tiles_old_sheet.crop(width * 2, 0, width, height);
		
		tile_single = tiles_spritesheet.crop(0, 0, TILESIZE, TILESIZE);
		tile_north = tiles_spritesheet.crop(TILESIZE, 0, TILESIZE, TILESIZE);
		tile_west = tiles_spritesheet.crop(TILESIZE * 2, 0, TILESIZE, TILESIZE);
		tile_south = tiles_spritesheet.crop(0, TILESIZE, TILESIZE, TILESIZE);
		tile_east = tiles_spritesheet.crop(TILESIZE, TILESIZE, TILESIZE, TILESIZE);
		tile_double_hor = tiles_spritesheet.crop(TILESIZE * 2, TILESIZE, TILESIZE, TILESIZE);
		tile_double_ver = tiles_spritesheet.crop(0, TILESIZE * 2, TILESIZE, TILESIZE);
		
		tile_road = tiles_spritesheet.crop(TILESIZE, TILESIZE * 2, TILESIZE, TILESIZE);
		tile_road_vertical = tiles_spritesheet.crop(0, TILESIZE * 3, TILESIZE, TILESIZE); 
		tile_road_horizontal = tiles_spritesheet.crop(TILESIZE, TILESIZE * 3, TILESIZE, TILESIZE); 
		tile_road_left_bottom = tiles_spritesheet.crop(TILESIZE * 2, TILESIZE * 3, TILESIZE, TILESIZE);
		tile_road_left_top = tiles_spritesheet.crop(0, TILESIZE * 4, TILESIZE, TILESIZE);
		tile_road_top_right = tiles_spritesheet.crop(TILESIZE, TILESIZE * 4, TILESIZE, TILESIZE); 
		tile_road_bottom_right = tiles_spritesheet.crop(TILESIZE * 2, TILESIZE * 4, TILESIZE, TILESIZE); 
		tile_road_cross_left = tiles_spritesheet.crop(0, TILESIZE * 5, TILESIZE, TILESIZE); 
		tile_road_cross_right = tiles_spritesheet.crop(TILESIZE, TILESIZE * 5, TILESIZE, TILESIZE); 
		tile_road_cross_all = tiles_spritesheet.crop(TILESIZE * 2, TILESIZE * 5, TILESIZE, TILESIZE);
		
		//init special tiles
		selector = specialTiles_sheet.crop(0, 0, TILESIZE/3, TILESIZE/3);
		movable_tile = specialTiles_sheet.crop(0, TILESIZE/3, TILESIZE/3, TILESIZE/3);
		doors_hor = specialTiles_sheet.crop(0, 54, 12, 21);
		doors_hor_open = specialTiles_sheet.crop(12, 54, 12, 21);
		doors_ver = specialTiles_sheet.crop(24, 54, 21, 12);
		doors_ver_open = specialTiles_sheet.crop(24, 54 + 12, 21, 12);
		zombi_spawn_active_hor = tiles_spritesheet.crop(TILESIZE * 2, TILESIZE * 2 + 24, 48, 21);
		zombi_spawn_active_ver = tiles_spritesheet.crop(TILESIZE * 2 + 48, TILESIZE * 2, 21, 48);
		
		spawner_hor[0] = specialTiles_sheet.crop(0, 78, 48, 21);
		spawner_hor[1] = specialTiles_sheet.crop(0, 99, 48, 21);
		spawner_hor[2] = specialTiles_sheet.crop(0, 120, 48, 21);

		spawner_ver[0] = specialTiles_sheet.crop(0, 141, 21, 48);
		spawner_ver[1] = specialTiles_sheet.crop(21, 141, 21, 48);
		spawner_ver[2] = specialTiles_sheet.crop(0, 189, 21, 48);
		
		//init Backgrounds
		main_background = backgrounds.crop(0, 0, WIDTH, HEIGHT);
		ingame_gui_background = backgrounds.crop(0, HEIGHT, WIDTH, HEIGHT);
		hands_inventar_background = backgrounds.crop(0, HEIGHT * 2, 145, 90);
		tooltip_background = backgrounds.crop(0, 1290, 650, 400);
		inventar_background = backgrounds.crop(0, 1690, 650, 400);
		
		intro_logo = intro.crop(0, 0, WIDTH, HEIGHT);
		
		//init heroes images
		for(int i = 0; i < HERONAMES.length; i++){
			String path = "/creatures/heroes/hero" + (i+1) + ".png";
			heroes[i] = ImageLoader.loadImage(path);
		}
		
		activeHeroAnims = new BufferedImage[HERONAMES.length][2];
		activeHeroAnims[0][0] = heroes_sheet.crop(0, 0, 28, 28);
		activeHeroAnims[0][1] = heroes_sheet.crop(28, 0, 28, 28);
		activeHeroAnims[1][0] = heroes_sheet.crop(0, 28, 28, 28);
		activeHeroAnims[1][1] = heroes_sheet.crop(28, 28, 28, 28);
		activeHeroAnims[2][0] = heroes_sheet.crop(0, 56, 28, 28);
		activeHeroAnims[2][1] = heroes_sheet.crop(28, 56, 28, 28);
		activeHeroAnims[3][0] = heroes_sheet.crop(0, 84, 28, 28);
		activeHeroAnims[3][1] = heroes_sheet.crop(28, 84, 28, 28);
		activeHeroAnims[4][0] = heroes_sheet.crop(0, 112, 28, 28);
		activeHeroAnims[4][1] = heroes_sheet.crop(28, 112, 28, 28);
		activeHeroAnims[5][0] = heroes_sheet.crop(0, 140, 28, 28);
		activeHeroAnims[5][1] = heroes_sheet.crop(28, 140, 28, 28);
		
		//init zombies images
		zombies[0] = ImageLoader.loadImage("/creatures/enemies/zombie.png");
		zombies[1] = ImageLoader.loadImage("/creatures/enemies/tankZombie.png");
		zombies[2] = ImageLoader.loadImage("/creatures/enemies/fastZombie.png");

		//init items
		sword = item_sheet.crop(0, 0, TILESIZE, TILESIZE);
		bow = item_sheet.crop(TILESIZE, 0, TILESIZE, TILESIZE);
		axe = item_sheet.crop(TILESIZE * 2, 0, TILESIZE, TILESIZE);
		magic_scroll = item_sheet.crop(TILESIZE * 3, 0, TILESIZE, TILESIZE);
	}
}
