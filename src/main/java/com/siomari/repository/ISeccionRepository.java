package com.siomari.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Seccion;

/**
 * 
 * @author crismetal
 *
 */
public interface ISeccionRepository extends JpaRepository<Seccion, Integer> {

	/**
	 * se buscara las secciones que pertenecen a una zona
	 * 
	 * @param id.
	 *            Id de la zona
	 * @return lista de secciones con solo su nombre e id
	 */
	@Query("select new com.siomari.model.Seccion(s.id, s.nombre) from Seccion s where s.zonaId.id = ?1")
	List<Seccion> buscarPorZonaId(int id);

	/**
	 * se buscara una seccion por su nombre e id de la zona
	 * 
	 * @param nombre.
	 *            Nombre de la seccion
	 * @param zona.
	 *            Id de la zona
	 * @return id de la seccion
	 */
	@Query("select s.id from Seccion s where s.nombre = ?1 and s.zonaId.id = ?2")
	Integer buscarIdPorNombreYZona(String nombre, int zona);

	/**
	 * se buscara el canal servidor de un registro por su id
	 * 
	 * @param id
	 * @return canal servidor
	 */
	@Query("select s.canalServidor from Seccion s where s.id = ?1")
	Integer buscarCanalServidorPorId(int id);

	/**
	 * se buscaran todos los canales servidores de las secciones de la zona
	 * especificada
	 * 
	 * @param id
	 *            id de la zona
	 * @return lista de id de los canales servidores de las zonas
	 */
	@Query("select s.canalServidor from Seccion s where s.zonaId.id = ?1")
	List<Integer> buscarCanalServidor(int id);

	/**
	 * se actualizara el canal servidor de la seccion
	 * 
	 * @param id
	 *            id de la seccion
	 * @param canalServidor
	 *            id del canal servidor
	 */
	@Transactional
	@Modifying
	@Query("update Seccion s set s.canalServidor = ?2 where s.id = ?1")
	void updateCanalServidor(int id, int canalServidor);

	/**
	 * se buscara una seccion que pertenescan a una zona
	 * 
	 * @param id
	 *            Id de la zona
	 * @return nombre de la seccion
	 */
	@Query("select s.nombre from Seccion s where s.zonaId.id = ?1")
	List<String> buscarNombrePorZonaId(int id);
}
