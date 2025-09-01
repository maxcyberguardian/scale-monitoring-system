package com.scalemonitor.app;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
			final double TOLERANCE_KG = 2.00;
		
			Scanner scan = new Scanner(System.in);
			
			System.out.print("Type the weight of Scale 1: ");
			double scale1 = scan.nextDouble();
			
			System.out.print("Type the weight of Scale 2: ");
			double scale2 = scan.nextDouble();
			
			if(Math.abs(scale1 - scale2) <= TOLERANCE_KG) {
				System.out.println("Scales Calibrated!");
			}
			else {
				System.err.println("Scales Uncalibrated. Please call Maintenance team.");
			}
			
			scan.close();
	}

}
