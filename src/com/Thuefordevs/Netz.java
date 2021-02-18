package com.Thuefordevs;

import com.Thuefordevs.Graphix.DrawN;

import java.util.ArrayList;
import java.util.Vector;

public class Netz {
    //Array mit den Datenpunkten
    private ArrayList<DataPoint> dp;
    //Anzahl der Koordinaten der Punkte
    private int dim;

    /**
     * erzeuge leeres Feld mit Punkt an (0,0)
     */
    public Netz(){
        dp.add(new DataPoint());
        dim = (int)this.dp.get(0).getNValues();
    }

    /**
     * erzeugt Feld mit dem Punkt dp
     * @param dp
     *      DataPoint
     */
    public Netz(DataPoint dp){
        this.dp.add(dp);
        dim = (int)this.dp.get(0).getNValues();
    }

    /**
     * erzeugt Feld mit den Punkten in dp
     * @param dp
     *      DataPoint
     */
    public Netz(ArrayList<DataPoint> dp){
        this.dp = dp;
        dim = (int)this.dp.get(0).getNValues();
        for(int i = 0; i<dp.size(); i++){
            if(dp.get(i).getNValues() != dim){
                System.err.println("WARNING: DataPoint " + i + " isn't the same size as DataPoint 0");
            }
        }
    }


    /**
     * füge Punkt in Feld ein
     * @param dp
     *      DataPoint
     */
    public void addDataPoint(DataPoint dp){
        if(dp.getNValues() == dim){
            this.dp.add(dp);
        } else {
            System.err.println("DataPoint isn't the same size as the others. It wasn't added.");
        }
    }

    /**
     * füge mehrere Punkte in Feld ein
     * @param dp
     *      ArrayList<DataPoint>
     */
    public void addDataPoints(ArrayList<DataPoint> dp){
        for(int i = 0; i<dp.size(); i++){
            if(dp.get(i).getNValues() == dim){
                this.dp.add(dp.get(i));
            } else {
                System.err.println("DataPoint " + i + " isn't the same size as the others. It wasn't added.");
            }
        }
    }

    /**
     * get the n next neighbours to dp
     * @param dp
     *      DataPoint
     * @param n
     *      int
     * @param funktion
     *      String
     */
    public ArrayList<DataPoint> getNeighbours(DataPoint dp, int n, String funktion){
        ArrayList<DataPoint> arr = this.dp;
        arr.remove(dp);
        ArrayList<DataPoint> ret = new ArrayList<DataPoint>();
        ArrayList<Double> dist = new ArrayList<Double>();
        for(int i = 0; i<this.dp.size(); i++){
            dist.add(dp.dist(arr.get(i), funktion));
        }
        boolean sorted = false;
        double h1 = 0;
        DataPoint h2;
        while(!sorted){
            sorted = true;
            for(int i = 0; i<dist.size()-1;i++){
                if(dist.get(i) > dist.get(i+1)){
                    h1 = dist.get(i);
                    dist.set(i, dist.get(i+1));
                    dist.set(i+1, h1);
                    h2 = arr.get(i);
                    arr.set(i, arr.get(i+1));
                    arr.set(i+1, h2);
                    sorted = false;
                }
            }
        }
        for(int i = 0; i<n; i++){
            ret.add(arr.get(i));
        }
        return ret;
    }

    /**
     * gibt n nachbarn von dp zurück
     * @param dp
     *      DataPoint
     * @param n
     *      int
     * @return
     *      ArrayList<DataPoint>
     */
    public ArrayList<DataPoint> getNeighbours(DataPoint dp, int n){
        return this.getNeighbours(dp, n, "euklid");
    }

    /**
     * gibt den nächsten nutzbaren Punkt zu dp zurück
     * @param dp
     *      DataPoint
     * @param funktion
     *      String
     * @return
     *      DataPoint
     */
    public ArrayList<DataPoint> getNextUseableNeighbours(DataPoint dp, int n, String funktion){

        ArrayList<DataPoint> arrtemp = new ArrayList<>();
        arrtemp = (ArrayList<DataPoint>) this.dp.clone();
        arrtemp.remove(dp);
        ArrayList<DataPoint> arr = new ArrayList<>();
        for(int i = 0; i<arrtemp.size(); i++){
            if(arrtemp.get(i).isUseable()){
                arr.add(arrtemp.get(i));
            }
        }
        ArrayList<Double> dist = new ArrayList<Double>();
        for(int i = 0; i<arr.size(); i++){
            dist.add(dp.dist(arr.get(i), funktion));
        }
        boolean sorted = false;
        double h1 = 0;
        DataPoint h2;
        while(!sorted){
            sorted = true;
            for(int i = 0; i<dist.size()-1;i++){
                if(dist.get(i) > dist.get(i+1)){
                    h1 = dist.get(i);
                    dist.set(i, dist.get(i+1));
                    dist.set(i+1, h1);
                    h2 = arr.get(i);
                    arr.set(i, arr.get(i+1));
                    arr.set(i+1, h2);
                    sorted = false;
                }
            }
        }
        ArrayList<DataPoint> ret = new ArrayList<>();
        for(int i = 0; i<n; i++){
            ret.add(arr.get(i));
        }
        return ret;
    }

    /**
     * gibt den nächsten nutzbaren Punkt zu dp zurück
     * @param dp
     *      DataPoint
     * @return
     *      DataPoint
     */
    public ArrayList<DataPoint> getNextUseableNeighbours(DataPoint dp, int n){
        return this.getNextUseableNeighbours(dp, n, "euklid");
    }

    /**
     * gibt den nächsten nutzbaren nachbarn aus
     * @param dp
     *      DataPoint
     * @param funktion
     *      String
     * @return
     *      Datapoint
     */
    public DataPoint getNextUseableNeighbour(DataPoint dp, String funktion){

        ArrayList<DataPoint> arrtemp = new ArrayList<>();
        arrtemp = (ArrayList<DataPoint>) this.dp.clone();
        arrtemp.remove(dp);
        ArrayList<DataPoint> arr = new ArrayList<>();
        for(int i = 0; i<arrtemp.size(); i++){
            if(arrtemp.get(i).isUseable()){
                arr.add(arrtemp.get(i));
            }
        }
        ArrayList<Double> dist = new ArrayList<Double>();
        for(int i = 0; i<arr.size(); i++){
            dist.add(dp.dist(arr.get(i), funktion));
        }
        boolean sorted = false;
        double h1 = 0;
        DataPoint h2;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < dist.size() - 1; i++) {
                if (dist.get(i) > dist.get(i + 1)) {
                    h1 = dist.get(i);
                    dist.set(i, dist.get(i + 1));
                    dist.set(i + 1, h1);
                    h2 = arr.get(i);
                    arr.set(i, arr.get(i + 1));
                    arr.set(i + 1, h2);
                    sorted = false;
                }
            }
        }
        return arr.get(0);
    }

    /**
     * gibt den nächsten nutzbaren nachbarn aus
     * @param dp
     *      DataPoint
     * @return
     *      Datapoint
     */
    public DataPoint getNextUseableNeighbour(DataPoint dp){
        return this.getNextUseableNeighbour(dp, "euklid");
    }

    /**
     * gibt die Anzahl der Punkte des Netzes zurück
     * @return
     *      int
     */
    public int getDataSetSize(){
        return this.dp.size();
    }

    public double getMaxCoordinate(int x){
        if (x > this.dim){
            System.err.println("The maximum you can ask for is:" + this.dim);
        }
        double h = this.dp.get(0).getValues()[x];
        for(DataPoint d : this.dp){
            if(d.getValues()[x] > h){
                h = d.getValues()[x];
            }
        }
        return h;
    }

    public double getMinCoordinate(int x){
        if (x > this.dim){
            System.err.println("The maximum you can ask for is:" + this.dim);
        }
        double h = this.dp.get(0).getValues()[x];
        for(DataPoint d : this.dp){
            if(d.getValues()[x] < h){
                h = d.getValues()[x];
            }
        }
        return h;
    }

    public ArrayList<DataPoint> getPoints(){
        return this.dp;
    }

    //-----edit data set------

    /**
     * entfernt doppelte Punkte
     */
    public void removeDoubles(){
        ArrayList<DataPoint> help = new ArrayList<DataPoint>();
        ArrayList<DataPoint> remove = new ArrayList<DataPoint>();
        boolean drin = false;
        for(int i = 0; i < dp.size(); i++){
            for(int j = 0; j < help.size(); j++){
                if(dp.get(i).equals(help.get(j))){
                    drin = true;
                    break;
                }
            }
            if(drin){
                remove.add(dp.get(i));
                drin = false;
            } else {
                help.add(dp.get(i));
                drin = false;
            }
        }
        for(int i = 0; i<remove.size();i++){
            dp.remove(remove.get(i));
        }
    }

    /**
     * entfernt Tomek Links
     */
    public void removeTomekLinks(){
        for(int i = 0; i<this.dp.size(); i++){
            if((this.dp.get(i).isUseable())
              && (this.dp.get(i).getPointClass() != this.getNextUseableNeighbour(this.dp.get(i)).getPointClass())
              && (this.dp.get(i).getPointClass() != 0)
              && (this.getNextUseableNeighbour(this.dp.get(i)).getPointClass() != 0)){
                this.getNextUseableNeighbour(this.dp.get(i)).setUseable(false);
                this.dp.get(i).setUseable(false);
            }
        }
    }

    //--------PointClassifiziere--------

    /**
     * gibt alle vorhandenen PointClassen zurück
     * @return
     *      ArrayList<Integer>
     */
    public ArrayList<Integer> getPointClasses(){
        ArrayList<Integer> ret = new ArrayList<>();
        boolean add = true;
        for(int i = 0; i<this.dp.size(); i++){
            for(int j = 0; j<ret.size(); j++){
                if (dp.get(i).getPointClass() == ret.get(j)) {
                    add = false;
                }
            }
            if(add){
                ret.add(this.dp.get(i).getPointClass());

            }
            if(!add) add = true;
        }
        return ret;
    }

    /**
     * gibt alle vorhandenen PointClassen in einer ArrayList zurück
     * @return
     *      ArrayList<Integer>
     */
    public ArrayList<Integer> getPointClasses(ArrayList<DataPoint> dataPoints){
        ArrayList<Integer> ret = new ArrayList<>();
        boolean add = true;
        for(int i = 0; i<dataPoints.size(); i++){
            for(int j = 0; j<ret.size(); j++){
                if (dataPoints.get(i).getPointClass() == ret.get(j)) {
                    add = false;
                }
            }
            if(add){
                ret.add(dataPoints.get(i).getPointClass());

            }
            if(!add) add = true;
        }
        return ret;
    }

    /**
     * gibt einen Counter mit der Anzahl der PointClassen zurück
     * @param dp
     *      DataPoint
     * @return
     *      Counter
     */
    public Counter getNPointClasses(ArrayList<DataPoint> dp){
        Counter c = new Counter();
        for(int i = 0; i<dp.size(); i++){
            c.add(dp.get(i).getPointClass());
        }
        return c;
    }


    public void classify(int k, boolean weighted, String funktion){
        //soll gewichteter kNN genutzt werden?
        if(weighted){
            //anzahl der Durchläufe
            int i = 0;
            //solange nicht komplett klassifiziert wurde wiederholen
            while (!isClassified() && i<10){
                //alle Datenpunkte durchlaufen
                for(int j = 0; j<this.dp.size(); j++){
                    //wenn datenpunkt unklassifiziert
                    if(this.dp.get(j).getPointClass() == 0){
                        //Liste mit den k nächsten Nachbarn
                        ArrayList<DataPoint> nn = this.getNextUseableNeighbours(this.dp.get(j), k);
                        //Liste für die distanzen der Punkte
                        ArrayList<Double> dist = new ArrayList<>();
                        //array für die gewichteten Distanzen
                        //sortiert nach Klassen
                        ArrayList<Double> weighted_dist = new ArrayList<>();
                        //Alle Klassen in den Nächsten Nachbarn
                        Counter c = this.getNPointClasses(nn);
                        int h1 = c.getX().get(0);
                        //maximale Klasse besimmen
                        for (int i2 = 0; i2<c.getX().size(); i2++){
                            if(c.getX().get(i2) > h1){
                                h1 = c.getX().get(i2);
                            }
                        }
                        for (int i2 = 0; i2<=h1; i2++){
                            //befüllen der gewichteten Distanzen
                            weighted_dist.add(0.0);
                        }
                        for(int i2 = 0; i2<nn.size(); i2++){
                            //Distanzen berechnen
                            dist.add(this.dp.get(j).dist(nn.get(i2), "euklid"));
                        }
                        for(int i2 = 0; i2<nn.size(); i2++){
                            //Gewichtsformel
                            //if dk != d1 : (dk-di)/(dk-d1)
                            //else : 1
                            if(dist.get(k-1) == dist.get(0)){
                                weighted_dist.set(nn.get(i2).getPointClass(), weighted_dist.get(nn.get(i2).getPointClass())+(1));
                            } else {
                                weighted_dist.set(nn.get(i2).getPointClass(),
                                        weighted_dist.get(nn.get(i2).getPointClass())+
                                                (((dist.get(k-1) - dist.get(i2)))
                                                        / (dist.get(k-1) - dist.get(0))));
                            }
                        }
                        //hilfsvariablen
                        h1 = 0;
                        double h2 = weighted_dist.get(0);
                        //maximum in weighted_dist besimmen
                        for (int i2 = 0; i2<weighted_dist.size(); i2++){
                            if(weighted_dist.get(i2) > h2){
                                h2 = weighted_dist.get(i2);
                                h1 = i2;
                            }
                        }
                        //Klasse des Punktes setzen
                        this.dp.get(j).setPointClass(h1);
                    }
                }
                //anzahl Durchläufe inkrementieren
                i++;
            }
        } else {
            int i = 0;
            while (!isClassified() && i<10){
                for(int j = 0; j<this.dp.size(); j++){
                    if(this.dp.get(j).getPointClass() == 0){
                        ArrayList<DataPoint> nn = this.getNextUseableNeighbours(this.dp.get(j), k, funktion);
                        Counter c = this.getNPointClasses(nn);
                        c.sort();
                        this.dp.get(j).setPointClass(c.getX().get(0));
                    }
                }
                i++;
            }
        }
    }

    public void classify(int k, boolean weighted){
        this.classify(k, weighted, "euklid");
    }

    public void classify(int k){
        this.classify(k, false);
    }

    public boolean isClassified(){
        boolean ret = true;
        for(int i = 0; i<this.dp.size(); i++){
            if(this.dp.get(i).getPointClass() == 0){
                ret = false;
            }
        }
        return ret;
    }


    //--------show Data---------

    /**
     * zeichne die Koordinaten d1 und d2 in ein Koordinatensystem
     * @param d1
     *      integer
     * @param d2
     *      integer
     */
    public void draw(int d1, int d2){
        DrawN.draw(this, d1, d2);
    }

    /**
     * zeichne die ersten beiden koordinaten in ein Koordinatensystem
     */
    public void draw(){
        this.draw(0, 1);
    }


    @Override
    public String toString() {
        return "Netz{" +
                "dp=" + dp +
                ", dim=" + dim +
                '}';
    }
}
