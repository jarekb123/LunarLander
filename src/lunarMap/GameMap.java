package lunarMap;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

public class GameMap {
	
	private Double [] x, y; 
	private String imgPath;
	
	public Polygon returnMapPolygon(Dimension gameDimension)
	{
		Polygon p = new Polygon();
		for(int i=0; i<x.length; i++)
		{
			p.addPoint((int)(x[i]*gameDimension.getWidth()),(int) (y[i]*gameDimension.getHeight()));
		}
		return p;
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
	public void loadMap(String filename)
	{
		System.setProperty("file.encoding","UTF-8");
		 InputStream is;
		 
        try 
        {
        	Properties properties = new Properties();
			is = new FileInputStream(new File(filename));
			properties.load(is);
			imgPath = properties.getProperty("imgPath");
			String [] xTab = properties.getProperty("x").split(",");
			String [] yTab = properties.getProperty("y").split(",");
			x = new Double[xTab.length+2];
			y = new Double[yTab.length+2];
			
			for(int i=1; i<=xTab.length; i++)
			{
				x[i] = Double.parseDouble(xTab[i-1]);
				y[i] = Double.parseDouble(yTab[i-1]);
			}
			x[0]=0d;
			y[0]=1d;
			x[xTab.length+1]=1d;
			y[xTab.length+1]=1d;
			
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
/*	public List<Dimension> addCorners(Dimension gameDimension)
	{
		List<Dimension> toRet=new ArrayList<Dimension>();
		
		Dimension dim1=new Dimension(gameDimension.width,gameDimension.height);
		Dimension dim2=new Dimension(0,gameDimension.height);
		toRet.add(dim1);
		toRet.add(dim2);
		return toRet;
	}*/
}
