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
import lunarGraphics.ExtraLifeBonus;
import lunarGraphics.ExtraPointsBonus;
import lunarGraphics.GraphicButton;
import lunarGraphics.LPanel;
import lunarGraphics.LPanel.GameState;
import lunarGraphics.TimeBonus;
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
    long time=System.currentTimeMillis();
    long currTime=6000000;
    long points=0;
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
        bonus=new ExtraLifeBonus("img/bonus.png",0.6,0.6);
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
        Integer x=player.getLifes();
        g2d.drawString("Time:"+this.currTime/100000, (int)(size.width-100), (int)(size.height*0.15));
        g2d.drawString(x.toString(), (int)(size.width-100), (int)(size.height*0.25));
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
	public void calculatePoints()
	{
		points+=currTime/1000;
		
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
		long extraTime=System.currentTimeMillis()-time;
		currTime=currTime-extraTime;
		ifBonusCatched();
		if(ifLanded(size))
		{
			calculatePoints();
			parentPanel.setPoints(points);
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
    	if(!bonus.iftouched())
    	{
    		double x=player.getX()*size.getWidth();
	    	double y=player.getY()*size.getHeight();
	    	double h=0.1*size.getHeight();
	    	double w=0.1*size.getWidth();
	    	if(bonus.getButtonRect(size, preferredSize).intersects(x,y,w,h))
	    	{
			  //TODO: jeszcze obczaić aby lepiej to działało
			   if(bonus instanceof TimeBonus)
			   {
				   currTime+=1000000;
			   }
			   if(bonus instanceof ExtraLifeBonus)
			   {
				   player.addLife();
			   }
			   if(bonus instanceof ExtraPointsBonus)
			   {
				   points+=100;
			   }
			   bonus.touched();
			   graphicObjects.remove(bonus);
		   }
	   }
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
    				points+=i*1000;
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
			if(currTime<0)
			{
				return true;
			}
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
