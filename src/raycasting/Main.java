/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author asilkaratas
 */
public class Main extends Application
{
    public static final String CSS = "assets/css/style.css";
    
    @Override
    public void start(Stage primaryStage)
    {
        Pane root = new RootView();
        
        Scene scene = new Scene(root, 600, 530);
        scene.getStylesheets().add(CSS);
        
        primaryStage.setTitle("Ray Casting");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
