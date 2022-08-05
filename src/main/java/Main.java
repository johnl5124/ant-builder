import PrototypeTesting.PrototypeTesting.*;

public class Main 
{
	public static void main(String args[]) throws InterruptedException
	{
		System.out.println("Basic Robot Movement Program...");
        
        TestRobot t1 = new TestRobot();
        
        t1.setWheelVelocities(-100, 100, 2500);
	}
}
