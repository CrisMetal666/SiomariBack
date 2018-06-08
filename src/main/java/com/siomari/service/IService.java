package com.siomari.service;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author crismetal
 * 
 * Declaracion de los metodos en comun para la capa service
 *
 */
@Repository
public interface IService<T> {

	/**
	 * Registrara el modelo <T>
	 * @param t modelo a registrar
	 */
	void registrar(T t);
	
	/**
	 * Actualizara el modelo <T>
	 * @param t modelo a actualizar
	 */
	void actualizar(T t);
	
	/**
	 * eliminara un modelo <T> por su id
	 * @param id
	 */
	void eliminar(int id);
	
	/**
	 * listara todos
	 * @return lista de modelo <T>
	 */
	List<T> listar();
	
	/**
	 * Buscara un modelo <T> por su id
	 * @param id
	 * @return un modelo <T>
	 */
	T listar(int id);
}
