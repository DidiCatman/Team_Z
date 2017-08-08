package entities;

import java.awt.image.BufferedImage;

import main.Handler;
import main.Translations;

public class Player extends Entity implements Translations{
	
	@SuppressWarnings("unused")
	private String hero;

	public Player(Handler handler, int tilex, int tiley, int maxHealth, int hero, BufferedImage image) {
		super(handler, tilex, tiley, maxHealth, image);
		this.hero = HERONAMES[hero];
	}

	@Override
	public void tick() {
		
	}

}
