package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siomari.model.Usuario;

/**
 * 
 * @author crismetal
 *
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

}
