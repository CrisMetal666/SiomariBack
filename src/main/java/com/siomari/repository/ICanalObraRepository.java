package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.CanalObra;

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

}
