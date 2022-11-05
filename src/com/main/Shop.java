package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.main.Game.STATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter{

	Handler handler;
	HUD hud;
	
	private int B1 = 500;
	private int B2 = 800;
	private int B3 = 1000;
	
	public Shop(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",0,48));
		g.drawString("SHOP", Game.WIDTH / 2 - 60, 60);
		
		//box 1 - tambah max nyawa
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Upgrade Health", 110, 120);
		g.drawString("Cost: " + B1, 110, 140);
		g.drawRect(105,100,100,80);
		
		//box 2 - 
		g.drawString("Upgrade Speed", 255, 120);
		g.drawString("Cost: " + B2, 255, 140);
		g.drawRect(250,100,100,80);
				
		//box 3 - isi nyawa bagi full
		g.drawString("Refill health", 405, 120);
		g.drawString("Cost: " + B3, 405, 140);
		g.drawRect(400,100,100,80);
		
		g.drawString("SCORE: " + hud.getScore(), Game.WIDTH/2 - 50, 300);
		g.drawString("Press SPACE to continue", Game.WIDTH/2 - 50, 350);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Shop) {
			//box 1
			if(mx >= 100 && mx <= 200) {
				if(my >= 100 && my <=180) {
					//you've select box 1
					if(hud.getScore() >= B1) {
						hud.setScore(hud.getScore() - B1);
						B1 += 800;
						hud.bounds += 40;
						hud.HEALTH = (100 + (hud.bounds/2));
					}
				}
			}
			
			//box 2
			if(mx >= 250 && mx <= 350) {
				if(my >= 100 && my <=180) {
					if(hud.getScore() >= B2) {
						hud.setScore(hud.getScore() - B2);
						B2 += 200;
						handler.speed++;
					}
				}
			}
			
			//box 3
			if(mx >= 400 && mx <= 500) {
				if(my >= 100 && my <=180) {
					//you've select box 3
					if(hud.getScore() >= B3) {
						hud.setScore(hud.getScore() - B3);
						B3 += 500;
						hud.HEALTH = (100 + (hud.bounds/2));
					}
				}
			}
		}		
	}
}
