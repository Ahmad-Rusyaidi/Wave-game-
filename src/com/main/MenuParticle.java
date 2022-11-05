package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class MenuParticle extends GameObject{

	private Handler handler;
	private Random r = new Random();
	private Color col;
	private int dir = 0;
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		dir = r.nextInt(2);
		
		if(dir == 0) {
			velX = 4;
			velY =6;
		} else if(dir == 1) {
			velX = 6;
			velY =4;
		}

		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, col, 16, 16, 0.02f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y,16,16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
}
