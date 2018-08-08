package com.siomari.SiomariBack;

public class TestWithMain {

	public static void main(String[] args) {
		
		double d = 50;

		double f = 0.531747 + 0.011621 * d - 0.000089 * Math.pow(d, 2) + 0.00000023 * Math.pow(d, 3);

		System.out.println(f);

	}

}