package com.siomari.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@OneToMany(mappedBy = "usuarioId", fetch = FetchType.LAZY)
	List<Predio> lstPredio;
	
	@Transient
	private String nombreCompleto;

	public Usuario() {
	}

	public Usuario(int id) {
		this.id = id;
	}

	public Usuario(int id, String nombre, String apellido, String cedula) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Usuario(int id, String cedula, String nombre, String apellido, String direccion, String ciudad,
			String telefono, String celular, String correo) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.celular = celular;
		this.correo = correo;
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

	public String getNombreCompleto() {
		return nombre + " " + apellido;
	}

	public List<Predio> getLstPredio() {
		return lstPredio;
	}

	public void setLstPredio(List<Predio> lstPredio) {
		this.lstPredio = lstPredio;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	
}
