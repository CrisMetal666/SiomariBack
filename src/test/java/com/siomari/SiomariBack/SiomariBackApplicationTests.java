package com.siomari.SiomariBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.service.IUsersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {
	
	@Autowired
	private IUsersService repo;

	@Test
	public void contextLoads() {

		int clave = repo.cambiarClave("1075299691", "232322");
		
		System.out.println(clave);
	}

}
