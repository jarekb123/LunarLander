package lunarPlayer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

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
	public String  getName()
	{
		return name;
	}
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

	public double getvX()
	{
		return vX;
	}
	public double getvY()
	{
		return vY;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public int getScore()
	{
		return score;
	}
	public double getFuelLevel()
	{
		return this.fuelLevel;
	}
	public void setFuelLevel(double level)
	{
		this.fuelLevel=level;
	}
	public void setX(double x)
	{
		this.x=x;
	}
	public void setY(double y)
	{
		this.y=y;
	}
		
}
