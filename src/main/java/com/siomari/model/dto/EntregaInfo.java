package com.siomari.model.dto;

/**
 * se utiliza para armar el json de las entregas de agua, con su costo y fechas
 * 
 * @author crismetal
 *
 */
public class EntregaInfo {

	private String fecha;
	private long segundos;
	private double m3;
	private double costo;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public long getSegundos() {
		return segundos;
	}

	public void setSegundos(long segundos) {
		this.segundos = segundos;
	}

	public double getM3() {
		return m3;
	}

	public void setM3(double m3) {
		this.m3 = m3;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

}
