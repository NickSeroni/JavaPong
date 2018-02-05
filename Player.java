package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/*
 * Sets rules for player objects
 * Sets collision
 */
public class Player extends GameObject
{
	private Random r = new Random();
	private Handler handler;

	public Player(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 15, 75);
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		y = Game.clamp(y, 0, Game.HEIGHT - 100);//clamp for the character in the room
		
		collision();
	}
	
	private void collision()
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Ball)//tempObject is the ball
			{
				if(getBounds().intersects(tempObject.getBounds()))//collision code
				{
					tempObject.velX *= -1.05;
					tempObject.velY += velY/5;
					if(tempObject.velY == 0)
					{
						tempObject.velY = r.nextInt() % 2 == 0 ? 5 : -5;
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.game.main.GameObject#render(java.awt.Graphics)
	 * Renders the players
	 */
	public void render(Graphics g) 
	{
		if(id == ID.Player)
		{
			g.setColor(Color.red);
		}
		else if(id == ID.Player2)
		{
			g.setColor(Color.cyan);
		}
		g.fillRect(x, y, 15, 75);
	}
}
