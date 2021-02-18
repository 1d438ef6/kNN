package com.Thuefordevs;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DataPoint a0 = new DataPoint(new double[]{1, 2});
        DataPoint a1 = new DataPoint(new double[]{-7, 5}, 2);
        DataPoint a2 = new DataPoint(new double[]{1, 3}, 2);
        DataPoint a3 = new DataPoint(new double[]{4, -4}, 2);
        DataPoint a4 = new DataPoint(new double[]{-1, 4}, 1);
        DataPoint a5 = new DataPoint(new double[]{3, 4});
        DataPoint a6 = new DataPoint(new double[]{2, 4}, 2);
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

        /*for(int i = 0; i<100; i++){
            arr.add(new DataPoint(new double[]{Math.random()*15, Math.random()*10}, (int)(Math.random()*3)));
        }*/

        Netz n = new Netz(arr);

        n.removeDoubles();
        System.out.println(n.toString());
        System.out.println(n.getPointClasses());
        //n.removeTomekLinks();
        /*for(int i = 0; i<50; i++){
            arr.add(new DataPoint(new double[]{Math.random()*10, Math.random()*7}, 0));
        }*/
        n.draw();
        n.classify(7);
        n.removeTomekLinks();
        System.out.println(n.toString());
        System.out.println(n.getPointClasses());
        System.out.println(n.getUseablePointClasses());
        //n.draw();
    }
}
