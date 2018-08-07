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
	private IDecadaService repo;

	@Test
	public void contextLoads() {

		Decada d = repo.probabilidadDel75(3);
		
		System.out.println(d.getDecada1().getEvaporacion() + " " + d.getDecada1().getPrecipitacion());
		System.out.println(d.getDecada2().getEvaporacion() + " " + d.getDecada2().getPrecipitacion());
		System.out.println(d.getDecada3().getEvaporacion() + " " + d.getDecada3().getPrecipitacion());
		
		repo.buscarPorMesYYear(2, 2018);
		
		
	}

}
