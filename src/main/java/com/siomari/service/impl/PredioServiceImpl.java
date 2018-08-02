package com.siomari.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.siomari.config.Variables;
import com.siomari.model.Canal;
import com.siomari.model.Predio;
import com.siomari.model.Usuario;
import com.siomari.repository.IPredioRepository;
import com.siomari.service.IPredioService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class PredioServiceImpl implements IPredioService {

	@Autowired
	private IPredioRepository predioRepo;

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void registrar(Predio predio) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro
		// existente
		if (predio.getId() != 0)
			return;

		predioRepo.save(predio);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void actualizar(Predio predio) {

		predioRepo.save(predio);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void eliminar(int id) {

		predioRepo.delete(id);
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public List<Predio> listar() {

		List<Predio> lst = predioRepo.findAll();

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		lst.forEach(x -> {
			x.setCanalId(new Canal(x.getCanalId().getId()));
			x.setUsuarioId(null);
		});

		return lst;
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public Predio listar(int id) {

		Predio predio = predioRepo.findOne(id);

		if (predio != null) {
			/*
			 * dejamos el objeto con solo el id y el nombre para que no haya referencias
			 * ciclicas
			 */
			Canal canal = new Canal();
			canal.setId(predio.getCanalId().getId());
			canal.setNombre(predio.getCanalId().getNombre());
			predio.setCanalId(canal);

			predio.setNombreUsuario(predio.getUsuarioId().getNombreCompleto());

			// solo dejamos el id del predio
			predio.setUsuarioId(new Usuario(predio.getUsuarioId().getId()));
		} else {
			predio = new Predio();
		}

		return predio;
	}

	@Override
	public List<Predio> listarDatosBasicos() {

		return predioRepo.listarDatosBasicos();
	}

	@Override
	public List<Predio> listarIdCodigoNombrePorNombreOCodigo(String query) {

		// necesario para indicar que traiga los elementos que tengan coincidencia con
		// el parametro query
		String parameter = "%" + query + "%";
		return predioRepo.listarIdCodigoNombrePorNombreOCodigo(parameter);
	}

	@Override
	public int buscarIdPorCodigo(String codigo) {

		Integer id = predioRepo.buscarIdPorCodigo(codigo);

		if (id == null) {
			id = 0;
		}

		return id;
	}

	@Override
	public Double listarModuloRiegoPorId(int predio) {

		Double moduloRiego = predioRepo.listarModuloRiegoPorId(predio);

		return moduloRiego == null ? 0 : moduloRiego;
	}

	@Override
	public List<Predio> buscarPorCanalId(int id) {

		return predioRepo.buscarIdNombreCondigoPorCanalId(id);
	}

	@Override
	public String buscarNombrePorId(int id) {

		return predioRepo.buscarNombrePorId(id);
	}

	@Override
	public Double sumAreaPotencialPorCanalId(int id) {

		return predioRepo.sumAreaPotencialPorCanalId(id);
	}

	@Override
	public List<Predio> buscarNombreCodigoPropietarioPorUsuarioId(int id) {

		return predioRepo.buscarNombreCodigoPropietarioPorUsuarioId(id);
	}

	@Override
	public List<Predio> buscarPorSeccionId(int id) {

		return predioRepo.buscarPorSeccionId(id);
	}

	@Override
	public List<Predio> buscarPorZonaId(int id) {

		return predioRepo.buscarPorZonaId(id);
	}

	@Override
	public List<Predio> buscarPorUnidadId(int id) {

		return predioRepo.buscarPorUnidadId(id);
	}

	@Override
	public List<Predio> buscarPorDistrito() {

		return predioRepo.buscarPorDistrito();
	}

	@Override
	public double sumAreaPotencialPorSeccionId(int id) {

		Double area = predioRepo.sumAreaPotencialPorSeccionId(id);

		return area == null ? 0 : area;
	}

	@Override
	public double sumAreaPotencialPorZonaId(int id) {

		Double area = predioRepo.sumAreaPotencialPorZonaId(id);

		return area == null ? 0 : area;
	}

	@Override
	public double sumAreaPotencialPorUnidadId(int id) {

		Double area = predioRepo.sumAreaPotencialPorUnidadId(id);

		return area == null ? 0 : area;
	}

	@Override
	public double sumAreaPotencialPorDistrito() {

		Double area = predioRepo.sumAreaPotencialPorDistrito();

		return area == null ? 0 : area;
	}

	@Override
	public Predio buscarCoordenadasPorId(int id) {

		Predio predio = predioRepo.buscarCoordenadasPorId(id);

		return predio == null ? new Predio() : predio;
	}

	@Override
	public int guardar(Predio predio, MultipartFile file) {

		// guardamos el nombre del archivo que se construira en el servidor
		String nombrePlano = "";

		try {

			// verificamos si han enviado un archivo
			if (file.getBytes().length != 0) {
				
				nombrePlano = construirArchivo(file);

				// si no hay nombre significa que ocurrio un error al manejar el archivo
				if (nombrePlano.length() == 0)
					return 2;

				// vefirficamos si tenia un plano anteriormente para eliminarla
				if (predio.getPlano() != null) {
					FileUtils.forceDelete(new File(Variables.PATH_PREDIO_PLANO + predio.getPlano()));
				}

				// asignamos el nombre de la imagen que se acaba de contruir
				predio.setPlano(nombrePlano);
			}

			predioRepo.save(predio);

		} catch (Exception e) {

			/*
			 * si ocurrio un error al guardar el registro, eliminamos el plano que se
			 * contriyo si es que hubo alguna
			 */
			if (nombrePlano.length() != 0) {

				File plano = new File(Variables.PATH_PREDIO_PLANO + nombrePlano);

				try {
					FileUtils.forceDelete(plano);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			e.printStackTrace();

			return 0;
		}

		return 1;
	}
	
	/**
	 * se transformara los bytes a un archivo para ser alojado en el servidor
	 * 
	 * @param file
	 *            contiene el nombre del archivo con sus bytes
	 * @return nombre del archivo
	 */
	private String construirArchivo(MultipartFile file) {

		// obtenemos la extension del archivo enviado y le generamos un nombre aleatorio
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String nameFile = UUID.randomUUID().toString() + "." + extension;

		// obtenemos la ruta donde se alojan los planos prediales
		String ruta = Variables.PATH_PREDIO_PLANO;
		// contruimos la ruta donde se alojara los planos enviada desde el servidor
		String rutaPlano = ruta + nameFile;

		// objeto que nos indicara si el nombre del archivo ya existe
		File archivo = new File(rutaPlano);

		// nos aseguramos que el nombre del archivo no exista
		while (archivo.exists()) {

			nameFile = UUID.randomUUID().toString() + "." + extension;
			rutaPlano = ruta + nameFile;

			archivo = new File(rutaPlano);
		}

		try {
			FileUtils.writeByteArrayToFile(archivo, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		return nameFile;
	}
}
