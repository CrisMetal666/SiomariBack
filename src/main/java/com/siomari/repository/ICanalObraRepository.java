package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.CanalObra;

/**
 * 
 * @author crismetal
 *
 */
public interface ICanalObraRepository extends JpaRepository<CanalObra, Integer> {

	/**
	 * se buscaran las obras que pertenecen a un canal
	 * 
	 * @param id
	 *            id del canal
	 * @return id, descripcion, coordenadas de georrferenciacion y obra
	 */
	@Query("select new com.siomari.model.CanalObra(co.id,co.descripcion,co.ultimaIntervension,co.observacion,co.imagen,co.x,co.y,"
			+ "co.altitud,co.canalId.id,co.obraId) from CanalObra co where co.canalId.id = ?1")
	List<CanalObra> buscarPorCanalId(int id);

	/**
	 * se buscaran las obras que pertenecen a un canal y obra
	 * 
	 * @param canal
	 *            id del canal
	 * @param obra
	 *            id de la obra
	 * @return id, descripcion, coordenadas de georrferenciacion y obra
	 */
	@Query("select new com.siomari.model.CanalObra(co.id,co.descripcion,co.ultimaIntervension,co.observacion,co.imagen,co.x,"
			+ "co.y,co.altitud,co.canalId.id,co.obraId) from CanalObra co where co.canalId.id = ?1 and "
			+ "co.obraId.id = ?2")
	List<CanalObra> buscarPorCanalIdYObraId(int canal, int obra);

	/**
	 * se buscara el id y el nombre de la obra por el id del canal
	 * 
	 * @param id
	 *            id del canal
	 * @return
	 */
	@Query("select new com.siomari.model.CanalObra(co.id,co.obraId) from CanalObra co where " + "co.canalId.id = ?1")
	List<CanalObra> buscarIdNombrePorCanalId(int id);

	/**
	 * se buscara toda la informacion de un canal obra
	 * 
	 * @param id
	 *            id del canal obra
	 * @return
	 */
	@Query("select new com.siomari.model.CanalObra(co.id,co.descripcion,co.ultimaIntervension,co.observacion,co.imagen,"
			+ "co.x,co.y,co.altitud,co.canalId.id,co.obraId) from CanalObra co where co.id = ?1")
	CanalObra buscarPorId(int id);

	/**
	 * se obtendra el nombre de la imagen registrada
	 * 
	 * @param id
	 *            id del canalobra
	 * @return nombre imagen
	 */
	@Query("select co.imagen from CanalObra co where co.id = ?1")
	String getImagenPorId(int id);
	
	/**
	 * se buscara las obras pertenecientes a una seccion
	 * 
	 * @param id.
	 *            Id de la seccion
	 * @return cantidad de obras
	 */
	@Query("select count(co.id) from CanalObra co inner join co.canalId c inner join c.lstSeccionCanal "
			+ "sc where sc.seccionId.id = ?1")
	Integer buscarPorSeccionId(int id);
	
	/**
	 * se buscara las obras pertenecientes a una zona
	 * 
	 * @param id.
	 *            Id de la zona
	 * @return cantidad de obras
	 */
	@Query("select count(co.id) from CanalObra co inner join co.canalId c inner join c.lstSeccionCanal "
			+ "sc where sc.seccionId.zonaId.id = ?1")
	Integer buscarPorZonaId(int id);
	
	/**
	 * se buscara las obras pertenecientes a una unidad
	 * 
	 * @param id.
	 *            Id de la unidad
	 * @return cantidad de obras
	 */
	@Query("select count(co.id) from CanalObra co inner join co.canalId c inner join c.lstSeccionCanal "
			+ "sc where sc.seccionId.zonaId.unidadId.id = ?1")
	Integer buscarPorUnidadId(int id);
	
	/**
	 * se buscara las obras pertenecientes del distrito
	 * 
	 * 
	 * @return cantidad de obras
	 */
	@Query("select count(co.id) from CanalObra co ")
	Integer buscarPorDistrito();
}
