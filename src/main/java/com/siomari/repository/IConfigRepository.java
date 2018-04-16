package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siomari.model.Config;

/**
 * 
 * @author crismetal
 *
 */
public interface IConfigRepository extends JpaRepository<Config, Integer> {
	
	

}
