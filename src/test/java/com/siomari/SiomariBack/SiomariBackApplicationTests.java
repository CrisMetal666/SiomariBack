package com.siomari.SiomariBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.repository.IEstructuraControlRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {

	@Autowired
	private IEstructuraControlRepository repo;

	@Test
	public void contextLoads() {

		repo.listarPorCodigo("");
		

	}

}
