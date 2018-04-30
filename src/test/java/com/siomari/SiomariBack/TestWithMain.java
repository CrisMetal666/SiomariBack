package com.siomari.SiomariBack;

public class TestWithMain {

	public static void main(String[] args) {

		double x[] = { 0.1, 10, 5, 3, 6, 1, 2, 7, };

		double y[] = { 0.01, 100, 25, 9, 36, 1, 4, 49 };
		
		metodoRegresionPotencial(x, y);
	}
	
	private static double[] metodoRegresionPotencial(double x[], double y[]) {

		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumX2 = 0;
		int n = x.length;

		for (int i = 0; i < n; i++) {

			sumX += Math.log10(x[i]);
			sumY += Math.log10(y[i]);
			sumXY += Math.log10(x[i]) * Math.log10(y[i]);
			sumX2 += Math.pow(Math.log10(x[i]), 2);
			
			System.out.println(sumX + " "  + sumY+ " "  + sumXY+ " "  + sumX2);

		}

		double b = (n * sumXY - sumX * sumY) / (n * sumX2 - Math.pow(sumX, 2));
		double aPrima = (sumY / n) - b * (sumX / n);

		double a = Math.pow(10, aPrima);
		
		System.out.println(a + " "  + b);

		return new double[] { a, b };
	}
}