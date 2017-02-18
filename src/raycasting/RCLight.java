/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

/**
 *
 * @author asilkaratas
 */
public class RCLight
{
    private Point3D position;
    private Color color;
    
    public RCLight(Point3D position, Color color)
    {
        this.position = position;
        this.color = color;
    }
    
    public Point3D getPosition()
    {
        return position;
    }
    
    public void setPosition(Point3D position)
    {
        this.position = position;
    }
    
    public Color getColor()
    {
        return color;
    }
}
