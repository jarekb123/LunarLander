
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lunarGraphics;

import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author jarek
 */
public class GraphicTitle extends GraphicObject {


    /**
     *
     * @param imgPath
     * @param x
     * @param y
     */

    public GraphicTitle(String imgPath, double x, double y) {
        super(imgPath, x, y);
    }

    @Override
    public void paintImage(Graphics2D g2d, Dimension size, Dimension preferredSize) {
        
    }
    
}
