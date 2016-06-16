package lunarGraphics.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import lunarGraphics.GraphicButton;
import lunarGraphics.LPanel;
import lunarGraphics.LPanel.GameState;
import lunarMap.Level;
import lunarPlayer.Player;

public class SuccessScene extends Scene {

	long points=parentPanel.getPoints();
	public SuccessScene(LPanel parent, Dimension size, Dimension preferredSize) {
		super(parent, size, preferredSize);
		 GraphicButton sign = new GraphicButton("img/menu/sukces.png", 0.5, 0.3);
	     GraphicButton retur = new GraphicButton("img/menu/wroc.png",0.3,0.8);
	     GraphicButton next = new GraphicButton("img/menu/next.png",0.7,0.8);
	     retur.setAction("back");
	     next.setAction("next");
	     graphicObjects.add(sign);
	     graphicObjects.add(retur);
	     graphicObjects.add(next);
		
	}
	public void back()
	{
		parentPanel.setState(GameState.Menu);
        parentPanel.initScene(GameState.Menu);
			
	}
	public void next()
	{
		Integer lvl =parentPanel.getLevel().getNumber();
		//lvl++;
		lvl=lvl%2;
		lvl++;
		Level lev=new Level();
		lev.loadLevel("map"+lvl.toString()+".properties");
		Player pl=parentPanel.getPlayer();
		pl.setX(0.5);
		pl.setY(0.15);
		pl.setVx(0);
		pl.setVy(0);
		parentPanel.setLevel(lev);
		parentPanel.setState(GameState.Play);
		parentPanel.initScene(GameState.Play);
	}
	@Override
    public void mouseClicked(MouseEvent e)
    {
        Point p = e.getPoint();
        boolean clicked = false;
        for(int i = 0; i<graphicObjects.size() && !clicked; i++)
        {
            if(graphicObjects.get(i) instanceof GraphicButton)
            {
               GraphicButton gBtn = (GraphicButton)graphicObjects.get(i);
               Rectangle rect = gBtn.getButtonRect(size, preferredSize);
               if(rect.contains(p))
               {
                   clicked = true;
                   String btnAction = gBtn.getAction();
                  switch(btnAction)
                  {
                  case "back":
                	  back();
                	  break;
                  case "next":
                	  next();
                	  break;
                 default: 
                	 break;
                  }
               }
               
            }
        }
    }

	@Override
	public void keyPressed(KeyEvent arg0) {
	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	
	}

	@Override
	public void updateScene(Graphics2D g2d) {
		Color transparentBlack = new Color(0f, 0f, 0f, 0.5f);
		g2d.setBackground(transparentBlack);
		g2d.setPaint(transparentBlack);
	    g2d.fillRect(0, 0, size.width, size.height);
	    for(int i=0; i<graphicObjects.size(); i++)
        {
            graphicObjects.get(i).paintImage(g2d, size, preferredSize);
        }
	    g2d.setColor(new Color(255,255,0));
	   Integer x=(int)points;
	   Font f = new Font("Comic Sans MS", Font.BOLD, 30);
       g2d.setFont(f);
	   g2d.drawString(x.toString()+"pkt", (int)(size.width*0.5), (int)(size.height*0.5));
	    
	}


	@Override
	public void updateLogic(long dt) {
	
		
	}

}
