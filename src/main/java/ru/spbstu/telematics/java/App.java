package ru.spbstu.telematics.java;

import java.util.Random;

class MyThread extends Thread{
	
	public int start;
	public int end;
	
	MyThread(int a, int b){
		start = a;
		end = b;
	}
	
	@Override
	public void run(){
		 for(int i = start+1; i < end+1; i++){
        	for(int j = 1; j < App.size+1; j++){
        		App.NewMassiv[i-1][j-1] = (App.Massiv[i-1][j] + App.Massiv[i+1][j] + App.Massiv[i][j-1] + App.Massiv[i][j+1] + App.Massiv[i-1][j-1] + App.Massiv[i-1][j+1] + App.Massiv[i+1][j-1] + App.Massiv[i+1][j+1]);
        	}
        }
	}
}

public class App 
{
	public volatile static int[][]NewMassiv;
	public volatile static int[][]Massiv;
	public static final int size = 1000;
	
	
    public static void main( String[] args ) throws InterruptedException{
    	NewMassiv = new int [size][size];
    	Massiv = new int[size+2][size+2];
    	for(int i = 0; i < size+2; i++){
         	for(int j = 0; j < size+2; j++){
         		if(i==0 || j==0 || i==(size+1) || j==(size+1)){
         			Massiv[i][j] = 0;
         		}
         		else{
         			Massiv[i][j] = new Random().nextInt(10);
         		}
         	}
         }
    	 
    	MyThread t1 = new MyThread(0,250);
    	MyThread t2 = new MyThread(250,500);
    	//MyThread t3 = new MyThread(500,750);
    	//MyThread t4 = new MyThread(750,1000);
    	long startTime = System.nanoTime();
    	t1.start();
    	t2.start();
    	//t3.start();
    	//t4.start();
    	
    	t1.join();
    	System.out.println("First stream "+ (System.nanoTime() - startTime));
    	t2.join();
    	System.out.println("Second stream "+ (System.nanoTime() - startTime));
    	/*t3.join();
    	System.out.println("Third stream "+ (System.nanoTime() - startTime));
    	t4.join();
    	System.out.println("Fourth stream "+ (System.nanoTime() - startTime));*/
    	
    	/*for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			System.out.print(NewMassiv[i][j] + " ");
    		}
    		System.out.println();
    	}*/
    	
    }
}
