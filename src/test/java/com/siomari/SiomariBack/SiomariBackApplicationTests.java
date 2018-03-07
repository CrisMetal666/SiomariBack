package com.siomari.SiomariBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.repository.IClimatologiaYearRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {
	
	@Autowired
	private IClimatologiaYearRepository repo;
	
	

	@Test
	public void contextLoads() {
		
		repo.buscarPorId(2018);
		
	}

}
