package com.siomari.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.siomari.model.CanalObra;

/**
 * 
 * @author crismetal
 *
 */
public interface ICanalObraService {

	/**
	 * @see com.siomari.repository.ICanalObraRepostiry
	 */
	List<CanalObra> buscarPorCanalId(int id);
	
	/**
	 * se buscaran las obras que pertenecen a un canal y obra
	 * 
	 * @param canal
	 *            id del canal
	 * @param obra
	 *            id de la obra
	 * @return id, descripcion, coordenadas de georrferenciacion y obra
	 */
	List<CanalObra> buscarPorCanalIdYObraId(int canal, int obra);
	
	/**
	 * @see com.siomari.repository.ICanalObraRepostiry
	 */
	List<CanalObra> buscarIdNombrePorCanalId(int id);

	/**
	 * se guardara una obra al canal y gardara una imagen
	 * 
	 * @param canalObra
	 *            modelo canalobra con su informacion a guardar
	 * @param file
	 *            archivo que se guardara en el servidor
	 * @return 1 si es exitoso, 2 si hubo un error al manejar el arvhivo y 0 si hubo
	 *         error al registrar
	 */
	int guardar(CanalObra canalObra, MultipartFile file);
	
	/**
	 * @see com.siomari.repository.ICanalObraRepostiry
	 */
	CanalObra buscarPorId(int id);
	
	/**
	 * se eliminara un registro por su id
	 * @param id id del canalobra
	 *  
	 */
	void eliminar(int id);

	/**
	 * se buscara las obras pertenecientes a una seccion
	 * 
	 * @param id.
	 *            Id de la seccion
	 * @return cantidad de obras
	 */
	int buscarPorSeccionId(int id);
	
	/**
	 * se buscara las obras pertenecientes a una zona
	 * 
	 * @param id.
	 *            Id de la zona
	 * @return cantidad de obras
	 */
	int buscarPorZonaId(int id);
	
	/**
	 * se buscara las obras pertenecientes a una unidad
	 * 
	 * @param id.
	 *            Id de la unidad
	 * @return cantidad de obras
	 */
	int buscarPorUnidadId(int id);
	
	/**
	 * se buscara las obras pertenecientes del distrito
	 * 
	 * 
	 * @return cantidad de obras
	 */
	int buscarPorDistrito();
}
