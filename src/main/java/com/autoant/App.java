package com.autoant;

public class App 
{
	
    public static void main( String[] args ) throws InterruptedException
    {
        System.out.println("Basic Robot Movement Program...");
        
        RobotMovement t1 = new RobotMovement();
        
        t1.forward();
        
        Thread.sleep(2000);
        
        t1.stop();
        
    }
}
