package lunarPlayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import lunarGraphics.GraphicObject;
/**
 * klasa zawierająca wszelkie dane o graczu

 */
public class Player extends GraphicObject{
	private String name;
	private int score;
	private double vX, vY;
	private double fuelLevel;
	private boolean isRunning;
	private int accelerationX=0;
	private int accelerationY=0;
	public Player()
	{
		super(0.5, 0.15);
		vX=0.005;
		vY=0.005;
		isRunning = false;
	}
	public Player(String name,int score, String imgPath)
	{
		super(imgPath);
		this.x = 0.5;
		this.y = 0.15;
		vX = 0.005;
		vY = 0.005;
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
	public void loadPlayer(String filename)
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
			fuelLevel=Double.parseDouble(properties.getProperty("fuelLevel"));
			
			
        } 
        catch (IOException e)
        {
       	 e.printStackTrace();	
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

	public void goUp() { accelerationY=-1;	}
	public void goDown() { accelerationY=1; }
	public void goLeft(){ accelerationX= 1;}
	public void goRight() { accelerationX=-1;}
	public void stop() 
	{
		accelerationX=0;
		accelerationY=0;
	}
	//TODO: sprawdz ułamki
	public double freeFall(double gravity,long dt)
	{
		double toReturn=gravity*dt/1000;	
		
		return toReturn;
	}
	public void updatevX(long dt)
	{
		vX=accelerationX*dt/1000;
	}
	public void updatevY(long dt)
	{
		vY=accelerationY*dt/1000;
	}
	public void updatePlayerPosition(long dt,double gravity )
	{
		updatevX(dt);
		updatevY(dt);
		double freefall=freeFall(gravity,dt);
		double newX=x+vX;
		double newY=y+vY+freefall;
		setX(newX);
		setY(newY);
	}
}
