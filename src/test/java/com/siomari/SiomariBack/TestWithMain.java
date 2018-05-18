package com.siomari.SiomariBack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestWithMain {

	public static void main(String[] args) {
		
		LocalDateTime d = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:m a");
		
		System.out.println(d.format(formatter));
		


	}

}