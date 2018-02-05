package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

/*
 * Individually renders and processes objects on screen
 * Creates a list of objects and stores them for use
 */
public class Handler 
{
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick()
	{
		for(int i = 0; i< object.size(); i++)//loops through all game objects
		{
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i< object.size(); i++)//loops through all game objects
		{
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
}

