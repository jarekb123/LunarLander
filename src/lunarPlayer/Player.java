package lunarPlayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import lunarGraphics.GraphicObject;
/**
 * klasa zawierająca wszelkie dane o graczu

 */
public class Player extends GraphicObject{
	private String name;
	private Integer score;
	private double vX, vY;
	private double fuelLevel, maxFuelLevel;
	private boolean isRunning;
	private boolean[] levelsAvalible;
	private int accelerationY;
	private int accelerationX;
	private int lifes=1;

        /** 
         * Domyślny konstruktor @class Player
         */
	public Player()
	{
		super(0.5, 0.15);
		vX=0;
		vY=0;
		isRunning = false;
		levelsAvalible=new boolean[10];
		for(boolean level:levelsAvalible)
		{
			level=false;
		}
		levelsAvalible[1]=true;
	}
        /**
         * Konstruktor parametrowy
         * @param name Nazwa gracza
         * @param score Wynik gracza
         * @param imgPath Ścieżka do pliku graficznego gracza
         */
	public Player(String name,int score, String imgPath)
	{
		super(imgPath);
		this.x = 0.5;
		this.y = 0.15;
		vX = 0;
		vY = 0;
		this.name=name;
		this.score=score;
		isRunning = false;
	}
	/**
	 * metoda zwracająca nazwę gracza
	 * @return name
	 */
	public String  getName()
	{
		return name;
	}
	/**
	 * metoda wczytująca dane z pliku konfiguracyjnego

	 * @param filename nazwa pliku
	 */
	public boolean loadPlayer(String filename)
	{
		System.setProperty("file.encoding","UTF-8");
		 InputStream is;
		 
       try 
       {
        	Properties properties=new Properties();

        	is = new FileInputStream(new File(filename));
			properties.load(is);
			name=properties.getProperty("name");
			imgPath = properties.getProperty("imgPath");
			score=Integer.parseInt(properties.getProperty("score"));
			maxFuelLevel=fuelLevel=Double.parseDouble(properties.getProperty("fuelLevel"));
			
			return true;
       } 
      catch(IOException e)
       {
      	 e.printStackTrace();	
      	 return false;
        } 
	}
	public void savePlayer()
	{
		 try {
		        Properties props = new Properties();
		        props.setProperty("name", this.name);
		        props.setProperty("score", ""+this.score);
		        props.setProperty("imgPath", ""+this.imgPath);
		        props.setProperty("fuelLevel", ""+this.fuelLevel);
		        File f = new File(name+".properties");
		        OutputStream out = new FileOutputStream( f );
		        props.store(out, "This is an optional header comment string");
		    }
		 catch(FileNotFoundException e)
		 {
			 
		 }
		    catch (Exception e ) {
		       // e.printStackTrace();
		    }
		
	}
	/** 
	 * metoda zwracająca obrazek gracza
	 * @return obrazek z pliku @param imgPath
	 */
	public BufferedImage getImage()
	{
		try
		{
			return ImageIO.read(new FileInputStream(imgPath));
		}
		catch(Exception e)
		{
			System.out.println("Blad wczytywania tekstury: " + imgPath);
			System.out.println("Wystapil blad : "+e.getClass().getName()+e.getMessage());
			System.exit(0);
			return null;
		}
	}
/**
 * metoda zwracająca prędkość w osi X
 * @return vX
 */
	public double getvX()
	{
		return vX;
	}
	public void setVx(double vx)
	{
		this.vX=vx;
	}
	public void setVy(double vy)
	{
		this.vY=vy;
	}
	/**
	 * metoda zwracająca prędkość w osi Y
	 * @return vY
	 */
	public double getvY()
	{
		return vY;
	}
	/**
	 * metoda zwracająca wspolrzedna X
	 * @return x
	 */
	public double getX()
	{
		return x;
	}
	/**
	 * metoda zwracajaca wspolrzedna Y
	 * @return y
	 */
	public double getY()
	{
		return y;
	}
	/**
	 * metoda zwracajaca wynik gracza
	 * @return score
	 */
	public int getScore()
	{
		return score;
	}
	/**
	 * metoda zwracajaca poziom paliwa
	 * @return fuelLevel
	 */
	public double getFuelLevel()
	{
		return this.fuelLevel;
	}
	/**
	 * metoda ustalajaca poziom paliwa
	 * @param level- nowy poziom paliwa
	 */
	public void setFuelLevel(double level)
	{
		this.fuelLevel=level;
	}
	/**
	 *m metoda ustalajaca wspolrzedna X
	 * @param x-nowa wspolrzedna na osi X
	 */
	public void setX(double x)
	{
		this.x=x;
	}
	/**
	 * metoda ustalajaca wspolrzedna Y
	 * @param y-nowa wspolrzedna y
	 */
	public void setY(double y)
	{
		this.y=y;
	}
	public void setName(String s)
	{
		this.name=s;
	}
        /**
         * Metoda włączająca ruch wymuszony gracza w górę
         */
	public void goUp() { accelerationY=-1;	}
        
        /**
         * Metoda włączająca ruch wymuszony gracza w dół
         */
	public void goDown() { accelerationY=1; }
        
        /**
         * Metoda włączająca ruch wymuszony gracza w lewo
         */
	public void goLeft(){ accelerationX= -1;}
        
        /**
         * Metoda włączająca ruch wymuszony gracza w prawo
         */
	public void goRight() { accelerationX=1;}
        
        /**
         * Metoda wyłączająca ruch wymuszony gracza
         */
	public void stop() 
	{
		accelerationX=0;
		accelerationY=0;
	}
	/**
         * metoda realizujaca swobodny spadek z przyspieszeniem 
         * @param gravity Stała grawitacji
         * @param dt Różnica czasu
         * @return predkosc jaka zyskal ten obiekt 
         */
	public double freeFall(double gravity,long dt)
	{
		double toReturn=gravity*dt/10000;	
		
		return toReturn;
	}

    /**
     *metoda zwiekszajaca vX o przyspieszenie accelerationX przez czas dt

     * @param dt
     */


	public void updatevX(long dt)
	{
		if(fuelLevel!=0)
		{
		vX=vX+accelerationX*dt/11.5;
		if (accelerationX!=0)
			this.fuelLevel--;
	
		}
	}
	
    /**
     *metoda zwiekszajaca vX o przyspieszenie accelerationY przez czas dt

     * @param dt
     */
	public void updatevY(long dt)
	{
		if(fuelLevel!=0)
		{
			vY=vY+accelerationY*dt/10;
			if(accelerationY!=0)
				this.fuelLevel--;
		}
	}


    /**
     *metoda zmieniajaca pozycje playera przez dany odstep czasowy dt
     * @param dt
     * @param gravity
     */


	public void updatePlayerPosition(long dt,double gravity )
	{
		updatevX(dt);
		updatevY(dt);
		double freefall=freeFall(gravity,dt);
		vY=vY+freefall;
		double newX=x+vX/10000;
		double newY=y+vY/10000;
		
		setX(newX);
		setY(newY);
	}

    @Override
    public void paintImage(Graphics2D g2d, Dimension size, Dimension preferredSize) {
        double scaleX = (double)size.width/(double)preferredSize.getWidth();
	double scaleY = (double)size.height/(double)preferredSize.getHeight();
        g2d.drawImage(getImage(), (int)(getX()*size.width),(int)(getY()*size.height) ,(int)(getImage().getWidth()*scaleX),(int)(getImage().getHeight()*scaleY),null);
	g2d.setColor(Color.white);
	g2d.drawString("x: "+(int)(getX()*preferredSize.width), 0, (int)(size.height*0.05));
	g2d.drawString("y: "+(int)(getY()*preferredSize.height), 0, (int)(size.height*0.1));
	g2d.drawString("vX: "+(int)getvX(), 0, (int)(size.height*0.15));
	g2d.drawString("vY: "+(int)getvY(), 0, (int)(size.height*0.2));
	g2d.drawString("Fuel", (int)(size.width-100), (int)(size.height*0.05));
        g2d.fillRect((int)(size.width-100), (int)(size.height*0.05), (int)(fuelLevel*100/maxFuelLevel), (int)(scaleY*10));
	 }
    public void addLife()
    {
    	this.lifes++;
    }
    public int getLifes()
    {
    	return lifes;
    }
    public void addPoints(long p)
    {
    	score+=(int)p;
    }
}