package com.siomari.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author crismetal
 *
 */
@Table(name = "canal", catalog = "siomari_db")
@Entity
public class Canal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo", length = 100, nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;
	
	@Column(name = "caudal_disenio", nullable = false)
	private double caudalDisenio;
	
	@Column(name = "longitud", length = 100, nullable = false)
	private double longitud;
	
	@Column(name = "seccion_tipica", length = 100, nullable = false)
	private String seccionTipica;
	
	@Column(name = "clase", length = 100, nullable = false)
	private String clase;
	
	@Column(name = "tipo", length = 100, nullable = false)
	private String tipo;
	
	@Column(name = "categoria", length = 100, nullable = false)
	private String categoria;
	
	@Column(name = "estado", length = 100, nullable = false)
	private String estado;
	
	@Column(name = "estado_descripcion", columnDefinition = "text")
	private String estadoDescripcion;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "canal_id", nullable = false)
	private Canal canalId;
	
	@OneToMany(mappedBy = "canalId", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<CanalObra> lstCanalObra;
	
	@OneToMany(mappedBy = "canalId", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<SeccionCanal> lstSeccionCanal;
	
	@OneToMany(mappedBy = "canalId", fetch = FetchType.LAZY)
	private List<Canal> lstCanal;
	
	@OneToMany(mappedBy = "canalId", fetch = FetchType.LAZY)
	private List<Predio> lstPredio;

	public Canal() {
	}
	
	public Canal(int id) {
		this.id = id;
	}
	
	public Canal(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
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

	public double getCaudalDisenio() {
		return caudalDisenio;
	}

	public void setCaudalDisenio(double caudalDisenio) {
		this.caudalDisenio = caudalDisenio;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getSeccionTipica() {
		return seccionTipica;
	}

	public void setSeccionTipica(String seccionTipica) {
		this.seccionTipica = seccionTipica;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoDescripcion() {
		return estadoDescripcion;
	}

	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
	}

	public Canal getCanalId() {
		return canalId;
	}

	public void setCanalId(Canal canalId) {
		this.canalId = canalId;
	}

	public List<CanalObra> getLstCanalObra() {
		return lstCanalObra;
	}

	public void setLstCanalObra(List<CanalObra> lstCanalObra) {
		this.lstCanalObra = lstCanalObra;
	}

	public List<SeccionCanal> getLstSeccionCanal() {
		return lstSeccionCanal;
	}

	public void setLstSeccionCanal(List<SeccionCanal> lstSeccionCanal) {
		this.lstSeccionCanal = lstSeccionCanal;
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

	
	
}
