package com.siomari.SiomariBack;

import java.time.LocalDate;

public class TestWithMain {

	public static void main(String[] args) {

		LocalDate f1 = LocalDate.of(2018, 6, 4);
		LocalDate f2 = LocalDate.of(2018, 6, 3);
		
		System.out.println(!f1.isAfter(f2));

	}

}