package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
	 * @param id. Id de la unidad
	 * @return lista de zonas, con solo su nombre e id
	 */
	@Query("select new com.siomari.model.Zona(z.id, z.nombre) from Zona z where z.unidadId.id = ?1")
	List<Zona> buscarPorUnidadId(int id);
}
