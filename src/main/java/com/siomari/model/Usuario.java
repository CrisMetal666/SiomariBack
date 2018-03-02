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
@Table(name = "usuario", catalog = "siomari_db")
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "cedula", length = 45, nullable = false, unique = true)
	private String cedula;
	
	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;
	
	@Column(name = "apellidos", length = 100, nullable = false)
	private String apellido;
	
	@Column(name = "direccion", length = 100, nullable = false)
	private String direccion;
	
	@Column(name = "ciudad", length = 100, nullable = false)
	private String ciudad;
	
	@Column(name = "telefono", length = 20, nullable = true)
	private String telefono;
	
	@Column(name = "celular", length = 20, nullable = true)
	private String celular;
	
	@Column(name = "correo", length = 200, nullable = true)
	private String correo;
	
	@Column(name = "propietario", length = 200, nullable = true)
	private boolean propietario;
	
	@ManyToOne
	@JoinColumn(name = "predio_id", nullable = true)
	private Predio predioId;
	
	public Usuario() {
	}

	public Usuario(int id) {
		this.id = id;
	}
	
	public Usuario(int id, String cedula, String nombre, String apellido, String direccion, String ciudad,
			String telefono, String celular, String correo, boolean propietario) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.celular = celular;
		this.correo = correo;
		this.propietario = propietario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Predio getPredioId() {
		return predioId;
	}

	public void setPredioId(Predio predioId) {
		this.predioId = predioId;
	}

	public boolean isPropietario() {
		return propietario;
	}

	public void setPropietario(boolean propietario) {
		this.propietario = propietario;
	}
	
	

}
