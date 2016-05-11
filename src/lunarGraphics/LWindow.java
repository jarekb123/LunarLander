package lunarGraphics;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.*;


/**
 * Klasa reperezentująca główne okno naszej gry
 * zawiera elementy reprezentujące preferowane i minimalne wymiary okna
 * 
 * 
 */
@SuppressWarnings("serial")
public class LWindow extends JFrame {
	Dimension preferredDim;
	Dimension minDim;
	/**
	 * konstruktor domyślny klasy okna inicjalizujący panel klasy LPanel
	 */
        JPanel panel;
        LPanel canvas = new LPanel();
	public LWindow()
	{
            super("Lunar Lander");
            panel = (JPanel)getContentPane();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            loadProperties("window.properties");
            
            setPreferredSize(preferredDim);
            setMinimumSize(minDim);

            addKeyListener(canvas);
            panel.add(canvas);
            
            pack();
            setVisible(true);

	}
	/**
	 * funkcja wczytujaca ustawienia okna z pliku
	 * @param filename nazwa pliku
	 */
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
