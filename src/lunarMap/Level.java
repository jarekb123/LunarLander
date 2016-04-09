package lunarMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Level {
	
	private int number;
	private double gravity;
	private double maxVy, maxVx;
	private int bonusNumber;
	
	private GameMap gameMap;
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
	public void loadMap(String filename)
	{
		gameMap.loadMap(filename);
	}
	public GameMap getMap()
	{
		return gameMap;
	}
	public int getNumber()
	{
		return number;
	}
	public double getGravity()
	{
		return gravity;
	}
	public double getMaxVy()
	{
		return maxVy;
	}
	public double getMaxVx()
	{
		return maxVx;
	}
	public int getBonusNumber()
	{
		return bonusNumber;
	}
}