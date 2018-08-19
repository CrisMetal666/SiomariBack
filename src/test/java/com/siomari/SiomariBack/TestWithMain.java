package com.siomari.SiomariBack;

public class TestWithMain {

	public static void main(String[] args) {

		double area = 100;
		double caudal = area * 5 / 1000f;
		double lamina = caudal * 604800 / (area * 10000);

		System.out.println("area " + area + "\ncaudal  " + caudal + "\nlamina " + lamina);

	}

}