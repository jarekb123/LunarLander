package lunarGraphics.scenes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import lunarGraphics.GraphicObject;

/**
 *
 * @author jarek
 */
public abstract class Scene implements MouseListener, MouseMotionListener
{
    /**  Kontekst graficzny */
    Graphics2D g2d; 
    /** Flaga pokazująca czy scena zmieniła swój rozmiar */
    boolean isResized = false;
    /** Lista obiektów @class GraphicObject do rysowania */
    ArrayList<GraphicObject> graphicObjects;
    Dimension size;
    Dimension preferredSize;
    public Scene(Dimension size, Dimension preferredSize)
    {
        this.preferredSize = preferredSize;
        this.size = size;
        graphicObjects = new ArrayList<>();      
    }
    /** Abstrakcyjna metoda, której implementacja wykonuje rysowanie obiektów na kontekście graficznym
     * @param g2d Kontekst graficzny
     * @param size Aktualny rozmiar komponentu wyswietlajacego scenę
     * @param preferredSize Preferowany rozmiar komponentu wyświetlającego scenę
     */
    public abstract void updateScene(Graphics2D g2d);
    public void resized(Dimension reSize) 
    { 
        isResized = true;
        size = reSize;
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
