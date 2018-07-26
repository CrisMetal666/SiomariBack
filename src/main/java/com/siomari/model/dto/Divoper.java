package com.siomari.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.siomari.model.Canal;
import com.siomari.model.Predio;

/**
 * Consulta general de canales, predios y obras de las secciones, zonas, unidad
 * y distrito
 * 
 * @author crismetal
 *
 */
public class Divoper {

	private int totalObras;
	private double totalAreaServida;
	private List<Canal> lstCanal;
	private List<Predio> lstPredio;
	private List<String> lstDivision;
	
	public Divoper() {
		this.lstCanal = new ArrayList<>();
		this.lstPredio = new ArrayList<>();
		this.lstDivision = new ArrayList<>();
	}

	public int getTotalCanales() {
		return lstCanal.size();
	}

	public int getTotalPredios() {
		return lstPredio.size();
	}

	public int getTotalObras() {
		return totalObras;
	}

	public void setTotalObras(int totalObras) {
		this.totalObras = totalObras;
	}

	public int getTotalDivision() {
		return lstDivision.size();
	}

	public double getTotalAreaServida() {
		return totalAreaServida;
	}

	public void setTotalAreaServida(double totalAreaServida) {
		this.totalAreaServida = totalAreaServida;
	}

	public List<Canal> getLstCanal() {
		return lstCanal;
	}

	public void setLstCanal(List<Canal> lstCanal) {
		this.lstCanal = lstCanal;
	}

	public List<Predio> getLstPredio() {
		return lstPredio;
	}

	public void setLstPredio(List<Predio> lstPredio) {
		this.lstPredio = lstPredio;
	}

	public List<String> getLstDivision() {
		return lstDivision;
	}

	public void setLstDivision(List<String> lstDivision) {
		this.lstDivision = lstDivision;
	}

}
