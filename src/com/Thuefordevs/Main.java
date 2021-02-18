package com.Thuefordevs;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DataPoint a0 = new DataPoint(new double[]{1, 2});
        DataPoint a1 = new DataPoint(new double[]{7, 5}, 2);
        DataPoint a2 = new DataPoint(new double[]{1, 3}, 2);
        DataPoint a3 = new DataPoint(new double[]{4, 4}, 2);
        DataPoint a4 = new DataPoint(new double[]{1, 4}, 3);
        DataPoint a5 = new DataPoint(new double[]{3, 4});
        DataPoint a6 = new DataPoint(new double[]{2, 4}, 3);
        DataPoint a7 = new DataPoint(new double[]{1.5, 0});

        ArrayList<DataPoint> arr = new ArrayList<>();

        arr.add(a0);
        arr.add(a1);
        arr.add(a2);
        arr.add(a3);
        arr.add(a4);

        arr.add(a5);
        arr.add(a6);

        arr.add(a7);

        Netz n = new Netz(arr);

        n.removeDoubles();
        System.out.println(n.toString());
        n.classify(3);
        //System.out.println(n.toString());
        n.draw();
    }
}
