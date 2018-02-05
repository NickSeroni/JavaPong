package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
/*
 * Backbone of the game
 * Contains main method
 */
public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = -1478604005915452565L;
	
	public static final int WIDTH = 900, HEIGHT = WIDTH/ 12 * 9;
	
	private Thread thread;
	private boolean running = false;

	private Handler handler;
	private HUD hud;
	private Menu menu;
	
	
	public enum STATE
	{
		Menu,
		Game,
		GG
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game()
	{
		handler = new Handler();
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler));//adds key input to game
		this.addMouseListener(menu);//adds mouse input to game
		
		new Window(WIDTH, HEIGHT, "Counterfeit Pong", this);
		
		hud = new HUD();
		
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Measures time using the system clock
	 * Measures frames per second
	 * Calls tick every frame
	 */
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();//get current time to nanosecond
		double amountOfTicks = 60.0;//set number of ticks
		double ns = 1000000000 / amountOfTicks;//determines how many times we can divide by 60 into 1e9 nanoseconds or about 1 second
		double delta = 0;//change in time
		long timer = System.currentTimeMillis();//get current time
		int frames = 0;//set frame variable
		while(running)
		{
			long now = System.nanoTime();//current time in current loop
			delta += (now - lastTime) / ns;//adds the amount of change since the last loop
			lastTime = now;//sets lastTime for next loop
			while(delta >= 1)
			{
				//this loop is a pause to make sure the game doesn't run too fast
				//makes sure there is about 60 frames per 1 second interval determined to the nanosecond
				//once pause is done we render the next frame
				tick();
				delta--;//lowers delta to 0 to start next frame
			}
			if(running)
			{
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)//if 1 second has passed
			{
				timer += 1000;//add 1000 to our timer for next time
				System.out.println("FPS: " + frames);//prints how many frames have occurred in the last second
				frames = 0;//reset frame count for next second
			}
		}
		stop();
	}
	
	private void tick()
	{
		handler.tick();
		
		if(gameState == STATE.Game)
		{
			hud.tick();
			
			if(hud.getScore1() >= 5 || hud.getScore2() >= 5)//when the score is at 5 the gameState becomes GG
			{
				gameState = STATE.GG;
			}
		}
		else
		{
			menu.tick();
		}
	}
	
	private void render()//renders the game room
	{	
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);//fills the game window
		
		handler.render(g);//renders objects
		
		if(gameState == STATE.Game)
		{
			hud.render(g);//renders HUD
		}
		else if(gameState == STATE.Menu)
		{
			menu.render(g);
		}
		else if(gameState == STATE.GG)
		{
			hud.rendGG(g);
		}

		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max)//prevents player from leaving room
	{
		if(var >= max)
		{
			return var = max;
		}
		else if(var <= min)
		{
			return var = min;
		}
		else
		{
			return var;
		}
	}
	
	public HUD getHud()
	{
		return hud;
	}
	
	public static void main(String args[])
	{
		new Game();
	}
	
	
}

