package com.siomari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author crismetal
 *
 */
@Entity
@Table(name = "estructura_control", catalog = "siomari_db")
public class EstructuraControl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "codigo", length = 45, nullable = false, unique = true)
	private String codigo;

	@Column(name = "coeficiente")
	private Double coeficiente;

	@Column(name = "exponente")
	private Double exponente;

	@Transient
	private double x[];

	@Transient
	private double y[];

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "canal_id", nullable = false)
	private Canal canalId;

	public EstructuraControl() {

	}

	public EstructuraControl(int id) {
		this.id = id;
	}

	public EstructuraControl(int id, String codigo) {
		this.id = id;
		this.codigo = codigo;
	}
	
	public EstructuraControl(int id, String codigo, int canalId) {
		this.id = id;
		this.codigo = codigo;
		this.canalId = new Canal(canalId);
	}
	
	public EstructuraControl(String codigo, Double coeficiente, Double exponente) {
		this.codigo = codigo;
		this.coeficiente = coeficiente;
		this.exponente = exponente;
	}

	public EstructuraControl(int id, String codigo, Double coeficiente, Double exponente, int canalId) {
		this.id = id;
		this.codigo = codigo;
		this.coeficiente = coeficiente;
		this.exponente = exponente;
		this.canalId = new Canal(canalId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Double coeficiente) {
		this.coeficiente = coeficiente;
	}

	public Double getExponente() {
		return exponente;
	}

	public void setExponente(Double exponente) {
		this.exponente = exponente;
	}

	public Canal getCanalId() {
		return canalId;
	}

	public void setCanalId(Canal canalId) {
		this.canalId = canalId;
	}

	public double[] getX() {
		return x;
	}

	public void setX(double[] x) {
		this.x = x;
	}

	public double[] getY() {
		return y;
	}

	public void setY(double[] y) {
		this.y = y;
	}

}
