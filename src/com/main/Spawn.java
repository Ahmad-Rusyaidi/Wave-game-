package com.main;

import java.util.Random;

import com.main.Game.STATE;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Game game;
	
	private int scoreKeep = 0;

	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	public void tick() {
		scoreKeep++;
		if(Game.gameState == STATE.Game) {
			if(scoreKeep >= 300) {
				scoreKeep = 0;
				if(scoreKeep % 300 == 0) {
					hud.setLevel(hud.getLevel() + 1);
				}
				if(game.diff == 0) {
					if(hud.getLevel() == 2) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					} else if(hud.getLevel() == 3) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					} else if(hud.getLevel() == 4) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					} else if(hud.getLevel() == 5) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
					} else if(hud.getLevel() == 10) {
						handler.clearEnemies();
						handler.addObject(new BossOne((Game.WIDTH / 2) - 16, -100, ID.BossOne, handler));
					} else if(hud.getLevel() == 11) {
						handler.clearEnemies();
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					} else {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
				} else {
					if(hud.getLevel() == 2) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					} else if(hud.getLevel() == 3) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
					} else if(hud.getLevel() == 4) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					} else if(hud.getLevel() == 5) {
						handler.clearEnemies();
						handler.addObject(new BossOne((Game.WIDTH / 2) - 16, -100, ID.BossOne, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
					} else if(hud.getLevel() == 10) {
						handler.clearEnemies();
						handler.addObject(new BossOne((Game.WIDTH / 2) - 16, -100, ID.BossOne, handler));
					} else if(hud.getLevel() == 11) {
						handler.clearEnemies();
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					} else {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
				}
			}
		} else if(Game.gameState != STATE.Game || Game.gameState != STATE.Help) {
			for(int i = 0; i < 12; i++) {
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
			}
		} else {
			scoreKeep = 0;
			hud.setLevel(1);
			hud.setScore(0);
			handler.clearEnemies();
		}
	}
	
	public void setScoreKeep(int score) {
		this.scoreKeep = score;
	}
}
