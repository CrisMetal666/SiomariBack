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
	@Query("select new com.siomari.model.CanalObra(co.id,co.descripcion,co.x,co.y,co.altitud,co.obraId) "
			+ "from CanalObra co where co.canalId.id = ?1")
	List<CanalObra> buscarPorCanalId(int id);

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
	@Query("select new com.siomari.model.CanalObra(co.id,co.descripcion,co.ultimaIntervension,co.imagen,"
			+ "co.x,co.y,co.altitud,co.canalId.id,co.obraId) from CanalObra co where co.id = ?1")
	CanalObra buscarPorId(int id);
	
	@Query("select co.imagen from CanalObra co where co.id = ?1")
	String getImagenPorId(int id);
}
