package com.siomari.SiomariBack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestWithMain {

	public static void main(String[] args) {
		
		String strFecha = "2018-07-01";

		LocalDate fecha = LocalDate.parse(strFecha, DateTimeFormatter.ISO_DATE);
		
		System.out.println(fecha.getYear() + " " + fecha.getMonthValue());

	}

}