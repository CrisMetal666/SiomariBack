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
import com.siomari.model.CanalObra;
import com.siomari.repository.ICanalObraRepository;
import com.siomari.service.ICanalObraService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class CanalObraServiceImpl implements ICanalObraService {

	@Autowired
	private ICanalObraRepository canalObraRepo;

	@Override
	public List<CanalObra> buscarPorCanalId(int id) {

		return canalObraRepo.buscarPorCanalId(id);
	}

	@Override
	public List<CanalObra> buscarIdNombrePorCanalId(int id) {

		return canalObraRepo.buscarIdNombrePorCanalId(id);
	}

	@Override
	public int guardar(CanalObra canalObra, MultipartFile file) {

		// guardamos el nombre de la imagen que se construira en el servidor
		String nombreImagen = "";

		try {

			// verificamos si han enviado una imagen
			if (file != null) {
				nombreImagen = construirImagen(file);

				// si no hay nombre significa que ocurrio un error al manejar el archivo
				if (nombreImagen.length() == 0)
					return 2;
				
				// vefirficamos si tenia una imagen anteriormente para eliminarla
				if(canalObra.getImagen() != null) {
					FileUtils.forceDelete(new File(Variables.PATH_OBRAS_IMAGEN + canalObra.getImagen()));
				}

				// asignamos el nombre de la imagen que se acaba de contruir
				canalObra.setImagen(nombreImagen);
			}

			canalObraRepo.save(canalObra);

		} catch (Exception e) {

			/*
			 * si ocurrio un error al guardar el registro, eliminamos la imagen que se
			 * contriyo si es que hubo alguna
			 */
			if (nombreImagen.length() != 0) {

				File imagen = new File(Variables.PATH_OBRAS_IMAGEN + nombreImagen);

				try {
					FileUtils.forceDelete(imagen);
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
	 * se contruira una imagen en el servidor
	 * 
	 * @param file
	 *            contiene el nombre de la imagen con sus bytes
	 * @return nombre de la imagen
	 */
	private String construirImagen(MultipartFile file) {

		// obtenemos la extension del archivo enviado y le generamos un nombre aleatorio
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String nameFile = UUID.randomUUID().toString() + "." + extension;

		// obtenemos la ruta donde se alojan las imagenes de las obras
		String ruta = Variables.PATH_OBRAS_IMAGEN;
		// contruimos la ruta donde se alojara la imagen enviada desde el servidor
		String rutaImagen = ruta + nameFile;

		// objeto que nos indicara si el nombre del archivo ya existe
		File archivo = new File(rutaImagen);

		// nos aseguramos que el nombre del archivo no exista
		while (archivo.exists()) {

			nameFile = UUID.randomUUID().toString() + "." + extension;
			rutaImagen = ruta + nameFile;

			archivo = new File(rutaImagen);
		}

		try {
			FileUtils.writeByteArrayToFile(archivo, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		return nameFile;
	}

	@Override
	public CanalObra buscarPorId(int id) {
		
		CanalObra canalObra = canalObraRepo.buscarPorId(id);
		
		return canalObra == null ? new CanalObra() : canalObra;
	}

	@Override
	public void eliminar(int id) {
		
		// obtenemos el nombre de la imagen para ser borrada
		String imagen = canalObraRepo.getImagenPorId(id);
		
		canalObraRepo.delete(id); // eliminamos el registro
		
		// si no tiene imagen salimos del metodo
		if(imagen.length() == 0) return;
		
		try {
			// eliminamos la imagen
			FileUtils.forceDelete(new File(Variables.PATH_OBRAS_IMAGEN + imagen));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
