/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import java.util.ArrayList;
import javafx.geometry.Point3D;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author asilkaratas
 */
public class RCScene
{
    private RCCamera camera;
    private RCLight light;
    private ArrayList<RCSphere> spheres;
    
    private Boolean disableDiffusion = false;
    private Boolean disableSpecular = false;
    
    public RCScene(RCCamera camera, RCLight light)
    {
        this.camera = camera;
        this.light = light;
        
        spheres = new ArrayList<RCSphere>();
    }
    
    public void addSphere(RCSphere sphere)
    {
        spheres.add(sphere);
    }
    
    public void setDisableDiffusion(Boolean disableDiffusion)
    {
        this.disableDiffusion = disableDiffusion;
    }
    
    
    public void setDisableSpecular(Boolean disableSpecular)
    {
        this.disableSpecular = disableSpecular;
    }
    
    public void render()
    {
        RCImagePlane imagePlane = camera.getImagePlane();
        int width = (int)imagePlane.getWidth();
        int height = (int)imagePlane.getHeight();
        double halfWidth = imagePlane.getWidth()/2;
        double halfHeight = imagePlane.getHeight()/2;
        
        
        double distance = camera.getDistance();
        WritableImage image = imagePlane.getImage();
        PixelWriter pixelWriter = image.getPixelWriter();
        
        imagePlane.clear();
        
        Point3D cameraPosition = new Point3D(0, 0, 0);
        
        //System.out.println("distance:" + distance);
        
        
        for(int x = 0; x < width; ++x)
        {
            for(int y = 0; y < height; ++y)
            {
                Point3D pixelPosition = new Point3D(x -halfWidth , y-halfHeight, distance);
                Point3D ray= pixelPosition.subtract(cameraPosition).normalize();
                
                double shortestDistance = Double.MAX_VALUE;
                RCSphere intersectedSphere = null;
                
                for(RCSphere sphere : spheres)
                {
                    Point3D center = sphere.getPosition();
                    Point3D originToCenter = center.subtract(cameraPosition);
                    
                    
                    double delta = Math.pow(ray.dotProduct(originToCenter), 2) - 
                                   Math.pow(originToCenter.magnitude(), 2) +
                                   Math.pow(sphere.getRadius(), 2);
                    
                    if(delta < 0)
                    {
                        //no intersection
                    }
                    else
                    {
                        double intersectionDistance = ray.dotProduct(originToCenter)-Math.sqrt(delta);
                        if(intersectionDistance < shortestDistance)
                        {
                            shortestDistance = intersectionDistance;
                            intersectedSphere = sphere;
                        }
                    }
                }
                
                if(intersectedSphere != null)
                {
                    RCMaterial material = intersectedSphere.getMaterial();
                    
                    Point3D intersectionPosition = ray.multiply(shortestDistance);
                    
                    Color originalColor = material.getColor();
                    
                    double r = originalColor.getRed();
                    double g = originalColor.getGreen();
                    double b = originalColor.getBlue();
                    
                    
                    Color lightColor = light.getColor();
                    double lr = lightColor.getRed();
                    double lg = lightColor.getGreen();
                    double lb = lightColor.getBlue();
                    
                    

                    //ambient
                    double ka = material.getAmbient();
                    double la = ka;

                    //diffusion
                    Point3D normal = intersectionPosition.subtract(intersectedSphere.getPosition()).normalize();
                    Point3D lightVector = light.getPosition().subtract(intersectionPosition).normalize();
                    double nl = normal.dotProduct(lightVector);
                    
                    double kd = material.getDiffuse();
                    
                    if(disableDiffusion)
                    {
                        kd = 0;
                    }

                    double ldr = kd * r * lr * nl; 
                    double ldg = kd * g * lg * nl;
                    double ldb = kd * b * lb * nl;
                    
                    /*
                    ldr = ldr > 1 ? 1 : ldr;
                    ldr = ldr < 0 ? 0 : ldr;
                    
                    ldg = ldg > 1 ? 1 : ldg;
                    ldg = ldg < 0 ? 0 : ldg;
                    
                    ldb = ldb > 1 ? 1 : ldb;
                    ldb = ldb < 0 ? 0 : ldb;
                    */

                    //specular
                    Point3D reflection = normal.multiply(2 * nl).subtract(lightVector).normalize();
                    Point3D observerVector = cameraPosition.subtract(intersectionPosition).normalize();
                    double rv = reflection.dotProduct(observerVector);
                    
                    //System.out.println("rv:" + rv);
                    
                    double ks = material.getSpecular();
                    if(disableSpecular)
                    {
                        ks = 0;
                    }

                    double lsr = ks * r * lr * Math.pow(rv, 9);//Math.pow(rv, 2);
                    double lsg = ks * g * lg * Math.pow(rv, 99);//Math.pow(rv, 100);
                    double lsb = ks * b * lb * rv;//Math.pow(rv, 1);

                    /*
                    lsr = lsr > 1 ? 1 : lsr;
                    lsr = lsr < 0 ? 0 : lsr;
                    
                    lsg = lsg > 1 ? 1 : lsg;
                    lsg = lsg < 0 ? 0 : lsg;
                    
                    lsg = lsg > 1 ? 1 : lsg;
                    lsg = lsg < 0 ? 0 : lsg;
                    */
                    
                    //color
                    double alpha = 1;
                    double red = la + ldr + lsr;
                    double green = la + ldg + lsg;
                    double blue = la + ldb + lsb;

                    red = red > 1 ? 1 : red;
                    red = red < 0 ? 0 : red;

                    green = green > 1 ? 1 : green;
                    green = green < 0 ? 0 : green;

                    blue = blue > 1 ? 1 : blue;
                    blue = blue < 0 ? 0 : blue;


                    Color color = new Color(red, green, blue, alpha);
                    pixelWriter.setColor(x, y, color);
                }
            }
        }
    }
    
    
    
}
