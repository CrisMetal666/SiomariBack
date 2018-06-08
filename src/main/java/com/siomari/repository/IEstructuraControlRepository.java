package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.EstructuraControl;

/**
 * 
 * @author crismetal
 *
 */
public interface IEstructuraControlRepository extends JpaRepository<EstructuraControl, Integer> {

	/**
	 * se listara por codigo
	 * 
	 * @param codigo
	 * @return todos los datos
	 */
	@Query("select new com.siomari.model.EstructuraControl(ec.id,ec.codigo,ec.coeficiente,ec.exponente,"
			+ "ec.canalId.id) from EstructuraControl ec where ec.codigo = ?1")
	EstructuraControl listarPorCodigo(String codigo);

	/**
	 * se buscaran las estructuras de control que concidan el codifgo con la cadena
	 * 
	 * @param codigo
	 *            cadena
	 * @return id, codigo de las estructuras
	 */
	@Query("select new com.siomari.model.EstructuraControl(ec.id,ec.codigo,ec.canalId.id) from "
			+ "EstructuraControl ec where ec.codigo like concat('%',?1,'%')")
	List<EstructuraControl> buscarPorCodigo(String codigo);

	/**
	 * se buscaran las estructuras de control que concidan el codifgo con la cadena
	 * 
	 * @param codigo
	 * @return codigo,coeficiente,exponenete
	 */
	@Query("select new com.siomari.model.EstructuraControl(ec.codigo,ec.coeficiente,ec.exponente) from "
			+ "EstructuraControl ec where ec.codigo like concat('%',?1,'%')")
	List<EstructuraControl> buscarCodigoCoeficienteExponentePorCodigo(String codigo);

	/**
	 * se buscara una estructura de control por su codigo
	 * 
	 * @param codigo
	 * @return id de la estructura
	 */
	@Query("select new com.siomari.model.EstructuraControl(ec.id) from EstructuraControl ec " + "where ec.codigo = ?1")
	EstructuraControl buscarIdPorCodigo(String codigo);
}
