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
	 * 
	 * @param codigo
	 * @return id del predio
	 */
	@Query("select p.id from Predio p where p.codigo = ?1")
	Integer buscarIdPorCodigo(String codigo);

	/**
	 * Se buscara los predios que no tengan un usuario registrado
	 * 
	 * @return lista de predios
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre) from Predio p where p.id not in "
			+ "(select u.predioId.id from Usuario u)")
	List<Predio> buscarSinUsuario();

	/**
	 * Se listaran los predios que contangan la cadena enviana en su nombre o codigo
	 * y no tenga un usuario registrado
	 * 
	 * @param query
	 *            cadena de consulta, la cadena debe iniciar y terminar con %
	 * @return lista de predios, solo con nombre, codigo, areaTotal y id
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre) from Predio p where p.id not in "
			+ "(select u.predioId.id from Usuario u where u.predioId.id > 0) and (p.nombre like %?1% or p.codigo "
			+ "like %?1%) group by p.id ")
	List<Predio> listarIdCodigoNombrePorNombreOCodigoSinUsuarios(String query);

	/**
	 * Se listaran todos los predios
	 * 
	 * @return predios solo con el id, codigo, nombre y area total
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre,p.areaTotal) from Predio p")
	List<Predio> listarDatosBasicos();

	/**
	 * Se listaran los predios que contangan la cadena enviana en su nombre o codigo
	 * 
	 * @param query
	 *            cadena de consulta, la cadena debe iniciar y terminar con %
	 * @return lista de predios, solo con nombre, codigo, areaTotal y id
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre,p.areaTotal) from Predio p where "
			+ "p.nombre like ?1 or p.codigo like ?1 group by p.id")
	List<Predio> listarIdCodigoNombrePorNombreOCodigo(String query);

	/**
	 * se buscara el modulo de riego de un predio por su id
	 * 
	 * @param predio
	 *            id del predio
	 * @return modulo de riego
	 */
	@Query("select p.moduloRiego from Predio p where p.id = ?1")
	Double listarModuloRiegoPorId(int predio);

	/**
	 * se buscara los predios que perteneen a un canal
	 * 
	 * @param id
	 *            id del canal
	 * @return id, nombre, codigo
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre) from Predio p where p.canalId.id "
			+ "= ?1")
	List<Predio> buscarIdNombreCondigoPorCanalId(int id);

}
