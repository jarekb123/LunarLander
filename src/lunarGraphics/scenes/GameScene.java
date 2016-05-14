/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lunarGraphics.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;

import lunarMap.Level;
import lunarPlayer.Player;

/**
 *
 * @author jarek
 */
public class GameScene extends Scene
{
    /** Obiekt @class Level, który przechowuje stan poziomu (w tym mapę) */
    Level level;
   /** Obiekt @class Player, która przechowuje wszystkie informacje związane z danym graczem */
    Player player;
    public GameScene(Dimension size, Dimension preferredSize)
    {
        super(size, preferredSize);
        level = new Level();
        level.loadLevel("map.properties");
        
        player = new Player();
        player.loadPlayer("player.properties");
        
        graphicObjects.add(player);
    }

    @Override
    public void updateScene(Graphics2D g2d)
    {
        if(isResized)
            level.getMap().paintMap(g2d, size);
        for(int i=0; i<graphicObjects.size(); i++)
        {
            graphicObjects.get(i).paintImage(g2d, size, preferredSize);
        }
    }
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			player.goUp();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player.goDown();
		}
		
		if(e.getKeyCode()== KeyEvent.VK_LEFT)
		{
			player.goLeft();
		}
		
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
		{
			player.goRight();
		}
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN 
				|| e.getKeyCode()==KeyEvent.VK_RIGHT|| e.getKeyCode()==KeyEvent.VK_LEFT)
			player.stop();	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override 
	public void updateLogic(long dt)
	{	
		
		player.updatePlayerPosition(dt, level.getGravity());
		
	}
	//metoda sprawdzajaca czy rakieta nie przecina siÄ™ z podĹ‚oĹĽem(Polygon) i z kraĹ„cami 
		//ekranu,czyli ĹĽe statek nie wyleciaĹ‚ poza ekran
		public boolean ifCrashed(Dimension gameDim)
		{
			//TODO:SPRAWDZ CZY DOBRE WYMIARY
			Polygon p=level.getMap().returnMapPolygon(gameDim);
			if(p.intersects(player.getX(), player.getY(), 0.1*gameDim.getHeight(), 0.1*gameDim.getWidth())
					||player.getX()==1||player.getY()==1||player.getX()==0||player.getY()==0)
				return true;
			else 
				return false;
		}

}
