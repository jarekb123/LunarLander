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

public class MenuScene extends Scene {

	public MenuScene(LPanel parent, Dimension size, Dimension preferredSize)
	{
		super(parent, size, preferredSize);
		
		GraphicButton loadGameBtn = new GraphicButton("img/menu/wczytaj_gre.png", "img/menu/wczytaj_gre_onclick.png", 0.5, 0.25);	        
	    loadGameBtn.setAction("load game");
	    GraphicButton saveGameBtn = new GraphicButton("img/menu/zapisz_gre.png", "img/menu/zapisz_gre_onclick.png", 0.5, 0.4);
	    saveGameBtn.setAction("save game");
	    GraphicButton newGameBtn = new GraphicButton("img/menu/nowa_gra.png", "img/menu/nowa_gra_onclick.png", 0.5, 0.5);
	    newGameBtn.setAction("new game");
	    GraphicButton title = new GraphicButton("img/menu/title.png", 0.5, 0.1);
	    GraphicButton instructionBtn = new GraphicButton("img/menu/instrukcja.png", "img/menu/instrukcja_onclick.png", 0.5, 0.6);
	    instructionBtn.setAction("instruction");   
	    GraphicButton optionBtn = new GraphicButton("img/menu/opcje.png", "img/menu/opcje_onclick.png", 0.5, 0.7);
	    optionBtn.setAction("options");  
	    GraphicButton bestScoreBtn = new GraphicButton("img/menu/najlepszy_wynik.png", "img/menu/najlepsze_wyniki_onclick.png", 0.5, 0.8);
	    bestScoreBtn.setAction("best score");
	    GraphicButton exitBtn = new GraphicButton("img/menu/koniec.png", "img/menu/koniec_menu_onclick.png", 0.5, 0.9);
	    exitBtn.setAction("exit");  
	        
	        graphicObjects.add(title);
	        graphicObjects.add(loadGameBtn);
	        graphicObjects.add(saveGameBtn); 
	        graphicObjects.add(newGameBtn);
	        graphicObjects.add(instructionBtn);
	        graphicObjects.add(optionBtn);
	        graphicObjects.add(bestScoreBtn);
	        graphicObjects.add(exitBtn);
	
		
	}
	@Override
    public void updateScene(Graphics2D g2d) 
    {
        Color transparentBlack = new Color(0f, 0f, 0f, 0.5f);
        g2d.setBackground(transparentBlack);
        g2d.setPaint(transparentBlack);
        g2d.fillRect(0, 0, size.width, size.height);
        for(int i=0; i<graphicObjects.size(); i++)
        {
            graphicObjects.get(i).paintImage(g2d, size, preferredSize);
        }
    }

    public void resume()
    {
        parentPanel.setState(GameState.Play);
        parentPanel.initScene(GameState.Play);
    }
    public void exit()
    {
        System.exit(2);
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
    public void loadGame()
    {
    	parentPanel.setState(GameState.LoadGame);
        parentPanel.initScene(GameState.LoadGame);
    }
    public void saveGame()
    {
    	//TODO: zrobić zapis
    }
    public void newGame()
    {
    	parentPanel.setState(GameState.NewGame);
        parentPanel.initScene(GameState.NewGame);
    }
    public void instruction()
    {
    	parentPanel.setState(GameState.Instruction);
        parentPanel.initScene(GameState.Instruction);
    }
    public void options()
    {
    	parentPanel.setState(GameState.Options);
        parentPanel.initScene(GameState.Options);
    }
    public void bestscore()
	{
		
		parentPanel.setState(GameState.BestScore);
        parentPanel.initScene(GameState.BestScore);
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
                         
                         case "load game":
                             loadGame();
                             break;
                         case "save game":
                             saveGame();
                             break;
                         case "new game":
                         		newGame();
                         		break;
                         case "instruction":
                         		instruction();
                         		break;
                         case "options":
                         		options();
                         		break;
                         case "best score":
                         		bestscore();
                         		break;
                         case "exit":
                         		exit();
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
	public void updateLogic(long dt) {
		
	}
}
