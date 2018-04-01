package com.siomari.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.ClimatologiaDatos;
import com.siomari.model.Cultivo;
import com.siomari.model.CultivoPredio;
import com.siomari.model.Decada;
import com.siomari.model.Kc;
import com.siomari.model.PlaneacionInfo;
import com.siomari.repository.ICultivoPredioRepository;
import com.siomari.service.ICultivoPredioService;
import com.siomari.service.ICultivoService;
import com.siomari.service.IDecadaService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class CultivoPredioServiceImpl implements ICultivoPredioService {

	@Autowired
	private ICultivoPredioRepository cultivoPredioRepo;

	@Autowired
	private ICultivoService cultivoService;

	@Autowired
	private IDecadaService decadaService;

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	@Override
	public CultivoPredio buscarPorPredioIdCultivoIdPlanSiembra(int predio, int cultivo, int year, short mes,
			short periodo) {

		CultivoPredio cultivoPredio = cultivoPredioRepo.buscarPorPredioIdCultivoIdPlanSiembra(predio, cultivo, year,
				mes, periodo);

		// nos aseguramos de no devolver datos nulos
		if (cultivoPredio == null)
			cultivoPredio = new CultivoPredio();

		return cultivoPredio;
	}

	/**
	 * @see com.siomari.model.ICultivoPredioService
	 */
	@Override
	public void guardar(CultivoPredio cultivoPredio) {

		cultivoPredioRepo.save(cultivoPredio);
	}

	@Override
	public List<CultivoPredio> buscarPorPredioIdPlanSiembraId(int predio, int planSiembra) {

		List<CultivoPredio> lst = cultivoPredioRepo.buscarPorPredioIdPlanSiembraId(predio, planSiembra);

		// dejamos nulo los kc ya que no lo necesitamos
		lst.forEach(item -> {
			item.getCultivoId().setLstKc(null);
		});

		return lst;
	}

	@Override
	public void guardar(List<CultivoPredio> cultivoPredio) {

		// obtenemos la lista ids que se desean eliminar
		Integer ids[] = cultivoPredio.get(0).getIdsEliminar();

		// verificamos que existan los datos y creamos la lista para eliminarlos
		// conjuntamente
		if (ids != null) {
			List<CultivoPredio> eliminar = new ArrayList<>();
			for (int id : ids) {
				eliminar.add(new CultivoPredio(id));
			}
			cultivoPredioRepo.delete(eliminar);
		}

		cultivoPredioRepo.save(cultivoPredio);
	}

	@Override
	public void eliminar(int id) {

		cultivoPredioRepo.delete(id);
	}

	@Override
	public List<PlaneacionInfo> informacionSiembras(int cultivo, int year, char campania) {

		// calculamos el rango de meses segun la camapaña
		short min = 0;
		short max = 0;

		if (campania == 'A') {

			min = 1;
			max = 6;

		} else {

			min = 7;
			max = 12;

		}

		List<CultivoPredio> lst = cultivoPredioRepo.hectareasPlanSiembraPorCultivo(cultivo, year, min, max);

		// nos va a servir para saber si el mes ha cambiado cuando estemos iterando la
		// lista
		short mes = 0;
		// guardaremos las hectareas de cada mes
		float hectareas[] = new float[3];
		// posicion en la que se guardara cada hectarea (periodo del mes)
		int index = 0;
		// necesario para poder agregar el ultimo registro a la lista sin repetirlo
		boolean agregado = false;

		List<PlaneacionInfo> lstPlaneacion = new ArrayList<>();

		for (CultivoPredio cp : lst) {

			// inicializamos el mes en la primera iteracion
			if (mes == 0) {
				mes = cp.getPlanSiembraId().getMes();
				// le restamos 1 para que se comporte como las posiciones de un array
				index = cp.getPlanSiembraId().getPeriodo() - 1;
			}

			// analizamos si el mes ha cambiado en la presente iteracion
			if (mes != cp.getPlanSiembraId().getMes()) {

				// guardamos en la lista la informacion
				lstPlaneacion.add(new PlaneacionInfo(mes, hectareas));

				// alistamos las variables para una nueva captura de informacion
				mes = cp.getPlanSiembraId().getMes();
				// le restamos 1 para que se comporte como las posiciones de un array
				index = cp.getPlanSiembraId().getPeriodo() - 1;
				hectareas = new float[3];
				agregado = true;
			}

			// analizamos si se ha cambiado de periodo
			if (index != cp.getPlanSiembraId().getPeriodo() - 1) {
				// le restamos 1 para que se comporte como las posiciones de un array
				index = cp.getPlanSiembraId().getPeriodo() - 1;
			}

			// guardamos la hectareas de caraperiodo del cada mes
			hectareas[index] += cp.getHectareas();
			agregado = false;

		}

		// verificamos si hay un ultimo mes por adicionar a la lista
		if (!agregado && mes != 0) {
			lstPlaneacion.add(new PlaneacionInfo(mes, hectareas));
		}

		return lstPlaneacion;
	}

	@Override
	public List<List<PlaneacionInfo>> informacionSiembrasDemanda(int year, char campania) {

		// obtenemos todos los cultivos que ofrece el distrito con su kc para hacer los
		// calculos
		List<Cultivo> lstCultivo = cultivoService.listar();
		List<List<PlaneacionInfo>> lst = new ArrayList<>();

		for (Cultivo cultivo : lstCultivo) {

			// obtenemos la cantidad de hectareas sembradas de el cultivo en cada mes
			List<PlaneacionInfo> lstPlaneacion = this.informacionSiembras(cultivo.getId(), year, campania);

			this.calculoDemanda(lstPlaneacion, cultivo, year);

			lst.add(lstPlaneacion);

		}

		return lst;
	}

	/**
	 * Cacular la demanda de agua de un determinado cultivo en diferentes meses
	 * 
	 * @param lstPlaneacion
	 *            lista de cultivos con su cantidad de hectareas sembradas
	 * @param year
	 *            año en el que se quiere consultar
	 * @return misma lista enviada, pero en cada posicion si le agregara la demanda
	 *         de agua
	 */
	private List<PlaneacionInfo> calculoDemanda(List<PlaneacionInfo> lstPlaneacion, Cultivo cultivo, int year) {		

		/*
		 * recorremos la planeacion de cada mes del cultivo para calcular por separado la demanda de agua 
		 * decadalmente 
		 */
		for (PlaneacionInfo planeacion : lstPlaneacion) {

			/*
			 * obtenemos el mes de la planeacion para despues poder ubicarnos en los meses que siguen en 
			 * la demanda de agua
			 */
			int mes = planeacion.getMesNumerico();
			
			
			List<Kc> lstKc = cultivo.getLstKc();
			
			//necesitamos tener ordenado los kc por orden para poder organizar bien la informacion
			lstKc.sort(Comparator.comparing(Kc::getDecada));
			
			//iremos guardado el meses de la demanda de agua y su valor decadalmente
			List<Map<String,Object>> lstDemanda = new ArrayList<>();

			//guardaremos el mes de la demanda y su valor decadalmente
			Map<String, Object> demanda = new HashMap<>();
			
			//valor de la demanda de agua decadalmente siendo la posicion 0 decada 1 y la posicion 2 la decada 3
			double lstVolumen[] = new double[3];
			
			//informacion climatologiaca de un mes determinado con sus valores decadalmente
			Decada decada = new Decada();
			
			//iremos ubicando en que decada estamos
			int indexDecada = 0;

			//recorremos los kc del cultivo e iremos haciendo los calculos de la demanda de agua
			for (Kc kc : lstKc) {

				/*
				 *  cada vez que el valor sea 0 significa que debemos consultar los valores climatologicos
				 *  en el mes que corresponde 
				 */
				if (indexDecada == 0) {
					decada = decadaService.buscarPorMesYYear(mes, year);
					/*
					 * aumentamos el valor del mes para que asi se vaya consultando los valores mensualmente
					 * cada 3 decadas
					 */
					mes++;
				}

				//almacenara los valores climatologicos de la decada determinada
				ClimatologiaDatos climatologiaDatos = null;

				//obtenemos los valores segun la decada en la que se encuentra la iteracion
				if (indexDecada == 0) {
					
					climatologiaDatos = decada.getDecada1();
					
				} else if(indexDecada == 1) {
					
					climatologiaDatos = decada.getDecada2();
					
				}else if(indexDecada == 2) {
					
					climatologiaDatos = decada.getDecada3();
					
				}
				
				//obtenemos los valores necesarios para los calculos
				double pricipitacionEfectiva = climatologiaDatos.getPrecipitacionEfecto();
				double evaporacion = climatologiaDatos.getEvaporacion();
				double areaTotal = planeacion.getTotal();

				//calculamos el volumen de la demanda de agua
				double volumen = calcularVolumenDemanda(pricipitacionEfectiva, evaporacion, kc.getKc(), areaTotal);
				
				//almacenamos el volumen en la lista en el index que le corresponde
				lstVolumen[indexDecada] = volumen;
				
				//aumentamos el index para que rn la siguiente iteracion se almacene en la decada correxpondiente
				indexDecada++;
				
				//si el index es 3, significa que pasamos al sieguiente mes
				if(indexDecada == 3) {
					
					/*
					 * almacenamos la informacion que llevavamos guardada, se le debe restar al mes 1 porque
					 * ya le habiamos sumado 1 al mes al principio de la iteracion
					 */
					demanda.put("mes", mesNumericoAMesTexto(mes-1));
					demanda.put("decada", lstVolumen);
					
					//agregamos a la lista para que esta despues se almacene en el objeto de planeacion
					lstDemanda.add(demanda);
					
					/*
					 * reiniciamos los objetos para que no sobreescriba los antigos valores por los futuros valores 
					 * que se calculen en las siguientes iteraciones
					 */
					demanda = new HashMap<>();
					lstVolumen = new double[3];
					
					//dejamos la decada en 0 porque iniciamos un nuevo mes
					indexDecada = 0;
					
				}

				
			}

			//le agregamos al objeto los calculos de la demanda de agua
			planeacion.setDemanda(lstDemanda);
			//le adicionamos el mes en texto
			planeacion.setCultivo(cultivo.getNombre());

		}

		return lstPlaneacion;
	}

	/**
	 * calculara la demanda de agua que requiere un cultivo con determinada area
	 * sembrada
	 * 
	 * @param pricipitacionEfectiva
	 *            informacion climatologica necesaria
	 * @param evaporacion
	 *            informacion climatologica necesaria
	 * @param kc
	 *            constante del cultivo para realizar los calculos
	 * @param areaTotal
	 *            area total sembrada del cultivo
	 * @return demanda de agua
	 */
	private double calcularVolumenDemanda(double pricipitacionEfectiva, double evaporacion, double kc,
			double areaTotal) {

		// valor quemado por el momento
		double eficiencia = 0.532;

		double pe = pricipitacionEfectiva;
		double ev = evaporacion;
		double evp = ev * 0.8;
		double evt = evp * kc;
		double nr = evt - pe;
		double dbr = nr > 0 ? nr / eficiencia : 0;
		double volumen = dbr * 10 * areaTotal;

		return volumen;
	}

	// Convertimos el mes numerico a text
	private String mesNumericoAMesTexto(int mesNumerico) {

		String mes = "";

		if (mesNumerico == 1) {

			mes = "Enero";

		} else if (mesNumerico == 2) {

			mes = "Febrero";

		} else if (mesNumerico == 3) {

			mes = "Marzo";

		} else if (mesNumerico == 4) {

			mes = "Abril";

		} else if (mesNumerico == 5) {

			mes = "Mayo";

		} else if (mesNumerico == 6) {

			mes = "Junio";

		} else if (mesNumerico == 7) {

			mes = "Julio";

		} else if (mesNumerico == 8) {

			mes = "Agosto";

		} else if (mesNumerico == 9) {

			mes = "Septiembre";

		} else if (mesNumerico == 10) {

			mes = "Octubre";

		} else if (mesNumerico == 11) {

			mes = "Noviembre";

		} else {

			mes = "Diciembre";

		}

		return mes;
	}

}
