package com.siomari.SiomariBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.service.IProgramacionSemanalService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {

	@Autowired
	private IProgramacionSemanalService service;

	@Test
	public void contextLoads() {
		
		String txtFecha = "2018-05-14";

		System.out.println(service.programacionSemanal(3, txtFecha));

	}

}
