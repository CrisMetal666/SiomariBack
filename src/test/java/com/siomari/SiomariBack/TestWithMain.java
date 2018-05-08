package com.siomari.SiomariBack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestWithMain {

	public static void main(String[] args) {

		String txtFecha = "2018-05-02";
		
		LocalDate d = LocalDate.parse(txtFecha, DateTimeFormatter.ISO_DATE);
		
		System.out.println(d);

	}

}