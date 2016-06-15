package lunarMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.imageio.ImageIO;

/**
 * klasa zawiera dane związane z planszą, czyli ścieżkę do pliku z tłem(imgPath) i punktami podloza(x[],y[])
 * 
 */
public class GameMap {
	
	private Double [] x, y,xLandings,yLandings; 
	private String imgPath;
	
	public Rectangle[] landings;
	boolean firstTime=true;
	//private 
	/**
	 * metoda zwracająca plansze zgodna z wymiarami okna
	 * @param gameDimension- wymiary okna
	 * @return Polygon o wspólrzędnych zgodnych z x[] i y[]
	 */
	public Polygon returnMapPolygon(Dimension gameDimension)
	{
		Polygon p = new Polygon();
		for(int i=0; i<x.length; i++)
		{
			p.addPoint((int)(x[i]*gameDimension.getWidth()),(int) (y[i]*gameDimension.getHeight()));
		}
		if(firstTime)
		{
			createLandings(gameDimension);
			firstTime=false;
		}
		return p;
	}
	public void createLandings(Dimension gameDimension)
	{
		int width=(int)(0.2*gameDimension.getWidth());
		int height=(int)(0.1*gameDimension.getHeight());
		landings=new Rectangle[xLandings.length];
		for(int i=0;i<xLandings.length;i++)
		{
			landings[i]=new Rectangle((int)(xLandings[i]*gameDimension.getWidth()),
					(int)(yLandings[i]*gameDimension.getHeight()),width,height);
		}
		
	}
	/**
	 * metoda zwracająca obraz tła z pliku
	 * @return obraz klasy BufferedImage
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
	/** metoda rysujaca wyskalowaną mapę z grafiką
	 * @param g2d - kontekst graficzny 2D
	 * @param gameDimension - rozdzielczość obszaru rysowania
	 */
	public void paintMap(Graphics2D g2d, Dimension gameDimension)
	{
		g2d.drawImage(getImage(),0,0,(int)gameDimension.getWidth(),(int)gameDimension.getHeight(),null);
		Color c = new Color(255,255,0);
		g2d.setColor(c);
		g2d.draw(returnMapPolygon(gameDimension));
		g2d.setColor(new Color(255,0,0));
		
		for(int i=0;i<landings.length;i++)
		{
			g2d.draw(landings[i]);
		}	
	}
	/**
	 * metoda wczytujaca mapę z pliku konfiguracyjnego
	 * @param filename - ścieżka do pliku
	 */
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
			String[] xlandings=properties.getProperty("landingsx").split(",");
			String[] ylandings=properties.getProperty("landingsy").split(",");
			xLandings=new Double[xlandings.length];
			yLandings=new Double[ylandings.length];
			for(int i=0;i<xlandings.length;i++)
			{
				xLandings[i]=Double.parseDouble(xlandings[i])-0.1;
				yLandings[i]=Double.parseDouble(ylandings[i])-0.1;
			}
			
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
	public Rectangle[] getLandings()
	{
		return landings;
	}

}
