package lunarGraphics.scenes;


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import lunarGraphics.LPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import lunarGraphics.GraphicButton;
import lunarGraphics.LPanel.GameState;


public class BestScoreScene extends Scene {
	String[] bestPlayer;
	
	public BestScoreScene(LPanel parent, Dimension size, Dimension preferredSize) {
		super(parent, size, preferredSize);
		 GraphicButton sign = new GraphicButton("img/menu/najlepszy_wynik.png", 0.5, 0.3);
	     GraphicButton retur = new GraphicButton("img/menu/wroc.png",0.5,0.8);
	     retur.setAction("back");
	     graphicObjects.add(sign);
	     graphicObjects.add(retur);
	     //TODO:WAŻNE::wczytywanie z serwera danych 3 najlepszych graczy i przypisac do PROTOCOLDATA
	     String protocolData= new String("Abacki 100pkt Cabacki 200pkt Babacki 300pkt	");
	     String[]data=protocolData.split(" ");	     
	    		 
	     bestPlayer=new String[3];
	     for(int i=0;i<3;i++)
	     {
	    	 bestPlayer[i]=new String(data[i*2]+"  "+data[i*2+1]); 
	     }
	}
	/**
	 * metoda pomocnicza do zmiany sceny spowrotem na menu
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
	    g2d.setColor(new Color(12, 16, 116));
	    Font f = new Font("Comic Sans MS", Font.BOLD, 30);
        g2d.setFont(f);
        for(int i=0;i<bestPlayer.length;i++)
        {
        	String str=bestPlayer[i];
        	g2d.drawString(str,(int)(size.width*0.3), (int)(size.height*(0.4+0.1*i)));
        }
        for(int i=0; i<graphicObjects.size(); i++)
        {
            graphicObjects.get(i).paintImage(g2d, size, preferredSize);
        }

	}

	@Override
	public void updateLogic(long dt) 
	{
	}
	
}

