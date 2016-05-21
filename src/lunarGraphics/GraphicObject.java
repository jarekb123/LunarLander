package lunarGraphics;

import java.awt.Dimension;
import java.awt.Graphics2D;
/**
 * Klasa abstrakcyjna reprezentująca obiekt graficzny
 * @author jarek
 */
public abstract class GraphicObject {

    /**
     *
     */
    protected String imgPath;
	protected double x,

    /**
     *
     */
    y;
	/**
         * Konstruktor ze współrzędnymi
         * @param x wsp x
         * @param y wsp y
         */
	public GraphicObject(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
        /**
         * Konstruktor ze ścieżką do pliku graficznego
         * @param imgPath ścieżka pliku graficznego
         */
	public GraphicObject(String imgPath)
	{
		this.imgPath = imgPath;
	}
        /**
         * Konstruktor ze ścieżką do pliku graficznego i współrzędnymi
         * @param imgPath ścieżka do pliku graficznego
         * @param x wsp. x
         * @param y wsp. y
         */
        public GraphicObject(String imgPath, double x, double y)
        {
            this(x,y);
            this.imgPath = imgPath;
        }
        /**
         * Metoda ustawiająca ścieżkę do pliku graficznego
         * @param path ścieżka pliku graf.
         */
        public void setImgPath(String path)
        {
            imgPath = path;
        }
        /**
         * Implementacja tej metody pozwala na rysowanie obiektów graficznych na kontekście graficznym
         * @param g2d Kontekst graficzny
         * @param size Aktualny rozmiar okna
         * @param preferredSize Domyślny i preferowany rozmiar okna
         */
        abstract public void paintImage(Graphics2D g2d, Dimension size, Dimension preferredSize);
}
