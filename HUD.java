package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


/*
 * Keeps track of score and has methods to display it
 */
public class HUD
{	
	private int score1 = 0;
	private int score2 = 0;
	
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.green);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("SCORE", 390, 50);
		
		g.setColor(Color.white);
		g.setFont(new Font("Impact", Font.BOLD, 50));
		g.drawString("" + score1, 100, 75);
		g.drawString("" + score2, 750, 75);
	}
	public void rendGG(Graphics g)//a separate hud method for when the game ends
	{
		g.setColor(Color.white);
		g.setFont(new Font("Impact", Font.BOLD, 50));
		if(score1 > score2)
		{
			g.drawString("Player 1 Wins!", 300, 300);
		}
		else if(score1 < score2)
		{
			g.drawString("Player 2 Wins!", 300, 300);
		}
	}
	
	
	public void increment1()
	{
		score1 += 1;
	}
	public void increment2()
	{
		score2 += 1;
	}
	
	public int getScore1()
	{
		return score1;
	}
	public int getScore2()
	{
		return score2;
	}
}
