package com.siomari.SiomariBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siomari.model.ClimatologiaYear;
import com.siomari.service.IClimatologiaYearService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {

	@Autowired
	private IClimatologiaYearService service;

	@Test
	public void contextLoads() {

		ClimatologiaYear cy = service.buscarPorId(2018);

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writeValueAsString(cy);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

	}

}
