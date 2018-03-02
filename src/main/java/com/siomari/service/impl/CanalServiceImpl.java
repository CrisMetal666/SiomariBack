package com.siomari.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Canal;
import com.siomari.repository.ICanalRepository;
import com.siomari.service.ICanalService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class CanalServiceImpl implements ICanalService {

	@Autowired
	private ICanalRepository canalRepo;

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void registrar(Canal canal) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro
		// existente
		if (canal.getId() != 0)
			return;

		// si la lista esta llena, vinculamos las obras al canal, para que haga una
		// persistencia en cascada
		if (canal.getLstCanalObra() != null) {
			canal.getLstCanalObra().forEach(x -> x.setCanalId(canal));
		}
		
		if(canal.getLstSeccionCanal() != null) {
			canal.getLstSeccionCanal().forEach(x -> x.setCanalId(canal));
		}

		canalRepo.save(canal);

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		if (canal.getLstCanalObra() != null) {
			canal.getLstCanalObra().forEach(x -> x.setCanalId(null));
		}
		
		if(canal.getLstSeccionCanal() != null) {
			canal.getLstSeccionCanal().forEach(x -> x.setCanalId(null));
		}

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void actualizar(Canal canal) {

		canalRepo.save(canal);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void eliminar(int id) {

		canalRepo.delete(id);
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public List<Canal> listar() {

		List<Canal> lst = canalRepo.findAll();

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		lst.forEach(x -> {

			if (x.getLstCanal() != null) {
				x.getLstCanal().forEach(y -> y.setCanalId(null));
			}
			
			if (x.getLstPredio() != null) {
				x.getLstPredio().forEach(y -> y.setCanalId(null));
			}
			
			if (x.getLstSeccionCanal() != null) {
				x.getLstSeccionCanal().forEach(y -> {
					y.setCanalId(null);
					y.setSeccionId(null);
				});
			}

			if (x.getLstCanalObra() != null) {
				x.getLstCanalObra().forEach(y -> y.setCanalId(null));
			}
		});

		return lst;
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public Canal listar(int id) {

		Canal canal = canalRepo.findOne(id);

		if(canal != null) {
			// dejamos el objeto con solo el id para que no haya referencias ciclicas
			if (canal.getLstCanal() != null) {
				List<Canal> lst = new ArrayList<>();
				canal.getLstCanal().forEach(y -> lst.add(new Canal(y.getId())));
				canal.setLstCanal(lst);
			}
			
			if (canal.getLstPredio() != null) {
				canal.getLstPredio().forEach(y -> y.setCanalId(null));
			}
			
			if (canal.getLstSeccionCanal() != null) {
				canal.getLstSeccionCanal().forEach(y -> {
					y.setCanalId(null);
					y.setSeccionId(null);
				});
			}

			if (canal.getLstCanalObra() != null) {
				canal.getLstCanalObra().forEach(y -> y.setCanalId(null));
			}
			
			if(canal.getCanalId() != null) {
				canal.setCanalId(new Canal(canal.getCanalId().getId()));
			}
		} else {
			canal = new Canal();
		}

		return canal;
	}

	/**
	 * @see com.siomari.repository.ICanalRepository
	 */
	@Override
	public List<Canal> buscarPorSeccionId(int id) {
		
		return canalRepo.buscarPorSeccionId(id);
	}

	/**
	 * @see com.siomari.service.ICanalService
	 */
	@Override
	public boolean existeCanalPorCodigo(String codigo) {
		
		boolean respuesta = false;
		
		Integer id = canalRepo.buscarIdPorCodigo(codigo);
		
		if(id != null) {
			respuesta = true;
		}
		
		return respuesta;
	}

}
