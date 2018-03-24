package com.siomari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author crismetal
 *
 */
@Entity
@Table(name = "predio", catalog = "siomari_db")
public class Predio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo", length = 100, nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "nombre", length = 200, nullable = false)
	private String nombre;
	
	@Column(name = "nombre_propietario", length = 100, nullable = false)
	private String nombrePropietario;
	
	@Column(name = "area_total", nullable = false)
	private double areaTotal;
	
	@Column(name = "area_potencial_riego", nullable = false)
	private double areaPotencialRiego;
	
	@Column(name = "area_bajo_riego", nullable = false)
	private double areaBajoRiego;
	
	@Column(name = "modulo_riego", nullable = false)
	private double moduloRiego;
	
	@Column(name = "numero_tomas", nullable = false)
	private int numeroTomas;
	
	@Column(name = "tipo_suelo", length = 100, nullable = false)
	private String tipoSuelo;
	
	@Column(name = "x")
	private Double x;
	
	@Column(name = "y")
	private Double y;
	
	@Column(name = "altitud")
	private Double altitud;
	
	@ManyToOne
	@JoinColumn(name = "canal_id", nullable = false)
	private Canal canalId;

	public Predio() {
	}
	
	public Predio(int id) {
		this.id = id;
	}
	
	public Predio(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Predio(int id, String codigo, String nombre) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public Predio(int id, String codigo, String nombre, double areaTotal) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.areaTotal = areaTotal;
	}
	
	public Predio(int id, String codigo, String nombre, String nombrePropietario, double areaTotal,
			double areaPotencialRiego, double areaBajoRiego, double moduloRiego, int numero_tomas, String tipoSuelo) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.nombrePropietario = nombrePropietario;
		this.areaTotal = areaTotal;
		this.areaPotencialRiego = areaPotencialRiego;
		this.areaBajoRiego = areaBajoRiego;
		this.moduloRiego = moduloRiego;
		this.numeroTomas = numero_tomas;
		this.tipoSuelo = tipoSuelo;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public double getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(double areaTotal) {
		this.areaTotal = areaTotal;
	}

	public double getAreaPotencialRiego() {
		return areaPotencialRiego;
	}

	public void setAreaPotencialRiego(double areaPotencialRiego) {
		this.areaPotencialRiego = areaPotencialRiego;
	}

	public double getAreaBajoRiego() {
		return areaBajoRiego;
	}

	public void setAreaBajoRiego(double areaBajoRiego) {
		this.areaBajoRiego = areaBajoRiego;
	}

	public double getModuloRiego() {
		return moduloRiego;
	}

	public void setModuloRiego(double moduloRiego) {
		this.moduloRiego = moduloRiego;
	}

	public String getTipoSuelo() {
		return tipoSuelo;
	}

	public void setTipoSuelo(String tipoSuelo) {
		this.tipoSuelo = tipoSuelo;
	}

	public Canal getCanalId() {
		return canalId;
	}

	public void setCanalId(Canal canalId) {
		this.canalId = canalId;
	}

	public int getNumeroTomas() {
		return numeroTomas;
	}

	public void setNumeroTomas(int numeroTomas) {
		this.numeroTomas = numeroTomas;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getAltitud() {
		return altitud;
	}

	public void setAltitud(Double altitud) {
		this.altitud = altitud;
	}



	
	

}
