/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lunarGraphics.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import lunarGraphics.Bonus;
import lunarGraphics.GraphicButton;
import lunarGraphics.LPanel;
import lunarGraphics.LPanel.GameState;
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
    Bonus bonus;
    Random rnd=new Random();
    boolean firstTime=true;



    /**
     *
     * @param parent
     * @param size
     * @param preferredSize
     */


    public GameScene(LPanel parent, Dimension size, Dimension preferredSize)
    {
        super(parent, size, preferredSize);
        level = new Level();
        bonus=new Bonus("img/bonus.png",0.6,0.6);
        level.loadLevel("map2.properties");
        
        player = new Player();
        player.loadPlayer("player.properties");
        
        graphicObjects.add(player);
        graphicObjects.add(bonus);
    }
   
    @Override
    public void updateScene(Graphics2D g2d)
    {            
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

		
	}

 
    /**
     * metoda zmieniajaca logike gry co jakis czas 
     * @param dt
     */
    @Override
    public void updateLogic(long dt)
	{	
		
		player.updatePlayerPosition(dt, level.getGravity());
		updateBonus(dt);

		if(ifLanded(size))
		{
			parentPanel.setState(GameState.Success);
			parentPanel.initScene(GameState.Success);
		}
		if(ifCrashed(size))
		{
			parentPanel.setState(GameState.Crashed);
	        parentPanel.initScene(GameState.Crashed);
		}
		
	}
    public void updateBonus(long dt)
    {
    	double freefall=level.getGravity()*dt/1000;
    	double newY=bonus.getY()-freefall/10000;
    	double vX=rnd.nextDouble()/10;
    	
    	if(rnd.nextBoolean())
    	{
    		vX=-vX;
    	}
    	
    	double newX=bonus.getX()+vX/1000;
    	bonus.setX(newX);
    	bonus.setY(newY);
    }
   public void ifBonusCatched()
    {
    	if()
    }
	

    	public boolean ifLanded(Dimension gameDim)
    	{
    		level.getMap().createLandings(gameDim);
    		Rectangle[] landings=level.getMap().landings;
    		if(landings==null)
    			System.out.println("shit");
    		double x=player.getX()*gameDim.getWidth();
    		double y=player.getY()*gameDim.getHeight();
    		
    		double width=(0.1*gameDim.getWidth());
    		double height=(0.1*gameDim.getHeight());
    		
    		for(int i=0;i<landings.length;i++)
    		{
    			if(landings[i].intersects(x, y, width, height)
    					&& level.getMaxVx()>player.getvX()&& level.getMaxVy()>player.getvY())
    			{
    				return true;
    			}
    		}
    		return false;
    	}

        /**Metoda sprawdzająca czy rakieta nie rozbija się o ścianę ani o podłoże
         *
         * @param gameDim
         * @return
         */
    
		public boolean ifCrashed(Dimension gameDim)
		{
			Polygon pol=level.getMap().returnMapPolygon(gameDim);
			int	x=(int)(player.getX()*gameDim.getWidth());
			int y=(int)(player.getY()*gameDim.getHeight());
			if(pol.intersects(x , y , 0.09*gameDim.getHeight(), 0.09*gameDim.getWidth()))
			{
				return true;	
			}
			if(player.getX()>1||player.getY()>1||player.getX()<0||player.getY()<0)
				return true;
			else 
				return false;
		}

}
