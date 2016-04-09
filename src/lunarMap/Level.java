package lunarMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Klasa zawieraj�ca wszystkie elementy danego poziomu
 * 
 *
 */
public class Level {
	
	private int number;
	private double gravity;
	private double maxVy, maxVx;
	private int bonusNumber;
	
	private GameMap gameMap;
	/**
	 * 
	 * @param number jaki numer porz�dkowy ma dany level
	 * @param gravity jakie jest przyspieszenie na tym poziomie
	 * @param maxVx jaka jest maxymalna pr�dko�� w osi x aby wyl�dowa�
	 * @param maxVy jaka jest maxymalna pr�dko�� w osi y aby wyl�dowa�
	 * @param bonusNumber ile jest bonus�w na planszy
	 */
	public Level(int number, double gravity, double maxVx, double maxVy, int bonusNumber)
	{
		this.number = number;
		this.gravity = gravity;
		this.maxVx = maxVx;
		this.maxVy = maxVy;
		this.bonusNumber = bonusNumber;
		this.gameMap = new GameMap();
	}
	
	public Level() {
		this.gameMap = new GameMap();
	}
	/**
	 * metoda wczytuj�ca dane z pliku konfiguracyjnego
	 * @param filename nazwa pliku
	 */
	public void loadLevel(String filename)
	{
		System.setProperty("file.encoding","UTF-8");
		 InputStream is;
		 
        try 
        {
        	Properties properties=new Properties();

        	is = new FileInputStream(new File(filename));
			properties.load(is);
			number=Integer.parseInt(properties.getProperty("levelNo"));
			gravity=Double.parseDouble(properties.getProperty("gravity"));
			bonusNumber=Integer.parseInt(properties.getProperty("bonusNo"));
			maxVx=Double.parseDouble(properties.getProperty("maxVx"));
			maxVy=Double.parseDouble(properties.getProperty("maxVy"));
			gameMap.loadMap(filename);
			
			
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
	 * metoda wczytuj�ca dane dla obiektu gameMap klasy @class GameMap
	 * @param filename
	 */
	public void loadMap(String filename)
	{
		gameMap.loadMap(filename);
	}
	/**
	 * metoda pozwalaj�ca na dost�p do obiektu gameMap klasy @class GameMap
	 * @return gameMap
	 */
	public GameMap getMap()
	{
		return gameMap;
	}
	/**
	 * metoda zwracaj�ca obiekt number, kt�ry reperezentuje numer porz�dkowy danego poziomu
	 * @return number
	 */
	public int getNumber()
	{
		return number;
	}
	/**
	 * metoda zwracaj�ca obiekt gravity, kt�ry reperezentuje przy�pieszenie
	 * @return gravity
	 */
	public double getGravity()
	{
		return gravity;
	}
	/**
	 * metoda zwracaj�ca maxymaln� pr�dko�� w osi Y
	 * @return maxVy
	 */
	public double getMaxVy()
	{
		return maxVy;
	}
	/**
	 * metoda zwracaj�ca maxymaln� prekos� w osi X
	 * @return maxVx
	 */
	public double getMaxVx()
	{
		return maxVx;
	}
	/**
	 * metoda zwracaj�ca liczb� bonus�w na danym poziomie
	 * @return bonusNumber
	 */
	public int getBonusNumber()
	{
		return bonusNumber;
	}
}