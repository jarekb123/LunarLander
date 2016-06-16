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
import lunarPlayer.Player;

public class LoadGameScene extends Scene {
	String login=new String();
	Player player=new Player();

	public LoadGameScene(LPanel parent, Dimension size, Dimension preferredSize) {
		super(parent, size, preferredSize);
		 GraphicButton sign = new GraphicButton("img/menu/podaj_login.png", 0.5, 0.3);
	     GraphicButton accept = new GraphicButton("img/menu/graj.png","img/menu/grajon.png",0.5,0.8);   
	     GraphicButton retur = new GraphicButton("img/menu/wroc.png","img/menu/wroc.png",0.2,0.8);   
		 accept.setAction("accept");
		 retur.setAction("back");
		 graphicObjects.add(sign);
	     graphicObjects.add(accept);
	     graphicObjects.add(retur);		
		
	}
	
	@Override
	public void updateScene(Graphics2D g2d) 
    {
        Color transparentBlack = new Color(0f, 0f, 0f, 0.5f);
        g2d.setBackground(transparentBlack);
        g2d.setPaint(transparentBlack);
        g2d.fillRect(0, 0, size.width, size.height);
        g2d.setColor(new Color(12, 16, 116));
        Font f = new Font("Comic Sans MS", Font.BOLD, 30);
        g2d.setFont(f);
        g2d.drawString(login,(int)(size.width*0.3), (int)(size.height*0.5));
        for(int i=0; i<graphicObjects.size(); i++)
        {
            graphicObjects.get(i).paintImage(g2d, size, preferredSize);
        }
    }
	@Override
    public void mouseMoved(MouseEvent e)
    {
        Point p = e.getPoint();
        for(int i = 0; i<graphicObjects.size(); i++)
        {
            if(graphicObjects.get(i) instanceof GraphicButton)
            {
               GraphicButton gBtn = (GraphicButton)graphicObjects.get(i);
               Rectangle rect = gBtn.getButtonRect(size, preferredSize);
               if(rect.contains(p))
               {
                   gBtn.mouseEntered();
               }
               else
               {
                    gBtn.mouseExited();
               }
            }
        }
    }
	/**
	 * wczytywanie odpowiedniego playera z pliku
	 */
	public void accept()
	{
		
		if(player.loadPlayer(login+".properties")!=true)
		{
			System.out.println("FAIL");
			player.loadPlayer("player.properties");
		}
		parentPanel.setPlayer(player);
		parentPanel.setState(GameState.LevelChoice);
        parentPanel.initScene(GameState.LevelChoice);
	}
	/**
	 * metoda zmieniajaca scene spowrotem na menu
	 */
	public void back()
	{
		parentPanel.setState(GameState.Menu);
        parentPanel.initScene(GameState.Menu);
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
                       
                       case "accept":
                           accept();
                           break;
                       case "back":
                           back();
                           break;
                       default:
                           break;
                           
                   }
               }
               
            }
        }
    }
     
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
		{
			if(login.length()>0)
			login=login.substring(0, login.length()-1);
		}
		else 
			login=login + e.getKeyChar();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void updateLogic(long dt) {
	
	}

}
