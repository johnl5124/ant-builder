package com.autoant;

import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class RobotMovement
{
	final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput motors;
	private int LEFT_Motor_Forward = 14, RIGHT_Motor_Forward = 12;
	private boolean exit;
	
	public RobotMovement()
	{
		motors = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "m1E");
		exit = false;
	}
	public void stop()
	{
		exit = true;
	}
	public void forward()
	{
		int Velocity = 100;
				
		motors.high();

		SoftPwm.softPwmCreate(LEFT_Motor_Forward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Forward, 0, 100);
		
		SoftPwm.softPwmWrite(LEFT_Motor_Forward, Velocity);
		SoftPwm.softPwmWrite(RIGHT_Motor_Forward, Velocity);		
	}
	

	/* ---- NOTE ----
	 * leftTurn() and rightTurn() will vary in int time as this differs depending on what
	 * surface the robot is on and the strengths/condition of the motors...
	 * 
	 * An example is time = 668 (ms) gives a high chance of completing an exact
	 * 90 degree turn however this may not be the same on wood or carpet...
	 * 
	 * Over the time of running the program, one can expect to see a dramatic difference
	 * in movement results as for a multitude of reasons... such as: surface texture, 
	 * GPIO motor inconsistencies, malfunctions and inconsistent ultrasonic detections. 
	 * 
	 * Throughout running a program, if a slight mistake occurs this can lead to more 
	 * impactful problems further into program runtime. Chaos theory...
	 */
	
	public void leftTurn()
	{
		motors.high();

		int time = 668;
		int LEFT_Motor_Backward = 10;
		int RIGHT_Motor_Forward = 12;
		SoftPwm.softPwmCreate(LEFT_Motor_Backward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Forward, 0, 100);
				
		if (time > 0)
		{
			try
			{
				SoftPwm.softPwmWrite(LEFT_Motor_Backward, 100);
				SoftPwm.softPwmWrite(RIGHT_Motor_Forward, 100);

				Thread.sleep(time);
			}
			catch (InterruptedException e) 
			{
			e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Error time is too low");
		}

		SoftPwm.softPwmWrite(LEFT_Motor_Backward, 0);
		SoftPwm.softPwmWrite(RIGHT_Motor_Forward, 0);
		motors.low();
		gpio.shutdown();
	}
	public void rightTurn()
	{
		motors.high();

		int time = 668;
		int LEFT_Motor_Forward = 14;
		int RIGHT_Motor_Backward = 13;
		SoftPwm.softPwmCreate(LEFT_Motor_Forward, 0, 100);
		SoftPwm.softPwmCreate(RIGHT_Motor_Backward, 0, 100);
				
		if (time > 0)
		{
			try
			{
				SoftPwm.softPwmWrite(LEFT_Motor_Forward, 100);
				SoftPwm.softPwmWrite(RIGHT_Motor_Backward, 100);
				Thread.sleep(time);
			}
			catch (InterruptedException e) 
			{
			e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Error time is too low");
		}

		SoftPwm.softPwmWrite(LEFT_Motor_Forward, 0);
		SoftPwm.softPwmWrite(RIGHT_Motor_Backward, 0);
		motors.low();
		gpio.shutdown();
	}
	public void shutdown()
	{
		SoftPwm.softPwmWrite(LEFT_Motor_Forward, 0);
		SoftPwm.softPwmWrite(RIGHT_Motor_Forward, 0);
		motors.low();
		gpio.shutdown();
	}
}
