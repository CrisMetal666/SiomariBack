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
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre) from Predio p where p.canalId.id " + "= ?1")
	List<Predio> buscarIdNombreCondigoPorCanalId(int id);

	/**
	 * se buscara el nombre del predio por id
	 * 
	 * @param id
	 *            id del predio
	 * @return nombre del predio
	 */
	@Query("select p.nombre from Predio p where p.id = ?1")
	String buscarNombrePorId(int id);

	/**
	 * se obtendra toda el area de riego de un canal
	 * 
	 * @param id
	 *            id del canal
	 * @return area de riego
	 */
	@Query("select sum(p.areaPotencialRiego) from Predio p where p.canalId.id = ?1")
	Double sumAreaPotencialPorCanalId(int id);

	/**
	 * se buscaran los predios de un usuario
	 * 
	 * @param id
	 *            id del usuario
	 * @return nombre, codigo, propietario
	 */
	@Query("select new com.siomari.model.Predio(p.codigo,p.nombre,p.propietario) from Predio p where "
			+ "p.usuarioId.id = ?1")
	List<Predio> buscarNombreCodigoPropietarioPorUsuarioId(int id);
	
	/**
	 * se buscara los predios pertenecientes a una seccion
	 * 
	 * @param id.
	 *            Id de la seccion
	 * @return lista de predios con solo su nombre, id y codigo
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre) from Predio p inner join p.canalId c inner join "
			+ "c.lstSeccionCanal sc where sc.seccionId.id = ?1")
	List<Predio> buscarPorSeccionId(int id);
	
	/**
	 * se buscara los predios pertenecientes a una zona
	 * 
	 * @param id
	 *            id de la zona
	 * @return lista de predios con solo su nombre, id y codigo
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre) from Predio p inner join p.canalId c inner join "
			+ "c.lstSeccionCanal sc where sc.seccionId.zonaId.id = ?1")
	List<Predio> buscarPorZonaId(int id);
	
	/**
	 * se buscara los predios pertenecientes a una unidad
	 * 
	 * @param id
	 *            id de la unidad
	 * @return lista de predios con solo su nombre, id y codigo
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre) from Predio p inner join p.canalId c inner join "
			+ "c.lstSeccionCanal sc where sc.seccionId.zonaId.unidadId.id = ?1")
	List<Predio> buscarPorUnidadId(int id);
	
	/**
	 * se buscara todos los predios
	 * 
	 * @return lista de predios con solo su nombre, id y codigo
	 */
	@Query("select new com.siomari.model.Predio(p.id,p.codigo,p.nombre) from Predio p")
	List<Predio> buscarPorDistrito();
	
	/**
	 * se obtendra toda el area de riego de una seccion
	 * 
	 * @param id
	 *            id de la seccion
	 * @return area de riego
	 */
	@Query("select sum(p.areaPotencialRiego) from Predio p inner join p.canalId c inner join c.lstSeccionCanal "
			+ "sc where sc.seccionId.id = ?1")
	Double sumAreaPotencialPorSeccionId(int id);
	
	/**
	 * se obtendra toda el area de riego de una zona
	 * 
	 * @param id
	 *            id de la zona
	 * @return area de riego
	 */
	@Query("select sum(p.areaPotencialRiego) from Predio p inner join p.canalId c inner join c.lstSeccionCanal "
			+ "sc where sc.seccionId.zonaId.id = ?1")
	Double sumAreaPotencialPorZonaId(int id);
	
	/**
	 * se obtendra toda el area de riego de una unidad
	 * 
	 * @param id
	 *            id de la unidad
	 * @return area de riego
	 */
	@Query("select sum(p.areaPotencialRiego) from Predio p inner join p.canalId c inner join c.lstSeccionCanal "
			+ "sc where sc.seccionId.zonaId.unidadId.id = ?1")
	Double sumAreaPotencialPorUnidadId(int id);
	
	/**
	 * se obtendra toda el area de riego de una seccion
	 * 
	 * @param id
	 *            id de la seccion
	 * @return area de riego
	 */
	@Query("select sum(p.areaPotencialRiego) from Predio p")
	Double sumAreaPotencialPorDistrito();
}
