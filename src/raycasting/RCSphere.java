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
public class RCSphere
{
    private Point3D position;
    private double radius;
    private RCMaterial material;
    
    public RCSphere(Point3D position, double radius, RCMaterial material)
    {
        this.position = position;
        this.radius = radius;
        this.material = material;
    }
    
    public Point3D getPosition()
    {
        return position;
    }
    
    public double getRadius()
    {
        return radius;
    }
    
    public RCMaterial getMaterial()
    {
        return material;
    }
    
    public double[][] getPositionMatrix()
    {
        double[][] p = new double[][]{
            {position.getX(), position.getY(), position.getZ(), 1}
        };
        
        return p;
    }
    
}
