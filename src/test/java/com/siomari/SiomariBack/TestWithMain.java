package com.siomari.SiomariBack;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestWithMain {

	public static void main(String[] args) {

		LocalDate fecha = LocalDate.of(2018, 6, 29);
		boolean leapYear = fecha.isLeapYear();
		
		int dia = fecha.getDayOfMonth();
		int dias = fecha.getMonth().length(leapYear);
		
		System.out.println(dia);
		System.out.println(dias - dia);
		System.out.println(fecha.getMonthValue());
		
		LocalDateTime f = LocalDateTime.of(2018, 6, 1, 0, 0,0);
		System.out.println(f);
		

	}

}