package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Canal;
import com.siomari.model.dto.ConsultaCanal;

/**
 * 
 * @author crismetal
 *
 */
public interface ICanalRepository extends JpaRepository<Canal, Integer> {

	/**
	 * Se buscara un canal por su codigo
	 * 
	 * @param codigo.
	 *            Codigo del canal
	 * @return id del canal
	 */
	@Query("select c.id from Canal c where c.codigo = ?1")
	Integer buscarIdPorCodigo(String codigo);

	/**
	 * se buscara los canales por nombre o codigo
	 * 
	 * @param query
	 *            nombre o codigo
	 * @return lista de canales solo con el nombre, codigo y id
	 */
	@Query("select new com.siomari.model.Canal(c.id,c.nombre,c.codigo) from Canal c where c.nombre like "
			+ "concat('%', ?1 ,'%') or c.codigo like concat('%', ?1 ,'%') order by c.id")
	List<Canal> buscarPorNombreOCodigo(String query);

	/**
	 * se buscara los canales por nombre o codigo que no sean de distribucion o
	 * principal
	 * 
	 * @param query
	 *            nombre o codigo
	 * @return lista de canales solo con el nombre, codigo y id
	 */
	@Query("select new com.siomari.model.Canal(c.id,c.nombre,c.codigo) from Canal c where (c.nombre like "
			+ "concat('%', ?1 ,'%') or c.codigo like concat('%', ?1 ,'%')) and c.categoria not in "
			+ "('CANAL_PRINCIPAL', 'CANAL_DISTRIBUCION') ")
	List<Canal> buscarPorNombreOCodigoNoServidores(String query);

	/**
	 * se buscara los canales por nombre o codigo que sean de distribucion o
	 * principal
	 * 
	 * @param query
	 *            nombre o codigo
	 * @return lista de canales solo con el nombre, codigo y id
	 */
	@Query("select new com.siomari.model.Canal(c.id,c.nombre,c.codigo) from Canal c where (c.nombre like "
			+ "concat('%', ?1 ,'%') or c.codigo like concat('%', ?1 ,'%')) and c.categoria in "
			+ "('CANAL_PRINCIPAL', 'CANAL_DISTRIBUCION') ")
	List<Canal> buscarPorNombreOCodigoServidores(String query);

	/**
	 * Se buscara el canal por su id
	 * 
	 * @param id
	 * @return canal con datos basicos, seccionCana, canalObra, canalId
	 */
	@Query("select c from Canal c  where c.id = ?1")
	Canal buscarPorId(int id);

	/**
	 * se buscara en caudal diseño de un canal
	 * 
	 * @param canal
	 *            id del canal
	 * @return caudal diseño
	 */
	@Query("select c.caudalDisenio from Canal c where c.id = ?1")
	Double buscarCaudalDisenioPorId(int canal);

	/**
	 * se buscara el nombre del canal por su id
	 * 
	 * @param id
	 *            id del canal
	 * @return nombre del canal
	 */
	@Query("select c.nombre from Canal c where c.id = ?1")
	String buscarNombrePorId(int id);

	/**
	 * se consultara los datos basicos del canal por su id
	 * 
	 * @param id
	 *            id del canal
	 * @return consultaCanal
	 */
	@Query("select new com.siomari.model.dto.ConsultaCanal(c.id,c.codigo,c.nombre,c.caudalDisenio,"
			+ "c.longitud,c.seccionTipica,c.clase,c.tipo,c.categoria,c.estado,c.estadoDescripcion,"
			+ "c2) from Canal c left join c.canalId c2 where c.id = ?1")
	ConsultaCanal datosBasicosPorId(int id);

	/**
	 * se buscaran los canales que son servidos por un canal
	 * 
	 * @param id
	 *            id del canal servidor
	 * @return nombre del canal
	 */
	@Query("select concat(c.nombre,' - ',c.codigo) from Canal c where c.canalId.id = ?1")
	List<String> buscarNombrePorCanalId(int id);

	/**
	 * se buscara los canales pertenecientes a una seccion
	 * 
	 * @param id.
	 *            Id de la seccion
	 * @return lista de canales con solo su nombre, id y codigo
	 */
	@Query("select new com.siomari.model.Canal(c.id,c.nombre,c.codigo) from Canal c inner join c.lstSeccionCanal "
			+ "sc where sc.seccionId.id = ?1")
	List<Canal> buscarPorSeccionId(int id);

	/**
	 * se buscara los canales pertenecientes a una zona
	 * 
	 * @param id
	 *            id de la zona
	 * @return lista de canales con solo su nombre, id y codigo
	 */
	@Query("select new com.siomari.model.Canal(c.id,c.nombre,c.codigo) from Canal c inner join c.lstSeccionCanal "
			+ "sc where sc.seccionId.zonaId.id = ?1")
	List<Canal> buscarPorZonaId(int id);

	/**
	 * se buscara los canales pertenecientes a una unidad
	 * 
	 * @param id
	 *            id de la unidad
	 * @return lista de canales con solo su nombre, id y codigo
	 */
	@Query("select new com.siomari.model.Canal(c.id,c.nombre,c.codigo) from Canal c inner join c.lstSeccionCanal "
			+ "sc where sc.seccionId.zonaId.unidadId.id = ?1")
	List<Canal> buscarPorUnidadId(int id);

	/**
	 * se buscara todos los canales
	 * 
	 * @return lista de canales con solo su nombre, id y codigo
	 */
	@Query("select new com.siomari.model.Canal(c.id,c.nombre,c.codigo) from Canal c")
	List<Canal> buscarPorDistrito();

	/**
	 * se buscaran los canales servidos por un canal
	 * 
	 * @param id
	 *            id del canal
	 * @return id de los canales servidos
	 */
	@Query("select c.id from Canal c where c.canalId.id = ?1")
	List<Integer> buscarCanalesServidos(int id);
}
