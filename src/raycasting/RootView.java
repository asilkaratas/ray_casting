/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author asilkaratas
 */
public class RootView extends VBox
{
    private RCImagePlane imagePlane;
    private RCCamera camera;
    private RCLight light;
    private RCScene scene;
    
    
    private Slider angleSlider;
    private Slider lightSlider;
    private Slider lightRadiusSlider;
    private ImageView imageView;
    private Label angleValueLabel;
    private CheckBox diffusionCheckBox;
    private CheckBox specularCheckBox;
    
    public RootView()
    {
        imagePlane = new RCImagePlane(500, 400);
        camera = new RCCamera(imagePlane, Math.PI/2);
        light = new RCLight(new Point3D(100, -100, 0), Color.ALICEBLUE);
        scene = new RCScene(camera, light);
        
        addSpheres();
        
        
        //UI
        setSpacing(10);
        
        angleSlider = new Slider(10, 90, 10);
        angleSlider.valueProperty().addListener(new UpdateChangeListener());
        
        imageView = new ImageView(imagePlane.getImage());
        
        Label angleLabel = new Label("angle");
        angleValueLabel = new Label();
        
        diffusionCheckBox = new CheckBox("diffusion");
        specularCheckBox = new CheckBox("specular");
        diffusionCheckBox.selectedProperty().addListener(new UpdateChangeListener());
        specularCheckBox.selectedProperty().addListener(new UpdateChangeListener());
        
        Label lightLabel = new Label("light");
        lightSlider = new Slider(0, 360, 0);
        lightSlider.valueProperty().addListener(new UpdateChangeListener());
        
        Label lightRadiusLabel = new Label("radius");
        lightRadiusSlider = new Slider(0, 500, 200);
        lightRadiusSlider.valueProperty().addListener(new UpdateChangeListener());
        
        GridPane.setConstraints(angleLabel, 0, 0);
        GridPane.setConstraints(angleSlider, 1, 0);
        GridPane.setConstraints(angleValueLabel, 2, 0);
        GridPane.setConstraints(diffusionCheckBox, 1, 1);
        GridPane.setConstraints(specularCheckBox, 1, 2);
        GridPane.setConstraints(lightLabel, 0, 3);
        GridPane.setConstraints(lightSlider, 1, 3);
        GridPane.setConstraints(lightRadiusLabel, 0, 4);
        GridPane.setConstraints(lightRadiusSlider, 1, 4);
        
        GridPane gridPane = new GridPane();
        gridPane.getChildren().addAll(angleLabel, angleSlider, angleValueLabel, 
                diffusionCheckBox, specularCheckBox, lightLabel, lightSlider,
                lightRadiusLabel, lightRadiusSlider);
        
        HBox imageHBox = new HBox(imageView);
        imageHBox.setAlignment(Pos.CENTER);
        
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(gridPane, imageHBox);
        
        BorderedTitledPane titledPane = new BorderedTitledPane("ray casting and phong", vBox);
        getChildren().add(titledPane);
        
        update();
    }
    
    private void addSpheres()
    {
        scene.addSphere(new RCSphere(new Point3D(0, 0, 300), 50, RCMaterial.YELLOW));
        scene.addSphere(new RCSphere(new Point3D(-10, 10, 200), 15, RCMaterial.BLUE));
        scene.addSphere(new RCSphere(new Point3D(-100, 100, 350), 50, RCMaterial.BLUE));
        scene.addSphere(new RCSphere(new Point3D(110, 120, 500), 100, RCMaterial.GREEN));
    }
    
    private void update()
    {
        angleValueLabel.setText(String.valueOf((int)angleSlider.getValue()));
        
        scene.setDisableDiffusion(!diffusionCheckBox.isSelected());
        scene.setDisableSpecular(!specularCheckBox.isSelected());
        
        double lightAngle = lightSlider.getValue() * (Math.PI / 180.0);
        double lightRadius = lightRadiusSlider.getValue();
        Point3D lightPosition = new Point3D(Math.cos(lightAngle) * lightRadius, 
                Math.sin(lightAngle) * lightRadius, 0);
        light.setPosition(lightPosition);
        
        double angle = angleSlider.getValue() * (Math.PI / 180.0);
        camera.setAngle(angle);
        scene.render();
    }
    

    private class UpdateChangeListener implements InvalidationListener
    {
        @Override
        public void invalidated(Observable observable)
        {
            update();
        }
    }
}
