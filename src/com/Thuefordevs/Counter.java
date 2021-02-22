package com.Thuefordevs;

import java.util.ArrayList;

public class Counter {
    private ArrayList<Integer> x;
    private ArrayList<Integer> y;

    /**
     * neues Counter Objekt
     */
    public Counter(){
        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
    }

    /**
     * addiere parameter x zum counter
     * @param x
     *      int
     */
    public void add(int x){
        boolean drin = false;
        for (int i = 0; i<this.x.size(); i++){
            if (x == this.x.get(i)){
                this.y.set(i, this.y.get(i)+1);
                drin = true;
                break;
            }
        }
        if(!drin){
            this.x.add(x);
            this.y.add(1);
        }
    }

    /**
     * gibt den x - Array zurück
     * @return
     *      ArrayList<Integer>
     */
    public ArrayList<Integer> getX() {
        return x;
    }

    /**
     * gibt den y - Array zurück
     * @return
     *      ArrayList<Integer>
     */
    public ArrayList<Integer> getY() {
        return y;
    }

    /**
     * leere Counter
     */
    public void delete(){
        this.x.clear();
        this.y.clear();
    }

    /**
     * sortiere Counter absteigend nach Array y
     */
    public void sort(){
        boolean sorted = false;
        int h1 = 0;
        int h2 = 0;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < y.size() - 1; i++) {
                if (y.get(i) < y.get(i + 1)) {
                    h1 = y.get(i);
                    y.set(i, y.get(i + 1));
                    y.set(i + 1, h1);
                    h2 = x.get(i);
                    x.set(i, x.get(i + 1));
                    x.set(i + 1, h2);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Counter{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
