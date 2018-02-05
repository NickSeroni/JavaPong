package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.game.main.Game.STATE;

/*
 * Game start and quit menu
 * Mouse inputs
 */
public class Menu extends MouseAdapter
{
	private Game game;
	private Handler handler;
	
	public Menu(Game game, Handler handler)
	{
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu)//mouse inputs on the menu only work when on menu screen
		{
			if(mouseOver(mx, my, 275, 200, 325, 100))//play button that creates the objects when pressed
			{
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2- 400, Game.HEIGHT/2- 75, ID.Player, handler));
				handler.addObject(new Player(Game.WIDTH/2+ 375, Game.HEIGHT/2- 75, ID.Player2, handler));
				handler.addObject(new Ball(Game.WIDTH/2- 25, Game.HEIGHT/2- 25, ID.Ball, handler, game.getHud()));
			}
			
			//quit button
			if(mouseOver(mx, my, 275, 350, 325, 100))
			{
				System.exit(1);
			}
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int height)//determines if the mouse is within
																				// a rectangle's boundaries
	{
		if(mx > x && mx < x + width)
		{
			if(my > y && my < y + height)
			{
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		if(game.gameState == STATE.Menu)//shows menu screen when the gameState is menu
		{
			g.setFont(new Font("Impact", 100, 100));
			g.setColor(Color.white);
			g.drawString("Menu", 325, 100);
			
			g.setFont(new Font("TimesRoman", 25, 30));
			g.drawString("First to 5 points wins!", 315, 150);
		}

			
		Font fnt = new Font("TimesRoman", 10, 50);
			
		g.setFont(fnt);
		g.setColor(Color.gray);
		g.drawRect(275, 200, 325, 100);//start box
		
		g.setColor(Color.white);
		g.drawString("Start", 385, 270);
			
		g.setColor(Color.gray);
		g.drawRect(275, 350, 325, 100);//quit box
		
		g.setColor(Color.white);
		g.drawString("Quit", 390, 415);
	}
}
