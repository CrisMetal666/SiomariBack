package com.siomari.SiomariBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.model.dto.ConsultaCanal;
import com.siomari.service.ICanalService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {

	@Autowired
	private ICanalService service;

	@Test
	public void contextLoads() {

		ConsultaCanal c = service.consultaCompleta(3);
		
		System.out.println(c);

	}

}
