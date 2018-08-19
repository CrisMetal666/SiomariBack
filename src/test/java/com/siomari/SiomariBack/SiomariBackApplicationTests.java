package com.siomari.SiomariBack;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.model.ProgramacionSemanal;
import com.siomari.repository.ICanalRepository;
import com.siomari.service.IProgramacionSemanalService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {

	@Autowired
	private ICanalRepository repo;
	
	private IProgramacionSemanalService repo2;

	@Test
	public void contextLoads() {

		repo.buscarCanalesServidos(1).forEach(x -> System.out.println(x));
		
		ProgramacionSemanal res = repo2.buscarPorFechaCanalIdYCszu(LocalDate.of(2018, 8, 6), 10, 10, 4);
		
		System.out.println(res);

	}

}
