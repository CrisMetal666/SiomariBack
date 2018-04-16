package com.siomari.SiomariBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.model.Decada;
import com.siomari.service.IDecadaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {

	@Autowired
	private IDecadaService service;

	@Test
	public void contextLoads() {

		Decada d = service.probabilidadDel75(4);
		
		System.out.println(d.getDecada1());
		System.out.println(d.getDecada2());
		System.out.println(d.getDecada3());

	}

}
