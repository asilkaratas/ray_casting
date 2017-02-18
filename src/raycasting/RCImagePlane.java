/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author asilkaratas
 */
public class RCImagePlane
{
    private double width;
    private double height;
    
    private WritableImage image;
    
    public RCImagePlane(double width, double height)
    {
        this.width = width;
        this.height = height;
        
        image = new WritableImage((int)width, (int)height);
        
        clear();
    }
    
    public double getWidth()
    {
        return width;
    }
    
    public double getHeight()
    {
        return height;
    }
    
    public WritableImage getImage()
    {
        return image;
    }
    
    public void clear()
    {
        PixelWriter pixelWriter = image.getPixelWriter();
        for(int x = 0; x < width; ++x)
        {
            for(int y = 0; y < height; ++y)
            {
                pixelWriter.setColor(x, y, Color.GRAY);
            }
        }
    }
}
