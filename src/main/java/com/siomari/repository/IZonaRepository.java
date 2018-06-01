package com.siomari.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Zona;

/**
 * 
 * @author crismetal
 *
 */
public interface IZonaRepository extends JpaRepository<Zona, Integer> {

	/**
	 * se buscan las zonas que pertecen a una unidad
	 * 
	 * @param id.
	 *            Id de la unidad
	 * @return lista de zonas, con solo su nombre e id
	 */
	@Query("select new com.siomari.model.Zona(z.id, z.nombre) from Zona z where z.unidadId.id = ?1")
	List<Zona> buscarPorUnidadId(int id);

	/**
	 * se buscara una zona por su nombre e id de la unidad
	 * 
	 * @param nombre.
	 *            Nombre de la zona
	 * @param unidad.
	 *            Id de la unidad
	 * @return id de la zona
	 */
	@Query("select z.id from Zona z where z.nombre = ?1 and z.unidadId.id = ?2")
	Integer buscarIdPorNombreYUnidad(String nombre, int unidad);

	/**
	 * se buscara el canal servidor de un registro por su id
	 * 
	 * @param id
	 * @return canal servidor
	 */
	@Query("select z.canalServidor from Zona z where z.id = ?1")
	Integer buscarCanalServidorPorId(int id);

	/**
	 * se buscaran todos los canales servidores de las zonas de la unidad
	 * 
	 * @param id
	 *            id de la unidad
	 * @return lista de id de los canales servidores de las zonas
	 */
	@Query("select z.canalServidor from Zona z where z.unidadId.id = ?1")
	List<Integer> buscarCanalServidor(int id);
	
	/**
	 * se actualizara el canal servidor de la zona
	 * 
	 * @param id
	 *            id de la zona
	 * @param canalServidor
	 *            id del canal servidor
	 */
	@Transactional
	@Modifying
	@Query("update Zona z set z.canalServidor = ?2 where z.id = ?1")
	void updateCanalServidor(int id, int canalServidor);
	
	/**
	 * se buscara las zonas de la unidad
	 * @param id id de la zona
	 * @return id, canalServidor
	 */
	@Query("select new com.siomari.model.Zona(z.id,z.canalServidor) from Zona z where z.unidadId.id = ?1")
	List<Zona> buscarIdCanalServidorPorUnidadId(int id);
}
