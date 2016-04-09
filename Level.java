package lunarMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Klasa zawieraj¹ca wszystkie elementy danego poziomu
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
	 * @param number jaki numer porz¹dkowy ma dany level
	 * @param gravity jakie jest przyspieszenie na tym poziomie
	 * @param maxVx jaka jest maxymalna prêdkoœæ w osi x aby wyl¹dowaæ
	 * @param maxVy jaka jest maxymalna prêdkoœæ w osi y aby wyl¹dowaæ
	 * @param bonusNumber ile jest bonusów na planszy
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
	 * metoda wczytuj¹ca dane z pliku konfiguracyjnego
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
	 * metoda wczytuj¹ca dane dla obiektu gameMap klasy @class GameMap
	 * @param filename
	 */
	public void loadMap(String filename)
	{
		gameMap.loadMap(filename);
	}
	/**
	 * metoda pozwalaj¹ca na dostêp do obiektu gameMap klasy @class GameMap
	 * @return gameMap
	 */
	public GameMap getMap()
	{
		return gameMap;
	}
	/**
	 * metoda zwracaj¹ca obiekt number, który reperezentuje numer porz¹dkowy danego poziomu
	 * @return number
	 */
	public int getNumber()
	{
		return number;
	}
	/**
	 * metoda zwracaj¹ca obiekt gravity, który reperezentuje przyœpieszenie
	 * @return gravity
	 */
	public double getGravity()
	{
		return gravity;
	}
	/**
	 * metoda zwracaj¹ca maxymaln¹ prêdkoœæ w osi Y
	 * @return maxVy
	 */
	public double getMaxVy()
	{
		return maxVy;
	}
	/**
	 * metoda zwracaj¹ca maxymaln¹ prekosæ w osi X
	 * @return maxVx
	 */
	public double getMaxVx()
	{
		return maxVx;
	}
	/**
	 * metoda zwracaj¹ca liczbê bonusów na danym poziomie
	 * @return bonusNumber
	 */
	public int getBonusNumber()
	{
		return bonusNumber;
	}
}