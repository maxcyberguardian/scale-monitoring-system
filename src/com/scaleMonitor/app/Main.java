package com.scaleMonitor.app;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
			final Double TOLERANCE = 2.00;
		
			Scanner scan = new Scanner(System.in);
			
			System.out.print("Type the weigth of balance1: ");
			double scale1 = scan.nextDouble();
			
			System.out.print("Type the weigth of balance2: ");
			double scale2 = scan.nextDouble();
			
			if(Math.abs(scale1 - scale2) <= TOLERANCE) {
				System.out.println("Scales Calibrated!");
			}
			else {
				System.err.println("Scales Descalibrated. Please call Maintenance SQUAD.");
			}
			
			scan.close();
	}

}
