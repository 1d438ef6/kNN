package com.Thuefordevs;

import java.util.Arrays;

public class DataPoint {
    //Koordinaten des Punktes
    private double values[];
    //ist der Punkt nutzbar für die Klassifikation?
    private boolean useable;
    //welcher Klasse gehört der punkt an?
    // 0 - keine Klasse
    private int PointClass;

    /**
     * lege DatenPunkt an
     * @param values
     *      double Array, Koordinaten des Punktes
     * @param useable
     *      boolean, ist der Punkt nutzbar für PointClassifikation
     * @param PointClass
     *      integer, welcher PointClasse soll der Punkt angehören?
     */
    public DataPoint(double values[], boolean useable, int PointClass){
        this.values = values;
        this.useable = useable;
        this.PointClass = PointClass;
    }

    /**
     * lege DatenPunkt an
     * @param values
     *      double Array, Koordinaten des Punktes
     * @param useable
     *      boolean, ist der Punkt nutzbar für PointClassifikation
     */
    public DataPoint(double values[], boolean useable){
        this.values = values;
        this.useable = useable;
        this.PointClass = 0;
    }

    /**
     * lege DatenPunkt an
     * @param values
     *      double Array, Koordinaten des Punktes
     * @param PointClass
     *      integer, welcher PointClasse soll der Punkt angehören?
     */
    public DataPoint(double values[], int PointClass){
        this.values = values;
        this.useable = true;
        this.PointClass = PointClass;
    }

    /**
     * lege DatenPunkt an
     * @param values
     *      double Array, Koordinaten des Punktes
     */
    public DataPoint(double values[]){
        this.values = values;
        this.useable = true;
        this.PointClass = 0;
    }

    /**
     * lege leeren Punkt an den koordinaten (0, 0) an mit der PointClasse 0
     */
    public DataPoint(){
        this.values = new double[]{0, 0};
        this.useable = false;
        this.PointClass = 0;
    }

    /**
     *
     * @param dp
     * @param function
     *      manhattan - 1-Norm
     *      euklid - 2-Norm
     *      supremum - infinity-Norm
     *      minkowskix - x-Norm
     * @return
     *      Entfernung zum DatenPunkt dp
     */
    public double dist(DataPoint dp, String function){
        double ret = 0;
        if(this.getNValues() != dp.getNValues()){
            System.err.println("DataPoints don't have the same dimension");
            return -1;
        }
        if(!dp.isUseable() || !this.isUseable()){
            System.err.println("WARNING: at least one of the DataPoints isn't supposed to be used to train or calculate");
        }
        double v1[] = dp.getValues();
        double v2[] = this.getValues();
        if(function.equals("manhatten")){
            for(int i = 0; i<dp.getNValues(); i++){
                ret += Math.abs(v1[i] - v2[i]);
            }
            return ret;
        } else if(function.equals("euklid")){
            for(int i = 0; i<dp.getNValues(); i++){
                ret += Math.pow(Math.abs(v1[i] - v2[i]), 2);

            }
            return Math.sqrt(ret);
        } else if(function.equals("supremum")){
            double[] vh = new double[(int) dp.getNValues()];
            for(int i = 0; i<dp.getNValues(); i++){
                vh[i] += Math.abs(v1[i] - v2[i]);
            }
            ret = vh[0];
            for(int i = 0; i<vh.length; i++){
                if(ret < vh[i]){
                    ret = vh[i];
                }
            }
            return ret;
        } else if(function.startsWith("minkowski")){
            int x;
            try {
                x = Integer.parseInt(function.substring(9));
            } catch (Exception e){
                e.printStackTrace();
                return -1;
            }
            for(int i = 0; i<dp.getNValues(); i++){
                ret += Math.pow(Math.abs(v1[i] - v2[i]), x);
            }
            return Math.pow(ret, (double)1/x);
        } else {
            System.err.println("The called function for distance isn't known yet. I'm sorry.");
            return -1;
        }
    }

    /**
     * gibt alle Koordinaten des Punktes zurück
     * @return
     *      double Array
     */
    public double[] getValues() {
        return values;
    }

    /**
     * gibt die Anzahl der Koordinaten des Punktes an
     * @return
     *      double
     */
    public double getNValues() {
        return this.values.length;
    }

    /**
     * gibt den Wert von useable zurück
     * @return
     *      boolean
     */
    public boolean isUseable(){
        return this.useable;
    }

    /**
     * setzt den wert von useable
     * @param w
     *      boolean
     */
    public void setUseable(boolean w){
        this.useable = w;
    }

    /**
     * invertiert den Wert von useable
     */
    public void changeUsable(){
        this.useable = !this.useable;
    }

    /**
     * Vergleicht zwei Punkte
     * @param dp
     *      DataPoint
     * @return
     *      boolean
     */
    public boolean equals(DataPoint dp){
        if(this.getNValues() != dp.getNValues()){
            return false;
        }
        if(this.getPointClass() != dp.getPointClass()){
            return false;
        }
        return Arrays.equals(this.getValues(), dp.getValues());

    }

    /**
     * gibt die PointClasse des Punktes zurück
     * @return
     *      int
     */
    public int getPointClass() {
        return PointClass;
    }

    /**
     * setzt die PointClasse
     * @param PointClass
     *      int
     */
    public void setPointClass(int PointClass) {
        this.PointClass = PointClass;
    }


    @Override
    public String toString() {
        return "DataPoint{" +
                "values=" + Arrays.toString(values) +
                ", useable=" + useable +
                ", PointClass=" + PointClass +
                '}';
    }

    //--------statische funktionen---------------

    /**
     * berechnet Entfernung zwischen 2 Punkten
     * @param dp1
     *      DataPoint
     * @param dp2
     *      DataPoint
     * @param function
     *      String
     * @return
     *      double
     */
    static public double dist(DataPoint dp1, DataPoint dp2, String function){
        double ret = 0;
        if(dp1.getNValues() != dp2.getNValues()){
            System.err.println("DataPoints don't have the same dimension");
            return -1;
        }
        if(!dp1.isUseable() || !dp2.isUseable()){
            System.err.println("WARNING: at least one of the DataPoints isn't supposed to be used to train or calculate");
        }
        double v1[] = dp1.getValues();
        double v2[] = dp2.getValues();
        if(function.equals("manhatten")){
            for(int i = 0; i<dp1.getNValues(); i++){
                ret += Math.abs(v1[i] - v2[i]);
            }
            return ret;
        } else if(function.equals("euklid")){
            for(int i = 0; i<dp1.getNValues(); i++){
                ret += Math.pow(Math.abs(v1[i] - v2[i]), 2);

            }
            return Math.sqrt(ret);
        } else if(function.equals("supremum")){
            double[] vh = new double[(int) dp1.getNValues()];
            for(int i = 0; i<dp1.getNValues(); i++){
                vh[i] += Math.abs(v1[i] - v2[i]);
            }
            ret = vh[0];
            for(int i = 0; i<vh.length; i++){
                if(ret < vh[i]){
                    ret = vh[i];
                }
            }
            return ret;
        } else if(function.startsWith("minkowski")){
            int x;
            try {
                x = Integer.parseInt(function.substring(9));
            } catch (Exception e){
                e.printStackTrace();
                return -1;
            }
            for(int i = 0; i<dp1.getNValues(); i++){
                ret += Math.pow(Math.abs(v1[i] - v2[i]), x);
            }
            return Math.pow(ret, (double)1/x);
        } else {
            System.err.println("The called function for distance isn't known yet. I'm sorry.");
            return -1;
        }
    }

    /**
     * vergleicht zwei Punkte
     * @param dp1
     *      DataPoint
     * @param dp2
     *      DataPoint
     * @return
     *      boolean
     */
    static public boolean equals(DataPoint dp1, DataPoint dp2){
        return dp1.equals(dp2);
    }
}
