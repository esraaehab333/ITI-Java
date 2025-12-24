package com.mycompany.helloworld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class App extends Application {

    @Override
    public void start(Stage stage) {
  
        StackPane root = new StackPane(); // comp... var args accept zeros 
        Scene scene = new Scene(root , 400 , 500);
        // put the scene on the stage 
        stage.setScene(scene);
        // open the window to show او زي مهنقول افتح الستاره 
        Stop[] stops = new Stop[] {
              new Stop(0.0, Color.BLACK),
              new Stop(0.5, Color.WHITE),
              new Stop(1.0, Color.BLACK)
        };

        LinearGradient gradient = new LinearGradient(
                0, 1, 0, 0,
                true,
                CycleMethod.NO_CYCLE,
                stops
        );
        
        Rectangle bg = new Rectangle(400, 500);
        bg.widthProperty().bind(scene.widthProperty());
        bg.heightProperty().bind(scene.heightProperty());
        bg.setFill(gradient);

        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        
        Text txt = new Text("Hello World");
        
        txt.setFont(Font.font(null, FontWeight.BOLD, 40));
        txt.setFill(Color.RED);
        txt.setEffect(reflection);
        
        root.getChildren().addAll(bg, txt);
        
        txt.setCache(true);
   
        stage.show();
    }

    public static void main(String[] args) {
        // وظيفتها تخلي الابلكيشن يشتغل و ميوقفش 
        // infinite loop لحد متخلص منه 
        launch();
    }

}