package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Obra;

/**
 * 
 * @author crismetal
 *
 */
public interface IObraRepository extends JpaRepository<Obra, Integer> {

	/**
	 * Se buscara una obra por su nombre
	 * 
	 * @param nombre
	 * @return id de la obra
	 */
	@Query("select o.id from Obra o where o.nombre = ?1")
	Integer buscarIdPorNombre(String nombre);

	/**
	 * Se buscara las obras que en su nombre contengan la cadena enviada
	 * 
	 * @param query
	 *            cadena para comparar con los nombres
	 * @return nombre y id de las obras
	 */
	@Query("select new com.siomari.model.Obra(o.id,o.nombre) from Obra o where o.nombre like concat('%',?1,'%')")
	List<Obra> buscarPorNombre(String query);

	/**
	 * buscara las obras que tiene un canal
	 * 
	 * @param id
	 *            id del canal
	 * @return
	 */
	@Query("select new com.siomari.model.Obra(o.id,o.nombre) from Obra o inner join o.lstCanalObra co "
			+ "where co.canalId.id = ?1 group by o.id")
	List<Obra> buscarIdNombrePorCanalId(int id);
}
