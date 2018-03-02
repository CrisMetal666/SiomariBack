package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Predio;

/**
 * 
 * @author crismetal
 *
 */
public interface IPredioRepository extends JpaRepository<Predio, Integer> {

	/**
	 * Se buscara el id del predio por su codigo
	 * @param codigo
	 * @return id del predio
	 */
	@Query("select p.id from Predio p where p.codigo = ?1")
	Integer buscarIdPorCodigo(String codigo);
	
	/**
	 * Se buscara los predios que no tengan un usuario registrado
	 * @return lista de predios
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre) from Predio p where p.id not in "
			+ "(select u.predioId.id from Usuario u)")
	List<Predio> buscarSinUsuario();
}
