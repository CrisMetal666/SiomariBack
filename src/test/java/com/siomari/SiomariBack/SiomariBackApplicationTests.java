package com.siomari.SiomariBack;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.repository.IManejoAguaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {

	@Autowired
	private IManejoAguaRepository repo;

	@Test
	public void contextLoads() {
		
		LocalDate fecha = LocalDate.of(2018,05,05);

		Integer id = repo.buscarIdPorCanalIdYFecha(3, fecha);
		
		System.out.println(id);

	}

}
