package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class BossOne extends GameObject{

	private Handler handler;
	private Random r = new Random();
	
	private int timer = 80;
	private int timer2 = 50;
	
	public BossOne(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		velX = 0;
		velY =2;
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) {
			velY = 0;
		} else {
			timer--;
		}
		
		if(timer <= 0) {
			timer2--;
		}
		
		if(timer2 <= 0) {
			if(velX == 0) {
				velX = 2;
			}
			int spawn = r.nextInt(10);
			if(spawn == 0) {
				handler.addObject(new BossOneBullet((int) x+48, (int) y+48, ID.BossOneBullet, handler));
			}
		}
		
		
		if(x <= 0 || x >= Game.WIDTH - 76
				) {
			velX *= -1;
		}
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int)x, (int)y,64,64);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
	}
}

