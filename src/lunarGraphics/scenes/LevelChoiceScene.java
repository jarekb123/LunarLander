package lunarGraphics.scenes;

import java.awt.Color;
import java.awt.Dimension;
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

public class LevelChoiceScene extends Scene {

	Player player;
	Level level=new Level();
	public LevelChoiceScene(LPanel parent, Dimension size, Dimension preferredSize) {
		super(parent, size, preferredSize);
		player=parentPanel.getPlayer();
		GraphicButton title = new GraphicButton("img/menu/wroc.png","img/menu/wroc.png",0.5,0.8);   
	    GraphicButton[] levels=new GraphicButton[10];
	   
		for(int i=0;i<=1;i++)
		{
	    	int j=0;
	   		Integer n=4*i+j;
	   		levels[n]= new GraphicButton("img/menu/square.png",0.3+i*0.4,0.4*j+0.3); 
	   		levels[n].setAction(n.toString());
	   		graphicObjects.add(levels[n]);
	    		
		}
		 graphicObjects.add(title);
	     
		
	
	}
	public void startGame(String number)
	{
		//TODO:poprawic wybieranie lvl
		Integer num=Integer.parseInt(number);
		String filename=new String();
		System.out.println(number);
		if(num==0)
			filename="map1.properties";
		else 
			filename="map2.properties";
		level.loadLevel(filename);
		parentPanel.setLevel(level);
		GameScene gs=new GameScene(parentPanel,parentPanel.getSize(),preferredSize);
		gs.level=level;
		gs.player=player;
		parentPanel.setGameScene(gs);
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
                  
                   
                   startGame(btnAction);
               }
               
            }
        }
    }
	
	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
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
	}

	@Override
	public void updateLogic(long dt) {

	}

}
