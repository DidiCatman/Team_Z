package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import gfx.Assets;
import input.KeyManager;
import input.MouseManager;
import states.GameOptionsState;
import states.GameState;
import states.IntroState;
import states.MenuState;
import states.State;

public class Game implements Runnable, Settings{
	
	private boolean running = false;
	private Thread thread;
	public String title;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//Handler
	private Handler handler;

	//Display
	private Display display;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//States
	public State introState;
	public State menuState;
	public State gameOptionsState;
	public State gameState;
	
	//language
	private int language = EN;

	public Game(){
		title = "TTZombies";
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init(){
		display = new Display(title);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();

		handler = new Handler(this);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		introState = new IntroState(handler);
		gameOptionsState = new GameOptionsState(handler);
		State.setState(introState);
	}
	
	private void tick(){
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, WIDTH, HEIGHT);
		//Draw Here!
		
		if(State.getState() != null)
			State.getState().render(g);
		
		//End Drawing!
		bs.show();
		g.dispose();
	}

	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				display.getFrame().setTitle(title + " ~ fps: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}

	public Display getDisplay() {
		return display;
	}

	public GameOptionsState getGameOptionsState() {
		return (GameOptionsState) gameOptionsState;
	}
	
	public GameState getGameState() {
		return (GameState) gameState;
	}
}
