package com.siomari.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.siomari.model.Users;
import com.siomari.repository.IUsersRepository;
import com.siomari.service.IUsersService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class UsersServiceImpl implements IUsersService {

	@Autowired
	private IUsersRepository repo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public int registrar(Users user) {

		Integer id = repo.buscarIdPorIdentificacion(user.getIdentificacion());

		// nos aseguramos que la identificacion no se encuentre registrada
		if (id != null)
			return 2;

		/*
		 * encriptamos la contraseña, asignamos que es un usuario nuevo y le damos un
		 * estado true que significa que esta habilitado para usar el sistema
		 */
		user.setClave(encoder.encode(user.getClave()));
		user.setNuevo(true);
		user.setEstado(true);

		repo.save(user);

		return 1;
	}

	@Override
	public int actualizar(Users user) {

		Integer id = repo.buscarIdPorIdentificacion(user.getIdentificacion());

		// nos aseguramos que la identificacion no se encuentre registrada
		if (id != null) {
			/*
			 * si los id son diferentes, significa que la identificacion pertenece a otro
			 * usuario
			 */
			if (id != user.getId()) {
				return 2;
			}
		}

		user.setNuevo(false); // false proque el usuario no es nuevo

		repo.save(user);

		return 1;
	}

	@Override
	public void cambiarClave(String identificacion, String clave) {

		repo.updateClave(identificacion, encoder.encode(clave));

	}

	@Override
	public void eliminar(int id) {

		repo.delete(id);

	}

	@Override
	public Users buscarPorIdentificacion(String identificacion) {

		Users user = repo.findOneByIdentificacion(identificacion);

		return user == null ? new Users() : user;
	}

}
