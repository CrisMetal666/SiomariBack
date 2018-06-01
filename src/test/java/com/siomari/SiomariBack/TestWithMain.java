package com.siomari.SiomariBack;

import java.time.LocalDate;

public class TestWithMain {

	public static void main(String[] args) {
		
		LocalDate d = LocalDate.of(2018,05,20);
		
		LocalDate dn = d.plusWeeks(-1);
		
		System.out.println(d);
		System.out.println(dn);
		


	}

}