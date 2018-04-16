package com.siomari.SiomariBack;

import java.time.Month;
import java.time.Year;

public class TestWithMain {

	public static void main(String[] args) {
		
		Month mes = Month.of(2);
		Year year = Year.of(2018);
		
		System.out.println(mes.length(year.isLeap()));
		System.out.println(year.isLeap());
	}

}

/*
 * 
 * FormatoFechas ff = new FormatoFechas();
 * 
 * String data =
 * "0.0;35.0;50.0;0.0;1.0;4.0;21.0;39.0;29.0;34.0;61.0;61.0;44.0;26.0;52.0;57.0;32.0;20.0;8.0;10.0;10.0;2.0;14.0;10.0;9.0;29.0;13.0;9.0;126.0;33.0;58.0;5.0;52.0;8.0;77.0;19.0";
 * 
 * int mes = 1;
 * 
 * String json = "{\"year\": 2007,";
 * 
 * String valores[] = data.split(";");
 * 
 * for (int i = 0; i < valores.length; i += 3) {
 * 
 * String txtMes = ff.mesNumericoAMesTexto(mes).toLowerCase();
 * 
 * mes++;
 * 
 * json += "\"" + txtMes + "\": {" + "		\"decada1\": {" +
 * "			\"evaporacion\": 170," + "			\"precipitacion\": " +
 * valores[i] + "," + "			\"qPrecipitacion\": 0.8" + "		}," +
 * "		\"decada2\": {" + "			\"evaporacion\": 170," +
 * "			\"precipitacion\": " + valores[i+1] + "," +
 * "			\"qPrecipitacion\": 0.8" + "		}," + "		\"decada3\": {"
 * + "			\"evaporacion\": 170," + "			\"precipitacion\": " +
 * valores[i+2] + "," + "			\"qPrecipitacion\": 0.8" + "		} },";
 * 
 * }
 * 
 * System.out.println(json);
 * 
 */