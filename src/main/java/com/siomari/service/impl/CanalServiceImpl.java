package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Canal;
import com.siomari.model.CanalObra;
import com.siomari.model.SeccionCanal;
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

		if (canal.getLstSeccionCanal() != null) {
			canal.getLstSeccionCanal().forEach(x -> x.setCanalId(canal));
		}

		canalRepo.save(canal);

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		if (canal.getLstCanalObra() != null) {
			canal.getLstCanalObra().forEach(x -> x.setCanalId(null));
		}

		if (canal.getLstSeccionCanal() != null) {
			canal.getLstSeccionCanal().forEach(x -> x.setCanalId(null));
		}

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void actualizar(Canal canal) {

		// si la lista esta llena, vinculamos las obras al canal, para que haga una
		// persistencia en cascada
		if (canal.getLstCanalObra() != null) {
			canal.getLstCanalObra().forEach(x -> x.setCanalId(canal));
		}

		if (canal.getLstSeccionCanal() != null) {
			canal.getLstSeccionCanal().forEach(x -> x.setCanalId(canal));
		}

		canalRepo.save(canal);

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		if (canal.getLstCanalObra() != null) {
			canal.getLstCanalObra().forEach(x -> x.setCanalId(null));
		}

		if (canal.getLstSeccionCanal() != null) {
			canal.getLstSeccionCanal().forEach(x -> x.setCanalId(null));
		}

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

		Canal canal = canalRepo.buscarPorId(id);

		// nos aseguramos de no devolver valores nulos
		if (canal == null) {
			canal = new Canal();
		}

		// eliminamos las referencias ciclicas
		if (canal.getLstCanalObra() != null) {

			for (CanalObra item : canal.getLstCanalObra()) {

				item.setCanalId(new Canal(canal.getId()));

			}

		}

		// eliminamos las referencias ciclicas
		if (canal.getLstSeccionCanal() != null) {

			for (SeccionCanal item : canal.getLstSeccionCanal()) {

				item.setCanalId(new Canal(canal.getId()));
				item.getSeccionId().getZonaId().getUnidadId().setLstZona(null);
				item.getSeccionId().getZonaId().setLstSeccion(null);
				item.getSeccionId().setLstSeccionCanal(null);

			}

		}
		
		if(canal.getCanalId() != null) {
			
			Canal c = new Canal();
			c.setId(canal.getCanalId().getId());
			c.setNombre(canal.getCanalId().getNombre());
			
			canal.setCanalId(c);
			
		}

		canal.setLstPredio(null);
		canal.setLstCanal(null);

		return canal;
	}

	/**
	 * @see com.siomari.repository.ICanalRepository
	 */
	@Override
	public List<Canal> buscarPorSeccionId(int id) {

		return canalRepo.buscarPorSeccionId(id);
	}

	@Override
	public List<Canal> buscarPorNombreOCodigo(String query) {

		return canalRepo.buscarPorNombreOCodigo(query);
	}

	@Override
	public int buscarIdPorCodigo(String codigo) {

		Integer id = canalRepo.buscarIdPorCodigo(codigo);

		if (id == null) {
			id = 0;
		}

		return id;
	}

	@Override
	public double buscarCaudalDisenioPorId(int canal) {
		
		Double cudalDisenio = canalRepo.buscarCaudalDisenioPorId(canal);
		
		return cudalDisenio == null ? 0 : cudalDisenio;
	}

}
