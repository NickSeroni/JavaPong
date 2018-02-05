

package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.main.Game.STATE;

/*
 * Sets key inputs
 */
public class KeyInput extends KeyAdapter
{
	private Handler handler;
	private Game game;
	private boolean[] keyDown = new boolean[2];
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
		
		keyDown[0] = false;//up
		keyDown[1] = false;//down
	}
	
	public void keyPressed(KeyEvent e)//everytime assigned keys are pressed a velocity is changed
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE)
		{
			game = new Game();
			game.gameState = STATE.Menu;
		}
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)//key events for Player 1
			{
				if(key == KeyEvent.VK_W)
				{
					tempObject.setVelY(-6);//up
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_S)
				{
					tempObject.setVelY(6);//down
					keyDown[1] = true;
				}
			}
			
			if(tempObject.getId() == ID.Player2)//key events for Player 2
			{
				if(key == KeyEvent.VK_UP)
				{
					tempObject.setVelY(-6);//up
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_DOWN)
				{
					tempObject.setVelY(6);//down
					keyDown[1] = true;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e)//everytime the key is released makes velocity 0
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE)
		{
			
		}
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)//key events for Player 1
			{
				if(key == KeyEvent.VK_W) keyDown[0] = false; //up
				if(key == KeyEvent.VK_S) keyDown[1] = false;//down
					
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);	
			}
			
			if(tempObject.getId() == ID.Player2)//key events for Player 2
			{
				if(key == KeyEvent.VK_UP) keyDown[0] = false;//up
				if(key == KeyEvent.VK_DOWN) keyDown[1] = false;//down
					
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
			}
		}
	}
}
