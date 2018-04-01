package com.siomari.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Este modelo sirve para mostrar la informacion de planeacion mejor estructurada, es ella se muestra el mes 
 * de la planeacion (lo convierte de valor numerico a texto), las hectareas de los tres periodos, la suma de 
 * estos 3 y el cultivo, tambien se usa para mostrar la demanda de agua
 * 
 * 
 * @author crismetal
 *
 */
public class PlaneacionInfo {
	
	//guardamos el valor numerico del mes que nos llega de la base de datos para despues convertirla a texto
	@JsonIgnore
	private int mesNumerico;
	//mes que que mostrara en el json
	private String mes;
	private float periodo1;
	private float periodo2;
	private float periodo3;
	private float total;
	private String cultivo;
	//se guardara el nombre del mes y su respectiva demanda de agua
	private List<Map<String,Object>> demanda;
	
	public PlaneacionInfo() {
	}

	public PlaneacionInfo(short mesNumerico, float periodo1, float periodo2, float periodo3) {
		this.mesNumerico = mesNumerico;
		this.periodo1 = periodo1;
		this.periodo2 = periodo2;
		this.periodo3 = periodo3;
	}
	
	public PlaneacionInfo(short mesNumerico, float periodos[]) {
		this.mesNumerico = mesNumerico;
		this.periodo1 = periodos[0];
		this.periodo2 = periodos[1];
		this.periodo3 = periodos[2];
	}

	public String getMes() {
		
		//Convertimos el mes numerico a text		
		if(mesNumerico == 1) {
			
			mes = "Enero";
			
		} else if(mesNumerico == 2) {
			
			mes = "Febrero";
			
		} else if(mesNumerico == 3) {
			
			mes = "Marzo";
			
		} else if(mesNumerico == 4) {
			
			mes = "Abril";
			
		} else if(mesNumerico == 5) {
			
			mes = "Mayo";
			
		} else if(mesNumerico == 6) {
			
			mes = "Junio";
			
		} else if(mesNumerico == 7) {
			
			mes = "Julio";
			
		} else if(mesNumerico == 8) {
			
			mes = "Agosto";
			
		} else if(mesNumerico == 9) {
			
			mes = "Septiembre";
			
		} else if(mesNumerico == 10) {
			
			mes = "Octubre";
			
		} else if(mesNumerico == 11) {
			
			mes = "Noviembre";
			
		} else {
			
			mes = "Diciembre";
			
		}
		
		return mes;
		
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public float getPeriodo1() {
		return periodo1;
	}

	public void setPeriodo1(float periodo1) {
		this.periodo1 = periodo1;
	}

	public float getPeriodo2() {
		return periodo2;
	}

	public void setPeriodo2(float periodo2) {
		this.periodo2 = periodo2;
	}

	public float getPeriodo3() {
		return periodo3;
	}

	public void setPeriodo3(float periodo3) {
		this.periodo3 = periodo3;
	}

	public float getTotal() {
		
		total = periodo1 + periodo2 + periodo3;
		
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	public int getMesNumerico() {
		return mesNumerico;
	}

	public void setMesNumerico(int mesNumerico) {
		this.mesNumerico = mesNumerico;
	}

	public List<Map<String, Object>> getDemanda() {
		return demanda;
	}

	public void setDemanda(List<Map<String, Object>> demanda) {
		this.demanda = demanda;
	}

	

	

	
	
	
	

}
