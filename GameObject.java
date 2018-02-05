package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * Sets rules for objects in game
 * Gives position and velocities of objects
 */
public abstract class GameObject 
{
	protected int x, y;//can only be used by what inherits this class
	protected ID id;//enumerations
	protected double velX, velY;//velocities
	
	public GameObject(int x, int y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setId(ID id)
	{
		this.id = id;
	} 
	public ID getId()
	{
		return id;
	}
	public void setVelX(int velX)
	{
		this.velX = velX;
	}
	public void setVelY(int velY)
	{
		this.velY = velY;
	}
	public double getVelX()
	{
		return velX;
	}
	public double getVelY()
	{
		return velY;
	}
}
