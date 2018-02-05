package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/*
 * Creates the ball
 * Adds score to hud
 * Deletes and adds a new ball upon scoring
 */
public class Ball extends GameObject
{
	private Handler handler;
	private HUD hud;
	private Random random = new Random();

	
	public Ball(int x, int y, ID id, Handler handler, HUD hud)
	{
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		velX = (random.nextInt() % 2 == 0) ? 5 : -5;//the ball randomly moves left or right at the start
	}

	@Override
	public void tick() 
	{
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 50)
		{
			velY *= -1;
		}
		if(x <= -1) 
		{
			handler.removeObject(this);//removes the ball if it leaves the room
			hud.increment2();//increases the score for the correct side by one
			if(hud.getScore2() < 5)//as long as the scores is below the threshold the ball resets
			{
				handler.addObject(new Ball(450, 325, ID.Ball, handler, hud));//adds a new ball in the middle
			}
		}
		
		if(x >= Game.WIDTH - 20)
		{
			handler.removeObject(this);
			hud.increment1();
			if(hud.getScore1() < 5)
			{
				handler.addObject(new Ball(450, 325, ID.Ball, handler, hud));
			}
		}
	}

	@Override
	public void render(Graphics g) //renders the ball
	{
		g.setColor(Color.white);
		g.fillRect(x, y, 16, 16);
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x, y, 16, 16);
	}
}
