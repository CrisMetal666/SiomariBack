package com.siomari.SiomariBack;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.siomari.model.ManejoAgua;
import com.siomari.repository.IManejoAguaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiomariBackApplicationTests {

	@Autowired
	private IManejoAguaRepository repo;

	@Test
	public void contextLoads() {
		
		LocalDate fecha1 = LocalDate.of(2018,05,05);
		LocalDate fecha2 = LocalDate.of(2018,05,06);
		
		List<ManejoAgua> lst = repo.buscarPorUnidadIdYRangoFecha(3, fecha1, fecha2);
		
		
		totalCaudalArea(lst).forEach(x -> System.out.println("qe " + x.getqExtraido() + " qs " + x.getqServido() + " area " + x.getArea() + " fecha " + x.getFecha()));

	}
	
private List<ManejoAgua> totalCaudalArea(List<ManejoAgua> lst) {
		
		double qExtraido = 0;
		double qServido = 0;
		double area = 0;
		LocalDate fecha = null;
		//fecha que se tendra encuanta para sumar los valores con fechas iguales
		LocalDate fechaRefenrecia = lst.get(0).getFecha();
		
		// lista donde se guardara los valores que se van sumando
		List<ManejoAgua> lstFinal = new ArrayList<>();
		
		for(ManejoAgua ma : lst) {
			
			if(fechaRefenrecia.isEqual(ma.getFecha())) {
				
				qExtraido += ma.getqExtraido();
				qServido += ma.getqServido();
				area += ma.getArea();
				fecha = ma.getFecha();
				
			} else {
				
				//armamos el objeto 
				ManejoAgua manejoAgua = new ManejoAgua();
				manejoAgua.setqExtraido(qExtraido);
				manejoAgua.setqServido(qServido);
				manejoAgua.setArea(area);
				manejoAgua.setFecha(fecha);
				
				//agregamos a la lista que se retornara
				lstFinal.add(manejoAgua);
				
				//reiniciamos los valores con los valores de el nuevo objeto
				qExtraido = ma.getqExtraido();
				qServido = ma.getqServido();
				area = ma.getArea();
				fecha = ma.getFecha();
				
				// volvemos establecer la fecha con la que se hacen las condiciones
				fechaRefenrecia = ma.getFecha();
			}
		}
		
		//armamos el objeto que se hizo en la ultima iteracion y lo agregamos en la lista 
		ManejoAgua manejoAgua = new ManejoAgua();
		manejoAgua.setqExtraido(qExtraido);
		manejoAgua.setqServido(qServido);
		manejoAgua.setArea(area);
		manejoAgua.setFecha(fecha);
		lstFinal.add(manejoAgua);
		
		return lstFinal;
	}

}
