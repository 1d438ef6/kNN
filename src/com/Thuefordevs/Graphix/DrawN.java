package com.Thuefordevs.Graphix;

import com.Thuefordevs.DataPoint;
import com.Thuefordevs.Netz;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
        double MaxX = n.getMaxCoordinate(d1);
        double MaxY = n.getMaxCoordinate(d2);
        double x0 = 0; double y0 = 0;
        int width = 400; int height = 400;
        //if(n.getMinCoordinate(d1) >= 0 && n.getMinCoordinate(d2) >= 0) {
            x0 = Math.abs(n.getMinCoordinate(d1));
            y0 = Math.abs(n.getMinCoordinate(d2));
            width = (int) (width * (n.getMaxCoordinate(d1) / n.getMaxCoordinate(d2))) + 50;
            height = (int) (height * (n.getMaxCoordinate(d2) / n.getMaxCoordinate(d1))) + 50;
            //x0 = 0; y0 = 0;
        //} else if(n.getMinCoordinate(d1) < 0 && n.getMinCoordinate(d2) >= 0){
            //width = (int) (width * ((Math.abs(n.getMinCoordinate(d1)) + n.getMaxCoordinate(d1))/(Math.abs(n.getMinCoordinate(d2) + n.getMaxCoordinate(d2))))) + 50;
            //height = (int) (height * (n.getMaxCoordinate(d2) / n.getMaxCoordinate(d1))) + 50;
            //x0 = Math.abs(n.getMinCoordinate(d1))*(width/MaxX); y0 = 0;
        //} else if(n.getMinCoordinate(d1) >= 0 && n.getMinCoordinate(d2) < 0){
            //width = (int) (width * (n.getMaxCoordinate(d1) / n.getMaxCoordinate(d2))) + 50;
            //height = (int) (height * ((Math.abs(n.getMinCoordinate(d2)) + n.getMaxCoordinate(d2))/(Math.abs(n.getMinCoordinate(d1) + n.getMaxCoordinate(d1))))) + 50;
            //x0 = 0; y0 = Math.abs(n.getMinCoordinate(d2))*(height/MaxY);
        //} else if (n.getMinCoordinate(d1) < 0 && n.getMinCoordinate(d2) < 0){
            //width = (int) (width * ((Math.abs(n.getMinCoordinate(d1)) + n.getMaxCoordinate(d1))/(Math.abs(n.getMinCoordinate(d2) + n.getMaxCoordinate(d2))))) + 50;
            //height = (int) (height * ((Math.abs(n.getMinCoordinate(d2)) + n.getMaxCoordinate(d2))/(Math.abs(n.getMinCoordinate(d1) + n.getMaxCoordinate(d1))))) + 50;
            //x0 = Math.abs(n.getMinCoordinate(d1))*(width/MaxX); y0 = Math.abs(n.getMinCoordinate(d2))*(height/MaxY);
        //}
        System.out.println(x0 + " : " + y0);
        Pane p = new Pane();
        Line xachse = new Line(0, height-50+y0, width, height-50+y0);
        Line yachse = new Line(50+x0, 0, 50+x0, height);
        p.getChildren().addAll(xachse, yachse);
        for (DataPoint d : dp){
            if(d.isUseable()){
                p.getChildren().add(new Circle((d.getValues()[d1]*(width/MaxX))+50+x0, height - ((d.getValues()[d2]*(height/MaxY))+50) +y0, 5, col[d.getPointClass()%col_size]));
            }
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
