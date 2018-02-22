package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Canal;

/**
 * 
 * @author crismetal
 *
 */
public interface ICanalRepository extends JpaRepository<Canal, Integer> {
	
	/**
	 * se buscara los canales pertenecientes a una seccion
	 * @param id. Id de la seccion
	 * @return lista de canales con solo su nombre e id
	 */
	@Query("select new com.siomari.model.Canal(c.id, c.nombre) from Canal c inner join c.lstSeccionCanal sc where sc.seccionId.id = ?1")
	List<Canal> buscarPorSeccionId(int id);
	
	/**
	 * Se buscara un canal por su codigo
	 * @param codigo. Codigo del canal
	 * @return id del canal
	 */
	@Query("select c.id from Canal c where c.codigo = ?1")
	Integer buscarIdPorCodigo(String codigo);
}
