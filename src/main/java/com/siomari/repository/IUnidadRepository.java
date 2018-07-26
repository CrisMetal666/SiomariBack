package com.siomari.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Unidad;

/**
 * 
 * @author crismetal
 *
 */
public interface IUnidadRepository extends JpaRepository<Unidad, Integer> {

	/**
	 * Se buscara una unidad por su nombre
	 * 
	 * @param nombre
	 * @return id de la unidad
	 */
	@Query("select u.id from Unidad u where u.nombre = ?1")
	Integer buscarIdPorNombre(String nombre);

	/**
	 * se buscara el canal servidor de un registro por su id
	 * 
	 * @param id
	 * @return canal servidor
	 */
	@Query("select u.canalServidor from Unidad u where u.id = ?1")
	Integer buscarCanalServidorPorId(int id);

	/**
	 * se actualizara el canal servidor de la unidad
	 * 
	 * @param id
	 *            id de la unidad
	 * @param canalServidor
	 *            id del canal servidor
	 */
	@Transactional
	@Modifying
	@Query("update Unidad u set u.canalServidor = ?2 where u.id = ?1")
	void updateCanalServidor(int id, int canalServidor);
	
	/**
	 * se buscaran las unidades del distrito
	 * @return nombre la unidad
	 */
	@Query("select u.nombre from Unidad u")
	List<String> listarNombre();
}
