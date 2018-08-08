package com.siomari.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * herramientas para el manejo de fechas, meses
 * 
 * @author crismetal
 *
 */
@Component
public class FormatoFechas {

	private final String MESES_INT_TO_STRING[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
			"Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
	private final Map<String, Integer> MESES_STRING_TO_INT;
	
	public FormatoFechas() {
		MESES_STRING_TO_INT = new HashMap<>();
		MESES_STRING_TO_INT.put("Enero", 1);
		MESES_STRING_TO_INT.put("Febrero", 2);
		MESES_STRING_TO_INT.put("Marzo", 3);
		MESES_STRING_TO_INT.put("Abril", 4);
		MESES_STRING_TO_INT.put("Mayo", 5);
		MESES_STRING_TO_INT.put("Junio", 6);
		MESES_STRING_TO_INT.put("Julio", 7);
		MESES_STRING_TO_INT.put("Agosto", 8);
		MESES_STRING_TO_INT.put("Septiembre", 9);
		MESES_STRING_TO_INT.put("Octubre", 10);
		MESES_STRING_TO_INT.put("Noviembre", 11);
		MESES_STRING_TO_INT.put("Diciembre", 12);
	}

	/**
	 * se convertiera el valor numerico de un mes a su valor textual
	 * 
	 * @param mesNumerico
	 *            debe ser del 1 - 12, siendo 1 enero y 12 diciembre
	 * @return valor textual del mes
	 */
	public String mesNumericoAMesTexto(int mesNumerico) {
		
		return MESES_INT_TO_STRING[mesNumerico - 1];
	}
	
	public int mesTextoAMesNumerico(String mes) {
		
		return MESES_STRING_TO_INT.get(mes);
	}
}
