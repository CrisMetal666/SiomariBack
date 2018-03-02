package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Usuario;
import com.siomari.repository.IUsuarioRepository;
import com.siomari.service.IUsuarioService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepo;

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void registrar(Usuario usuario) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro existente
		if(usuario.getId() != 0) return;	
		
		usuarioRepo.save(usuario);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void actualizar(Usuario usuario) {

		usuarioRepo.save(usuario);
		
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void eliminar(int id) {

		usuarioRepo.delete(id);
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public List<Usuario> listar() {

		List<Usuario> lst = usuarioRepo.findAll();

		return lst;
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public Usuario listar(int id) {

		Usuario usuario = usuarioRepo.findOne(id);

		if(usuario == null) {
			usuario = new Usuario();
		}
		
		return usuario;
	}

	@Override
	public boolean existePorIdentificacion(String identificacion) {
		
		boolean res = false;
		
		if(usuarioRepo.buscarIdPorIdentificacion(identificacion) != null) res = true;
		
		return res;
	}
}
