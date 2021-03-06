package com.siomari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "cultivo_predio", catalog = "siomari_db")
public class CultivoPredio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "hectareas", nullable = false)
	private float hectareas;

	@ManyToOne
	@JoinColumn(name = "predio_id", nullable = false)
	private Predio predioId;

	@ManyToOne
	@JoinColumn(name = "cultivo_id", nullable = false)
	private Cultivo cultivoId;

	@ManyToOne
	@JoinColumn(name = "plan_siembra_id", nullable = false)
	private PlanSiembra planSiembraId;

	/**
	 * Se usara cuando se desea eliminar mas de un modelo
	 */
	@Transient
	private Integer idsEliminar[];

	public CultivoPredio() {
	}

	public CultivoPredio(int id) {
		this.id = id;
	}

	public CultivoPredio(int id, float hectareas, PlanSiembra planSiembra) {
		this.id = id;
		this.hectareas = hectareas;
		this.planSiembraId = planSiembra;
	}

	public CultivoPredio(int id, float hectareas, Cultivo cultivo, int planSiembra) {
		this.id = id;
		this.hectareas = hectareas;
		this.cultivoId = cultivo;
		this.planSiembraId = new PlanSiembra(planSiembra);
	}

	public CultivoPredio(double hectareas, PlanSiembra planSiembra) {
		this.hectareas = (float) hectareas;
		this.planSiembraId = planSiembra;
	}

	public CultivoPredio(float hectareas, double moduloRiego, int idCultivo, String nombreCultivo, int mesesGestacion,
			short mesPlansiembra) {

		this.hectareas = hectareas;

		Predio predio = new Predio();
		predio.setModuloRiego(moduloRiego);
		this.predioId = predio;

		Cultivo cultivo = new Cultivo(id, nombreCultivo);
		cultivo.setMeses(mesesGestacion);
		cultivo.setNombre(nombreCultivo);
		this.cultivoId = cultivo;

		PlanSiembra planSiembra = new PlanSiembra();
		planSiembra.setMes(mesPlansiembra);
		this.planSiembraId = planSiembra;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getHectareas() {
		return hectareas;
	}

	public void setHectareas(float hectareas) {
		this.hectareas = hectareas;
	}

	public Predio getPredioId() {
		return predioId;
	}

	public void setPredioId(Predio predioId) {
		this.predioId = predioId;
	}

	public Cultivo getCultivoId() {
		return cultivoId;
	}

	public void setCultivoId(Cultivo cultivoId) {
		this.cultivoId = cultivoId;
	}

	public PlanSiembra getPlanSiembraId() {
		return planSiembraId;
	}

	public void setPlanSiembraId(PlanSiembra planSiembraId) {
		this.planSiembraId = planSiembraId;
	}

	public Integer[] getIdsEliminar() {
		return idsEliminar;
	}

	public void setIdsEliminar(Integer[] idsEliminar) {
		this.idsEliminar = idsEliminar;
	}

}
