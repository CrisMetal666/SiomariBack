package com.siomari.util;

import org.springframework.stereotype.Component;

/**
 * herramientas para el manejo de fechas, meses
 * 
 * @author crismetal
 *
 */
@Component
public class FormatoFechas {

	/**
	 * se convertiera el valor numerico de un mes a su valor textual
	 * @param mesNumerico debe ser del 1 - 12, siendo 1 enero y 12 diciembre
	 * @return valor textual del mes
	 */
	public String mesNumericoAMesTexto(int mesNumerico) {

		String mes = "";

		if (mesNumerico == 1) {

			mes = "Enero";

		} else if (mesNumerico == 2) {

			mes = "Febrero";

		} else if (mesNumerico == 3) {

			mes = "Marzo";

		} else if (mesNumerico == 4) {

			mes = "Abril";

		} else if (mesNumerico == 5) {

			mes = "Mayo";

		} else if (mesNumerico == 6) {

			mes = "Junio";

		} else if (mesNumerico == 7) {

			mes = "Julio";

		} else if (mesNumerico == 8) {

			mes = "Agosto";

		} else if (mesNumerico == 9) {

			mes = "Septiembre";

		} else if (mesNumerico == 10) {

			mes = "Octubre";

		} else if (mesNumerico == 11) {

			mes = "Noviembre";

		} else {

			mes = "Diciembre";

		}

		return mes;
	}
}
