package com.siomari.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Canal;
import com.siomari.model.CanalObra;
import com.siomari.model.EstructuraControl;
import com.siomari.model.Obra;
import com.siomari.model.Predio;
import com.siomari.model.SeccionCanal;
import com.siomari.model.dto.ConsultaCanal;
import com.siomari.model.dto.ObraDetalle;
import com.siomari.repository.ICanalRepository;
import com.siomari.service.ICanalObraService;
import com.siomari.service.ICanalService;
import com.siomari.service.IEstructuraControlService;
import com.siomari.service.IObraService;
import com.siomari.service.IPredioService;
import com.siomari.service.IUsuarioService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class CanalServiceImpl implements ICanalService {

	@Autowired
	private ICanalRepository canalRepo;

	@Autowired
	private IPredioService predioService;

	@Autowired
	private ICanalObraService canalObraService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IEstructuraControlService estructuraControlService;

	@Autowired
	private IObraService obraService;

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

		if (canal.getCanalId() != null) {

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

	@Override
	public List<Canal> buscarPorNombreOCodigoNoServidores(String query) {

		return canalRepo.buscarPorNombreOCodigoNoServidores(query);
	}

	@Override
	public List<Canal> buscarPorNombreOCodigoServidores(String query) {

		return canalRepo.buscarPorNombreOCodigoServidores(query);
	}

	@Override
	public String buscarNombrePorId(int id) {

		return canalRepo.buscarNombrePorId(id);
	}

	@Override
	public ConsultaCanal consultaCompleta(int id) {

		// traemos los datos basicos
		ConsultaCanal consultaCanal = canalRepo.datosBasicosPorId(id);

		// consultamos los predios, obras, y estructuras de control del canal
		List<Predio> lstPredio = predioService.buscarPorCanalId(id);
		List<EstructuraControl> lstEstructuraControl = estructuraControlService.buscarPorCanalId(id);
		List<String> lstCanal = canalRepo.buscarNombrePorCanalId(id);
		// contendra el nombre de la obra con sus respectivas obras registradas
		List<ObraDetalle> lstObraDetalle = new ArrayList<>();

		// buscamos las obras que contiene el canal
		List<Obra> lstObra = obraService.buscarIdNombrePorCanalId(id);

		// buscamos las obras del canal y todas los canalObra para agruparlas
		for (Obra o : lstObra) {

			ObraDetalle obraDetalle = new ObraDetalle();
			obraDetalle.setObra(o.getNombre());

			List<CanalObra> lstCanalObra = canalObraService.buscarPorCanalIdYObraId(id, o.getId());

			obraDetalle.setLstCanalObra(lstCanalObra);

			lstObraDetalle.add(obraDetalle);
		}

		// buscamos el usuario de cada predio
		lstPredio.forEach(p -> {

			String nombreUsuario = usuarioService.buscarNombrePorPredioId(p.getId());
			p.setNombreUsuario(nombreUsuario);
		});

		consultaCanal.setLstPredio(lstPredio);
		consultaCanal.setLstObraDetalle(lstObraDetalle);
		consultaCanal.setLstEstructuraControl(lstEstructuraControl);
		consultaCanal.setLstCanal(lstCanal);
		consultaCanal.setSumPredios(lstPredio.size());
		consultaCanal.setAreaServida(predioService.sumAreaPotencialPorCanalId(id));

		return consultaCanal;
	}

	@Override
	public List<String> buscarNombrePorCanalId(int id) {

		return canalRepo.buscarNombrePorCanalId(id);
	}

	@Override
	public List<Canal> buscarPorZonaId(int id) {

		return canalRepo.buscarPorZonaId(id);
	}

	@Override
	public List<Canal> buscarPorUnidadId(int id) {

		return canalRepo.buscarPorUnidadId(id);
	}

	@Override
	public List<Canal> buscarPorDistrito() {

		return canalRepo.buscarPorDistrito();
	}

	@Override
	public List<Integer> buscarCanalesServidos(int id) {
		
		return canalRepo.buscarCanalesServidos(id);
	}

}
