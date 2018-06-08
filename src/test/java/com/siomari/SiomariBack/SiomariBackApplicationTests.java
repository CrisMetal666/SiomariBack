package com.siomari.SiomariBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.model.EficienciaPerdidas;
import com.siomari.service.IManejoAguaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {

	@Autowired
	private IManejoAguaService service;

	@Test
	public void contextLoads() {

		String fecha1 = "2018-05-05";
		String fecha2 = "2018-06-28";

		EficienciaPerdidas ep = service.calcularEficienciaPerdidas(3, 4, fecha1, fecha2);
		
		System.out.println(ep);

	}

}
