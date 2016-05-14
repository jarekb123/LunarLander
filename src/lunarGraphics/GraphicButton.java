/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lunarGraphics;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

/**
 *
 * @author jarek
 */
public class GraphicButton extends GraphicObject implements ImageObserver 
{
    Image img = null;
    Image imgMouseOn = null;
    boolean isMouseOn;
    public GraphicButton(String imgPath, double x, double y) {
        super(imgPath, x, y);
        new Thread(new Runnable() {
            @Override
            public void run() {
                img = Toolkit.getDefaultToolkit().getImage(imgPath);
                Toolkit.getDefaultToolkit().prepareImage(img, -1, -1, GraphicButton.this);
            }
        }).start();
        
        
        
    }
    public GraphicButton(String imgPath, String imgMouseOnPath, double x, double y)
    {
        super(imgPath, x, y);
        new Thread(new Runnable() {
            @Override
            public void run() {
                img = Toolkit.getDefaultToolkit().getImage(imgPath);
                Toolkit.getDefaultToolkit().prepareImage(img, -1, -1, GraphicButton.this);
            }
        }).start();
        loadMouseOnImage(imgMouseOnPath).start();
    }
    

    @Override
    public void paintImage(Graphics2D g2d, Dimension size, Dimension preferredSize) {
        Image img1 = null;
        if(isMouseOn && imgMouseOn != null)
        {
            img1 = imgMouseOn;
        }
        else img1 = img;
        if(img1 != null)
        {
            int scaleX = size.width/preferredSize.width;
            int scaleY = size.height/preferredSize.height;

            int width = img1.getWidth(this)*scaleX;
            int height = img1.getHeight(this)*scaleY;
            int xx = (int)(x*size.width) - width/2;
            int yy = (int)(y*size.height) - height/2;
            g2d.drawImage(img1, xx, yy, width, height, this);      
        }
    }
    public Rectangle getButtonRect(Dimension sceneSize, Dimension preferredSize)
    {
        int scaleX = sceneSize.width/preferredSize.width;
        int scaleY = sceneSize.height/preferredSize.height;
    
        int width = img.getWidth(this)*scaleX;
        int height = img.getHeight(this)*scaleY;
        int xx = (int)(x*sceneSize.width) - width/2;
        int yy = (int)(y*sceneSize.height) - height/2;
        return new Rectangle(xx, yy, width, height);
    }
    public Thread loadMouseOnImage(String imgPath)
    {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                imgMouseOn = Toolkit.getDefaultToolkit().getImage(imgPath);
                Toolkit.getDefaultToolkit().prepareImage(imgMouseOn, -1, -1, GraphicButton.this);
            }
        });
    }
    public void mouseEntered()
    {
        isMouseOn = true;
    }
    
    public void mouseExited()
    {
        isMouseOn = false;
    }
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
    
}
