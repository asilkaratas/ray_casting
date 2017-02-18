/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import javafx.scene.paint.Color;

/**
 *
 * @author asilkaratas
 */
public class RCMaterial
{
    public static final RCMaterial RED = new RCMaterial(Color.RED, 0.1, .5, 0.4);
    public static final RCMaterial YELLOW = new RCMaterial(Color.YELLOW, 0.1, .5, 0.4);
    public static final RCMaterial BLUE = new RCMaterial(Color.BLUE, 0.1, .4, 0.4);
    public static final RCMaterial GREEN = new RCMaterial(Color.GREEN, 0.1, .6, 0.5);
    public static final RCMaterial ORANGE = new RCMaterial(Color.ORANGE, 0.1, .5, 0.6);
    
    private Color color;
    private double ambient;
    private double diffuse;
    private double specular;
    
    public RCMaterial(Color color, double ambient, double diffuse, double specular)
    {
        this.color = color;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public double getAmbient()
    {
        return ambient;
    }
    
    public double getDiffuse()
    {
        return diffuse;
    }
    
    public double getSpecular()
    {
        return specular;
    }
}
