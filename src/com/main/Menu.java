package com.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import com.main.Game.STATE;
import java.awt.Color;
import java.awt.Font;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler = new Handler();
	private Random r = new Random();
	private HUD hud;
	Spawn spawn;
	
	public Menu(Game game, Handler handler, HUD hud, Spawn spawn) {
		this.game = game;
		this.hud = hud;
		this.spawn = spawn;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx,my,420, 205, 200, 64)) {
				game.gameState = STATE.Select;
				return;
			}
			
			//help button
			if(mouseOver(mx, my, 420, 305, 200, 64)) {
				Game.gameState = STATE.Help;
				handler.clearEnemies();
			}
			
			//quit button
			if(mouseOver(mx, my, 420, 405, 200, 64)) {
				System.exit(1);
			}
		}
		
		if(Game.gameState == STATE.Select) {
			//normal button
			if(mouseOver(mx,my,420, 205, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
				game.diff = 0;
			}
			
			//hard button
			if(mouseOver(mx, my, 420, 305, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				game.diff = 1;
			}
			
			//back button for Select
			if(Game.gameState == STATE.Select) {
				if(mouseOver(mx, my, 420, 405, 200, 64)) {
					Game.gameState = STATE.Menu;
					return;
				}
			}
		}
		
		//back button for help
		if(Game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 420, 405, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			}
		}
		
		//retry button
				if(Game.gameState == STATE.End) {
					if(mouseOver(mx, my, 420, 405, 200, 64)) {
						Game.gameState = STATE.Menu;
						hud.setLevel(1);
						hud.setScore(0);
						spawn.setScoreKeep(0);
					}
				}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int x, int y, int mx, int my, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		if(Game.gameState == STATE.Menu) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 40);
			
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Wave", 260, 70);
			
			g.setFont(font2);
			g.drawRect(220, 140, 200, 64);
			g.drawString("Play", 280, 185);
			
			g.drawRect(220, 240, 200, 64);
			g.drawString("Help", 280, 285);
			
			g.drawRect(220, 340, 200, 64);
			g.drawString("Quit", 280, 385);
		} else if(Game.gameState == STATE.Help) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 20);
			
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Help", 260, 125);
			
			
			g.drawRect(220, 335, 200, 64);
			g.drawString("Back", 260, 385);
			
			g.setFont(font2);
			g.setColor(Color.yellow);
			g.drawString("Use WASD keys to move and dodge the enemies", 85, 215);
		} else if(Game.gameState == STATE.End) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 20);
			
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 180, 125);
			
			g.setFont(font2);
			g.setColor(Color.yellow);
			g.drawString("You lost with a score of: " + hud.getScore(), 130, 215);
			
			g.drawRect(220, 335, 200, 55);
			g.drawString("Try Again", 275, 370);
		} else if(Game.gameState == STATE.Select) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 40);
			
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Select DIFFICULTY", 100, 70);
			
			g.setFont(font2);
			g.drawRect(220, 140, 200, 64);
			g.drawString("NORMAL", 230, 185);
			
			g.drawRect(220, 240, 200, 64);
			g.drawString("HARD", 260, 285);
			
			g.drawRect(220, 340, 200, 64);
			g.drawString("BACK", 260, 385);
		}
	}
}
