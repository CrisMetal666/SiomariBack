package com.siomari.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.ClimatologiaDatos;
import com.siomari.repository.IClimatologiaDatosRepository;
import com.siomari.service.IClimatologiaDatosService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class ClimatologiaDatosServiceImpl implements IClimatologiaDatosService {
	
	@Autowired
	private IClimatologiaDatosRepository climatologiaDatosRepo;

	/**
	 * @see com.siomari.service.IClimatologiaDatosService
	 */
	@Override
	public ClimatologiaDatos buscarPorMesYYear(int mes, int year) {
		
		ClimatologiaDatos climatologiaDatos = null;
		
		if(year < 1800 || mes < 0 || mes > 12) {
			
			return new ClimatologiaDatos();
			
		} else if(mes == 1) {
			
			climatologiaDatos = climatologiaDatosRepo.datosEneroPorYear(year);
			
		} else if(mes == 2) {
			
			climatologiaDatos = climatologiaDatosRepo.datosFebreroPorYear(year);
			
		} else if(mes == 3) {
			
			climatologiaDatos = climatologiaDatosRepo.datosMarzoPorYear(year);
			
		} else if(mes == 4) {
			
			climatologiaDatos = climatologiaDatosRepo.datosAbrilPorYear(year);
			
		} else if(mes == 5) {
			
			climatologiaDatos = climatologiaDatosRepo.datosMayoPorYear(year);
			
		} else if(mes == 6) {
			
			climatologiaDatos = climatologiaDatosRepo.datosJunioPorYear(year);
			
		} else if(mes == 7) {
			
			climatologiaDatos = climatologiaDatosRepo.datosJulioPorYear(year);
			
		} else if(mes == 8) {
			
			climatologiaDatos = climatologiaDatosRepo.datosAgostoPorYear(year);
			
		} else if(mes == 9) {
			
			climatologiaDatos = climatologiaDatosRepo.datosSeptiembrePorYear(year);
			
		} else if(mes == 10) {
			
			climatologiaDatos = climatologiaDatosRepo.datosOctubrePorYear(year);
			
		} else if(mes == 11) {
			
			climatologiaDatos = climatologiaDatosRepo.datosNoviembrePorYear(year);
			
		} else if(mes == 12) {
			
			climatologiaDatos = climatologiaDatosRepo.datosDiciembrePorYear(year);
			
		}
		
		//Aseguramos de no devolver objetos nulos
		if(climatologiaDatos == null) climatologiaDatos = new ClimatologiaDatos();
		
		return climatologiaDatos;
	}

}
