package lunarGraphics;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.*;

@SuppressWarnings("serial")
public class LWindow extends JFrame {
	Dimension preferredDim;
	Dimension minDim;
	public LWindow()
	{
		super("Lunar Lander");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			loadProperties("window.properties");
			
			setPreferredSize(preferredDim);
			setMinimumSize(minDim);
			
			LPanel panel = new LPanel();
			
			add(panel);
			
			pack();
			setVisible(true);

	}
	private void loadProperties(String filename)
	{
		try 
		{
			InputStream is = new FileInputStream(filename);
			Properties properties = new Properties();
			
			properties.load(is);
			int minResX = Integer.parseInt(properties.getProperty("minimumResX"));
			int minResY = Integer.parseInt(properties.getProperty("minimumResY"));
			minDim = new Dimension(minResX, minResY);
			
			int preferredResX = Integer.parseInt(properties.getProperty("preferredResX"));
			int preferredResY = Integer.parseInt(properties.getProperty("preferredResY"));
			preferredDim = new Dimension(preferredResX, preferredResY);
				
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Blad wczytywania pliku - ustawienia okna: " + filename);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
}
