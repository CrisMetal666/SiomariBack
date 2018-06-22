package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Cultivo;

/**
 * 
 * @author crismetal
 *
 */
public interface ICultivoRepository extends JpaRepository<Cultivo, Integer> {

	/**
	 * Se buscara un canal por su codigo
	 * @param nombre. Nombre del cultivo
	 * @return id del cultivo
	 */
	@Query("select c.id from Cultivo c where c.nombre = ?1")
	Integer buscarIdPorNombre(String nombre);
	
	/**
	 * se listaran todos los cultivos
	 * @return se listaran solo el id, nombre de los cultivos
	 */
	@Query("select new com.siomari.model.Cultivo(c.id,c.nombre) from Cultivo c")
	List<Cultivo> listarDatosBasicos();
	
	/**
	 * Se listaran los predios que contangan la cadena enviana en su nombre
	 * @param query cadena de consulta, la cadena debe iniciar y terminar con %
	 * @return lista de cultivos, solo con nombre y id
	 */
	@Query("select new com.siomari.model.Cultivo(c.id,c.nombre) from Cultivo c where c.nombre like ?1")
	List<Cultivo> listarIdNombrePorNombre(String query);
	
	/**
	 * se consultara los meses de gestacion que sea mayor de los cultivos
	 * @return meses de gestacion
	 */
	@Query("select max(c.meses) from Cultivo c")
	int maxMes();
}
