package gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

import main.Settings;
import main.Translations;

public class Assets implements Settings, Translations{
	
	private static final int width = 32, height = 32;
	
	//fonts
	public static Font font28;
	
	//images
	public static BufferedImage grass, wall;
	public static BufferedImage[] start_btn, quit_btn, minus_btn, plus_btn, dropdown_btn;
	public static BufferedImage[] back_btn, back_written_btn, forward_btn, backward_btn;
	public static BufferedImage[] heroes;
	public static BufferedImage[] settings, move, attack, item, end_turn;
	public static BufferedImage[] en, de;
	public static BufferedImage tile1, tile2, tile3, tile4, tile5, tile6;
	public static BufferedImage intro_logo;
	public static BufferedImage main_background, ingame_inventar_background, hands_inventar_background;
	

	public static void init(){
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		//init sheets
		SpriteSheet tiles_sheet = new SpriteSheet(ImageLoader.loadImage("/tiles/sheet_1.png"));
		SpriteSheet buttons_sheet = new SpriteSheet(ImageLoader.loadImage("/menues/buttons.png"));
		SpriteSheet buttons_hover_sheet = new SpriteSheet(ImageLoader.loadImage("/menues/buttons_hover.png"));
		SpriteSheet countries_sheet = new SpriteSheet(ImageLoader.loadImage("/logo/countries.png"));

		SpriteSheet tile_1 = new SpriteSheet(ImageLoader.loadImage("/tiles/tile1.png"));
		SpriteSheet tile_2 = new SpriteSheet(ImageLoader.loadImage("/tiles/tile2.png"));
		SpriteSheet tile_3 = new SpriteSheet(ImageLoader.loadImage("/tiles/tile3.png"));
		SpriteSheet tile_4 = new SpriteSheet(ImageLoader.loadImage("/tiles/tile4.png"));
		SpriteSheet tile_5 = new SpriteSheet(ImageLoader.loadImage("/tiles/tile5.png"));
		SpriteSheet tile_6 = new SpriteSheet(ImageLoader.loadImage("/tiles/tile6.png"));

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
		settings = new BufferedImage[2];
		move = new BufferedImage[2];
		attack = new BufferedImage[2];
		item = new BufferedImage[2];
		end_turn = new BufferedImage[2];
		en = new BufferedImage[2];
		de = new BufferedImage[2];

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
		settings[0] = buttons_sheet.crop(96, 96, width, height);
		settings[1] = buttons_hover_sheet.crop(96, 96, width, height);
		move[0] = buttons_sheet.crop(0, 160, width * 2, height);
		move[1] = buttons_hover_sheet.crop(0, 160, width * 2, height);
		attack[0] = buttons_sheet.crop(0, 192, width * 2, height);
		attack[1] = buttons_hover_sheet.crop(0, 192, width * 2, height);
		item[0] = buttons_sheet.crop(0, 224, width * 2, height);
		item[1] = buttons_hover_sheet.crop(0, 224, width * 2, height);
		end_turn[0] = buttons_sheet.crop(0, 256, width * 2, height);
		end_turn[1] = buttons_hover_sheet.crop(0, 256, width * 2, height);
		
		//init country logos
		en[0] = countries_sheet.crop(0, 0, width, height);
		en[1] = countries_sheet.crop(0, 32, width, height);
		de[0] = countries_sheet.crop(0, 64, width, height);
		de[1] = countries_sheet.crop(0, 96, width, height);
		
		//init tiles
		grass = tiles_sheet.crop(width * 2, 0, width, height);
		wall = tiles_sheet.crop(width * 3, 0, width, height);
		
		tile1 = tile_1.crop(0, 0, TILESIZE, TILESIZE);
		tile2 = tile_2.crop(0, 0, TILESIZE, TILESIZE);
		tile3 = tile_3.crop(0, 0, TILESIZE, TILESIZE);
		tile4 = tile_4.crop(0, 0, TILESIZE, TILESIZE);
		tile5 = tile_5.crop(0, 0, TILESIZE, TILESIZE);
		tile6 = tile_6.crop(0, 0, TILESIZE, TILESIZE);
		
		//init Backgrounds
		main_background = backgrounds.crop(0, 0, WIDTH, HEIGHT);
		ingame_inventar_background = backgrounds.crop(0, HEIGHT, WIDTH, 160);
		hands_inventar_background = backgrounds.crop(0, 700, 195, 100);
		intro_logo = intro.crop(0, 0, WIDTH, HEIGHT);
		
		//init heroes images
		heroes[0] = ImageLoader.loadImage("/creatures/heroes/hero1.png");
		heroes[1] = ImageLoader.loadImage("/creatures/heroes/hero2.png");
		heroes[2] = ImageLoader.loadImage("/creatures/heroes/hero3.png");
		heroes[3] = ImageLoader.loadImage("/creatures/heroes/hero1.png");
	}
}
