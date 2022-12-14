package com.main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;



public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -4180142123780496767L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static boolean paused =false;
	
	public int diff = 0;
	// 0 - normal
	// 1 - hard
	
	private Thread thread;
	private boolean running = false;
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	private Shop shop;
	
	public enum STATE{
		Menu,
		Select,
		Shop,
		Help,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;

	
	public Game() {
		
		handler = new Handler();
		
		hud = new HUD();
		shop = new Shop(handler, hud);
		r= new Random();
		spawn = new Spawn(handler, hud, this);
		//AudioPlayer.load();
		//AudioPlayer.getMusic("music").loop();
		menu = new Menu(this, handler, hud, spawn);
		
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		
		
		new Window(WIDTH, HEIGHT, "GAMING!!", this);

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.requestFocus();
		 long lastTime = System.nanoTime();
		 double amountOfTicks = 60.0;
		 double ns = 1000000000 / amountOfTicks;
		 double delta = 0;
		 long timer = System.currentTimeMillis();
		 int frames = 0;
		 while(running) {
			 long now = System.nanoTime();
			 delta += (now - lastTime) / ns;
			 lastTime = now;
			 while(delta >= 1) {
				 tick();
				 delta--;
			 }
			 if(running) {
				 render();
			 }
			 frames++;
			 
			 if(System.currentTimeMillis() - timer > 1000) {
				 timer += 1000;
				 System.out.println("FPS: " + frames);
				 frames = 0;
			 }
		 }
		 stop();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(paused) {
			g.drawString("PAUSED",300,150);
		}
		
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);

		} else if(gameState == STATE.Shop){
			shop.render(g);
		} else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
			menu.render(g);
			handler.render(g);
		} 
		
		g.dispose();
		bs.show();
	}

	private void tick() {
		if(gameState == STATE.Game) {
			if(!paused) {
				hud.tick();
				spawn.tick();
				handler.tick();
				if(HUD.HEALTH == 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
					gameState = STATE.End;
					for(int i = 0; i < 12; i++) {
						handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}
				}
			}
			
		} else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
			menu.tick();
			handler.tick();
		}
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) {
			return var = max;
		} else if(var <= min) {
			return var = min;
		} else {
			return var;
		}
	}

	public static void main(String[] args) {

		new Game();
	}

}
