package com.siomari.model.dto;

import java.util.List;

import com.siomari.model.Canal;
import com.siomari.model.EstructuraControl;
import com.siomari.model.Predio;

/**
 * guardara la informacion del canal, con los predios que sirve, usuarios, total
 * de predios que sirve, obras, estructuras de control
 * 
 * @author crismetal
 *
 */
public class ConsultaCanal {

	private int id;
	private String codigo;
	private String nombre;
	private double caudalDisenio;
	private double longitud;
	private String seccionTipica;
	private String clase;
	private String tipo;
	private String categoria;
	private String estado;
	private String estadoDescripcion;
	private String canalServidor;
	private int sumPredios;
	private double areaServida;
	private List<ObraDetalle> lstObraDetalle;
	private List<String> lstCanal;
	private List<Predio> lstPredio;
	private List<EstructuraControl> lstEstructuraControl;

	public ConsultaCanal() {
	}

	public ConsultaCanal(int id, String codigo, String nombre, double caudalDisenio, double longitud,
			String seccionTipica, String clase, String tipo, String categoria, String estado, String estadoDescripcion,
			Canal canalId) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.caudalDisenio = caudalDisenio;
		this.longitud = longitud;
		this.seccionTipica = seccionTipica;
		this.clase = clase;
		this.tipo = tipo;
		this.categoria = categoria;
		this.estado = estado;
		this.estadoDescripcion = estadoDescripcion;
		this.canalServidor = canalId == null ? "" : canalId.getNombre() + " - " + canalId.getCodigo();
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

	public String getCanalServidor() {
		return canalServidor;
	}

	public void setCanalServidor(String canalServidor) {
		this.canalServidor = canalServidor;
	}

	public List<ObraDetalle> getLstObraDetalle() {
		return lstObraDetalle;
	}

	public void setLstObraDetalle(List<ObraDetalle> lstObraDetalle) {
		this.lstObraDetalle = lstObraDetalle;
	}

	public List<String> getLstCanal() {
		return lstCanal;
	}

	public void setLstCanal(List<String> lstCanal) {
		this.lstCanal = lstCanal;
	}

	public List<Predio> getLstPredio() {
		return lstPredio;
	}

	public void setLstPredio(List<Predio> lstPredio) {
		this.lstPredio = lstPredio;
	}

	public List<EstructuraControl> getLstEstructuraControl() {
		return lstEstructuraControl;
	}

	public void setLstEstructuraControl(List<EstructuraControl> lstEstructuraControl) {
		this.lstEstructuraControl = lstEstructuraControl;
	}

	public int getSumPredios() {
		return sumPredios;
	}

	public void setSumPredios(int sumPredios) {
		this.sumPredios = sumPredios;
	}

	public double getAreaServida() {
		return areaServida;
	}

	public void setAreaServida(double areaServida) {
		this.areaServida = areaServida;
	}

}
