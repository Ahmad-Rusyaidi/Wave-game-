package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class SmartEnemy extends GameObject{

	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}
		
		velX = 6;
		velY =6;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x - player.getX()) * (x-player.getX()) + (y - player.getY()) * (y-player.getY()));
		
		velX = ((-1 / distance) * diffX);
		velY = ((-1 / distance) * diffY);
		
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y,16,16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
}

