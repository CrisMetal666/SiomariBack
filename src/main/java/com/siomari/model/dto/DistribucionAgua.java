package com.siomari.model.dto;

/**
 * 
 * @author crismetal
 *
 */
public class DistribucionAgua {

	private String cultivo;
	private double superficieMes;
	private double superficieFisica;
	private double caudalServidoMes;
	private double caudalServidoAcumulado;
	private double caudalDerivadoMes;
	private double caudalDerivadoAcumulado;
	private double laminaNetaMes;
	private double laminaNetaAcumulado;
	private double laminaBrutaMes;
	private double laminaBrutaAcumulado;
	private double eficienciaMes;
	private double eficienciaAcumulado;

	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	public double getSuperficieMes() {
		return aproximar(superficieMes);
	}

	public void setSuperficieMes(double superficieMes) {
		this.superficieMes = superficieMes;
	}
	
	

	public double getSuperficieFisica() {
		return aproximar(superficieFisica);
	}

	public void setSuperficieFisica(double superficieFisica) {
		this.superficieFisica = superficieFisica;
	}

	public double getCaudalServidoMes() {
		return aproximar(caudalServidoMes);
	}

	public void setCaudalServidoMes(double caudalServidoMes) {
		this.caudalServidoMes = caudalServidoMes;
	}

	public double getCaudalServidoAcumulado() {
		return aproximar(caudalServidoAcumulado);
	}

	public void setCaudalServidoAcumulado(double caudalServidoAcumulado) {
		this.caudalServidoAcumulado = caudalServidoAcumulado;
	}

	public double getCaudalDerivadoMes() {
		return aproximar(caudalDerivadoMes);
	}

	public void setCaudalDerivadoMes(double caudalDerivadoMes) {
		this.caudalDerivadoMes = caudalDerivadoMes;
	}

	public double getCaudalDerivadoAcumulado() {
		return aproximar(caudalDerivadoAcumulado);
	}

	public void setCaudalDerivadoAcumulado(double caudalDerivadoAcumulado) {
		this.caudalDerivadoAcumulado = caudalDerivadoAcumulado;
	}

	public double getLaminaNetaMes() {
		return aproximar(laminaNetaMes);
	}

	public void setLaminaNetaMes(double laminaNetaMes) {
		this.laminaNetaMes = laminaNetaMes;
	}

	public double getLaminaNetaAcumulado() {
		return aproximar(laminaNetaAcumulado);
	}

	public void setLaminaNetaAcumulado(double laminaNetaAcumulado) {
		this.laminaNetaAcumulado = laminaNetaAcumulado;
	}

	public double getLaminaBrutaMes() {
		return aproximar(laminaBrutaMes);
	}

	public void setLaminaBrutaMes(double laminaBrutaMes) {
		this.laminaBrutaMes = laminaBrutaMes;
	}

	public double getLaminaBrutaAcumulado() {
		return aproximar(laminaBrutaAcumulado);
	}

	public void setLaminaBrutaAcumulado(double laminaBrutaAcumulado) {
		this.laminaBrutaAcumulado = laminaBrutaAcumulado;
	}

	public double getEficienciaMes() {
		return aproximar(eficienciaMes);
	}

	public void setEficienciaMes(double eficienciaMes) {
		this.eficienciaMes = eficienciaMes;
	}

	public double getEficienciaAcumulado() {
		return aproximar(eficienciaAcumulado);
	}

	public void setEficienciaAcumulado(double eficienciaAcumulado) {
		this.eficienciaAcumulado = eficienciaAcumulado;
	}

	// aproximara a 3 decimales
	private double aproximar(double n) {
		return (double) Math.round(n * 1000) / 1000d;
	}

}
