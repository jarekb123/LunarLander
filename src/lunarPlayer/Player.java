package lunarPlayer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
/**
 * klasa zawieraj¹ca wszelkie dane o graczu
 */
public class Player {
	private String name;
	private int score;
	private String imgPath;
	private double x,y;
	private double vX, vY;
	private double fuelLevel;
	public Player()
	{
		this.x=0.5;
		this.y=0.05;
		vX=0D;
		vY=0D;
	}
	public Player(String name,int score,String imgPath )
	{
		this.name=name;
		this.score=score;
		this.imgPath=imgPath;
		this.x=0.5;
		this.y=0.15;
		vX=0D;
		vY=0D;
	}
	/**
	 * metoda zwracaj¹ca nazwê gracza
	 * @return name
	 */
	public String  getName()
	{
		return name;
	}
	/**
	 * metoda wczytuj¹ca dane z pliku konfiguracyjnego
	 * @param filename- nazwa pliku
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
        catch (FileNotFoundException e)
        {
       	 e.printStackTrace();	
        } 
        catch (IOException e) 
        {
       	 e.printStackTrace();
        }
	}
	/** 
	 * metoda zwracaj¹ca obrazek gracza
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
 * metoda zwracaj¹ca prêdkoœæ w osi X
 * @return vX
 */
	public double getvX()
	{
		return vX;
	}
	/**
	 * metoda zwracaj¹ca prêdkoœæ w osi Y
	 * @return vY
	 */
	public double getvY()
	{
		return vY;
	}
	/**
	 * metoda zwracaj¹ca wspolrzedna X
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
		
}
