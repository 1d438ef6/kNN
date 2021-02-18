package com.Thuefordevs.Graphix;

import com.Thuefordevs.DataPoint;
import com.Thuefordevs.Netz;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrawN extends Application {

    static private Color[] col = {Color.LIGHTGRAY, Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.AZURE, Color.GRAY, Color.FUCHSIA, Color.LIMEGREEN};
    static private int col_size = 9;

    static public void draw(Netz n){
        draw(n, 0, 1);
    }

    static public void draw(Netz n, int d1, int d2){
        ArrayList<DataPoint> dp = n.getPoints();
        int width = 400; int height = 400;
        if(n.getMinCoordinate(d1) >= 0 && n.getMinCoordinate(d2) >= 0){
                width = (int) (width * (n.getMaxCoordinate(d1)/n.getMaxCoordinate(d2))) + 50;
                height = (int) (height * (n.getMaxCoordinate(d2)/n.getMaxCoordinate(d1))) + 50;
        }
        Pane p = new Pane();
        Line xachse = new Line(0, height-50, width, height-50);
        Line yachse = new Line(50, 0, 50, height);
        p.getChildren().addAll(xachse, yachse);
        Circle[] circles = new Circle[n.getDataSetSize()];
        int i = 0;
        for (DataPoint d : dp){
            circles[i] = new Circle((d.getValues()[d1]*(width/dp.size()))+50, height - ((d.getValues()[d2]*(height/dp.size()))+50), 5, col[d.getPointClass()%col_size]);
            p.getChildren().add(circles[i]);
            i++;
        }

        int finalHeight = height;
        int finalWidth = width;
        Platform.startup(() -> {
            // create primary stage
            Stage s = new Stage();
            s.setTitle("Punkte");
            Scene scene = new Scene(p, finalWidth, finalHeight);
            s.setScene(scene);
            s.show();
        });


    }

    @Override
    public void start(Stage stage) throws Exception {

        Stage s = new Stage();
        s.setTitle("Punkte");
        Scene scene = new Scene(new Button("test"), 200, 200);
        s.setScene(scene);
        s.show();
    }
}
