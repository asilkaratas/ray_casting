/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

/**
 *
 * @author asilkaratas
 */
public class RCCamera
{
    private RCImagePlane imagePlane;
    private double angle;
    private double distance;
    
    public RCCamera(RCImagePlane imagePlane, double angle)
    {
        this.imagePlane = imagePlane;
        setAngle(angle);
    }
    
    public void setAngle(double angle)
    {
        this.angle = angle;
        distance = imagePlane.getWidth()/2 * Math.cos(angle/2);
    }
    
    public RCImagePlane getImagePlane()
    {
        return imagePlane;
    }
    
    public double getAngle()
    {
        return angle;
    }
    
    public double getDistance()
    {
        return distance;
    }
}
