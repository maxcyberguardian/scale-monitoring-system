package com.scalemonitor.app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {

		final double TOLERANCE_KG = 2.00;
		Scanner scan1 = new Scanner(Path.of("data/scale1.txt"), StandardCharsets.UTF_8);
		Scanner scan2 = new Scanner(Path.of("data/scale2.txt"), StandardCharsets.UTF_8);
		double scale1 = 0;
		double scale2 = 0;

		while (scan1.hasNextLine()) {
			String line = scan1.nextLine();

			String[] parts = line.split(",", 2);
			if (parts.length < 2)
				continue;
			String weightStr = parts[1].trim(); // "980.00"
			scale1 = Double.parseDouble(weightStr);

		}

		while (scan2.hasNextLine()) {
			String line = scan2.nextLine();

			String[] parts = line.split(",", 2);
			if (parts.length < 2)
				continue; //

			String weightStr = parts[1].trim(); // "980.00"
			scale2 = Double.parseDouble(weightStr);

		}

		if (Math.abs(scale1 - scale2) <= TOLERANCE_KG) {
			System.out.printf("Weight Scale1: %.2f%nWeight Scale2: %.2f%nScales Calibrated!", scale1, scale2);
		} else {
			System.err.printf(
					"Weight Scale1: %.2f%nWeight Scale2: %.2f%nScales Uncalibrated. Please call Maintenance team.",
					scale1, scale2);
		}

		scan1.close();
		scan2.close();
	}

}
