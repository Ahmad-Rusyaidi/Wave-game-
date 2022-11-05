package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class BossOneBullet extends GameObject{

	private Handler handler;
	private Random r = new Random();
	
	public BossOneBullet(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y >= Game.HEIGHT) {
			handler.removeObject(this);
		}
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int)x, (int)y,16,16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
}

