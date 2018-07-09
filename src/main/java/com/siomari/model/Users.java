package com.siomari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author crismetal
 *
 */
@Table(name = "users", catalog = "siomari_db")
@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "identificacion", length = 45, unique = true, nullable = false)
	private String identificacion;

	@Column(name = "clave", length = 60, nullable = false, updatable = false)
	private String clave;

	@Column(name = "rol", length = 200, nullable = false)
	private String rol;

	@Column(name = "nuevo", nullable = false)
	private boolean nuevo;

	@Column(name = "estado", nullable = false)
	private boolean estado;
	
	public Users() {
		
	}

	public Users(int id, String identificacion) {
		this.id = id;
		this.identificacion = identificacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
