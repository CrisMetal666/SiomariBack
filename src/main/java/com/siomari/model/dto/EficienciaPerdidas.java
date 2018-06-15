package com.siomari.model.dto;

/**
 * 
 * modelo usado para mostrar la informacion de la evaluacion, perdias y
 * eficiencias de un canal, seccion, zona o unidad
 * 
 * @author crismetal
 * 
 */
public class EficienciaPerdidas {

	private double eficienciaConduccion;
	private double eficienciaIntrinseca;
	private double eficienciaOperacion;
	private double perdidasTotales;
	private double perdidasManejo;
	private double perdidasIntrinsecas;

	public double getEficienciaConduccion() {
		return eficienciaConduccion;
	}

	public void setEficienciaConduccion(double eficienciaConduccion) {
		this.eficienciaConduccion = eficienciaConduccion;
	}

	public double getEficienciaIntrinseca() {
		return eficienciaIntrinseca;
	}

	public void setEficienciaIntrinseca(double eficienciaIntrinseca) {
		this.eficienciaIntrinseca = eficienciaIntrinseca;
	}

	public double getEficienciaOperacion() {
		return eficienciaOperacion;
	}

	public void setEficienciaOperacion(double eficienciaOperacion) {
		this.eficienciaOperacion = eficienciaOperacion;
	}

	public double getPerdidasTotales() {
		return perdidasTotales;
	}

	public void setPerdidasTotales(double perdidasTotales) {
		this.perdidasTotales = perdidasTotales;
	}

	public double getPerdidasManejo() {
		return perdidasManejo;
	}

	public void setPerdidasManejo(double perdidasManejo) {
		this.perdidasManejo = perdidasManejo;
	}

	public double getPerdidasIntrinsecas() {
		return perdidasIntrinsecas;
	}

	public void setPerdidasIntrinsecas(double perdidasIntrinsecas) {
		this.perdidasIntrinsecas = perdidasIntrinsecas;
	}

	@Override
	public String toString() {
		return "EficienciaPerdidas [eficienciaConduccion=" + eficienciaConduccion + ", eficienciaIntrinseca="
				+ eficienciaIntrinseca + ", eficienciaOperacion=" + eficienciaOperacion + ", perdidasTotales="
				+ perdidasTotales + ", perdidasManejo=" + perdidasManejo + ", perdidasIntrinsecas="
				+ perdidasIntrinsecas + "]";
	}

}
