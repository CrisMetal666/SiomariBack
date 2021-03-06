package com.siomari.service.impl;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.ClimatologiaDatos;
import com.siomari.model.Config;
import com.siomari.model.Cultivo;
import com.siomari.model.CultivoPredio;
import com.siomari.model.Decada;
import com.siomari.model.Kc;
import com.siomari.model.dto.PlaneacionInfo;
import com.siomari.repository.ICultivoPredioRepository;
import com.siomari.service.IConfigService;
import com.siomari.service.ICultivoPredioService;
import com.siomari.service.ICultivoService;
import com.siomari.service.IDecadaService;
import com.siomari.util.FormatoFechas;

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

	@Autowired
	private FormatoFechas formatoFechas;

	@Autowired
	private IConfigService configService;

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
	public List<PlaneacionInfo> informacionSiembras(int cultivo, int year, char campania, int unidad) {

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

		List<CultivoPredio> lst = cultivoPredioRepo.hectareasPlanSiembraPorCultivo(cultivo, year, min, max, unidad);

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
	public List<List<PlaneacionInfo>> informacionSiembrasDemanda(int year, char campania, int unidad) {

		/*
		 * obtenemos todos los cultivos que ofrece el distrito con su kc para hacer los
		 * calculos
		 */
		List<Cultivo> lstCultivo = cultivoService.listar();
		List<List<PlaneacionInfo>> lst = new ArrayList<>();

		for (Cultivo cultivo : lstCultivo) {

			// obtenemos la cantidad de hectareas sembradas de el cultivo en cada mes
			List<PlaneacionInfo> lstPlaneacion = this.informacionSiembras(cultivo.getId(), year, campania, unidad);

			// le agregamos a los objetos la demanda
			this.calculoDemanda(lstPlaneacion, cultivo);// , year);

			lst.add(lstPlaneacion);

		}

		return lst;
	}

	// ------------- Balance
	@Override
	public List<PlaneacionInfo> demandaDecadalTodal(int year, char campania, int unidad) {

		Map<Integer, Double[]> demanda = new HashMap<>();

		/*
		 * obtenemos todos los cultivos que ofrece el distrito con su kc para hacer los
		 * calculos
		 */
		List<Cultivo> lstCultivo = cultivoService.listar();

		for (Cultivo cultivo : lstCultivo) {

			// obtenemos la cantidad de hectareas sembradas de el cultivo en cada mes
			List<PlaneacionInfo> lstPlaneacion = this.informacionSiembras(cultivo.getId(), year, campania, unidad);

			// obtenemos el volumen decadal total de el cultivo
			Map<Integer, Double[]> demandaCultivo = this.calculoDemandaTotal(lstPlaneacion, cultivo.getLstKc());

			for (Integer mes : demandaCultivo.keySet()) {

				// obtenemos la sumatoria de volumenes
				Double volumenes[] = demanda.get(mes);
				// obtenemos los volumenes del mes actual de la iteracion
				Double volumenesCultivo[] = demandaCultivo.get(mes);

				if (volumenes == null) {

					/*
					 * si volumenes es nulo singnifica que no hay volumenes en ese mes, entonces
					 * agregamos los volumenes calculados
					 */
					demanda.put(mes, volumenesCultivo);

				} else {

					// sumamos los volumenes de la demanda
					for (int i = 0; i < volumenes.length; i++) {

						volumenes[i] += volumenesCultivo[i];

					}

					// reemplazamos el valor de los volumenes
					demanda.put(mes, volumenes);

				}

			}

		}

		// objeto que llevara la sumatoria de volumenes decadal de cada mes
		List<PlaneacionInfo> demandaDecadal = new ArrayList<>();
		// traemos el caudal y horas de riego
		Config config = configService.listar();
		// verificamos si el año es biciesto
		boolean biciesto = Year.of(year).isLeap();

		// obtenemos todas las llaves que tiene el map, osea los meses de las demandas
		for (Integer key : demanda.keySet()) {

			Double volumenes[] = demanda.get(key);

			/*
			 * trasbasamos los datos del map a otro, el cual nos ayudara para visualizarlo
			 * mejor en un json
			 */
			PlaneacionInfo info = new PlaneacionInfo();
			info.setMesNumerico(key);
			info.setPeriodo1(volumenes[0].floatValue());
			info.setPeriodo2(volumenes[1].floatValue());
			info.setPeriodo3(volumenes[2].floatValue());

			Map<String, Object> balance = new HashMap<>();

			// calculamos los dias que tiene la ultima decada del mes
			int dias = Month.of(key).length(biciesto) - 20;

			// calculo del caudal de los primeras dos decadas
			double caudal1 = calcularCauadal(config.getCaudal(), config.getHoras(), 10);
			// calculo del caudal de la ultima decada
			double caudal2 = calcularCauadal(config.getCaudal(), config.getHoras(), dias);

			// calculamos el balance
			double balance1 = caudal1 - info.getPeriodo1();
			double balance2 = caudal1 - info.getPeriodo2();
			double balance3 = caudal2 - info.getPeriodo3();

			balance.put("decada1", balance1);
			balance.put("decada2", balance2);
			balance.put("decada3", balance3);
			balance.put("total", balance2 + balance3 + balance1);

			// debemos crear la lista para poder almacenarla en el ibjeto info
			List<Map<String, Object>> lst = new ArrayList<>();
			lst.add(balance);

			info.setDemanda(lst);

			demandaDecadal.add(info);
		}

		return demandaDecadal;
	}

	private Map<Integer, Double[]> calculoDemandaTotal(List<PlaneacionInfo> lstPlaneacion, List<Kc> lstKc) {

		// objeto donde se iran sumando los volumens decadales de cada mes
		Map<Integer, Double[]> demanda = new HashMap<>();

		// consultamos la lamina y la eficiencia
		Config config = configService.listar();

		/*
		 * necesitamos tener ordenado los kc por orden para poder organizar bien la
		 * informacion
		 */
		lstKc.sort(Comparator.comparing(Kc::getDecada));

		// creamos un cultivo solo con el kc
		Cultivo cultivo = new Cultivo();
		cultivo.setLstKc(lstKc);

		/*
		 * recorremos la planeacion de cada mes del cultivo para calcular por separado
		 * la demanda de agua decadalmente
		 */
		for (PlaneacionInfo planeacion : lstPlaneacion) {

			/*
			 * calculamos los volumenes por separado, segun la decada en la que se hayan
			 * sembrado
			 */
			List<Map<String, Object>> lstDemanda1 = calculoVolumenDecadal(planeacion.getMesNumerico(), 0,
					planeacion.getPeriodo1(), config, cultivo.getLstKc());

			List<Map<String, Object>> lstDemanda2 = calculoVolumenDecadal(planeacion.getMesNumerico(), 1,
					planeacion.getPeriodo2(), config, cultivo.getLstKc());

			List<Map<String, Object>> lstDemanda3 = calculoVolumenDecadal(planeacion.getMesNumerico(), 2,
					planeacion.getPeriodo3(), config, cultivo.getLstKc());
			
			List<Map<String, Object>> lstDemanda = this.sumarVolumenesDecadales(lstDemanda1,lstDemanda2,lstDemanda3);
			
			for(Map<String, Object> map : lstDemanda) {
				
				Integer mes = formatoFechas.mesTextoAMesNumerico(map.get("mes").toString()) ;
				double lstVolumen[] = (double[]) map.get("decada");
				
				Double volumenesDecadales[] = demanda.get(mes);
				
				if (volumenesDecadales == null) {

					/*
					 * si volumenesDecadales es nulo singnifica que no hay volumenes en ese mes,
					 * entonces agregamos los volumenes calculados
					 */
					demanda.put(mes, new Double[] {
							lstVolumen[0],
							lstVolumen[1],
							lstVolumen[2]
					});

				} else {

					// sumamos los volumenes de la demanda
					for (int i = 0; i < lstVolumen.length; i++) {

						volumenesDecadales[i] += lstVolumen[i];

					}

					// reemplazamos el valor de los volumenes
					demanda.put(mes, volumenesDecadales);

				}
				
			}
		}

		return demanda;
	}

	// ------------- plan de siembras
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
	private List<PlaneacionInfo> calculoDemanda(List<PlaneacionInfo> lstPlaneacion, Cultivo cultivo) {

		// consultamos la lamina y la eficiencia
		Config config = configService.listar();

		List<Kc> lstKc = cultivo.getLstKc();

		/*
		 * necesitamos tener ordenado los kc por orden para poder organizar bien la
		 * informacion
		 */
		lstKc.sort(Comparator.comparing(Kc::getDecada));

		/*
		 * recorremos la planeacion de cada mes del cultivo para calcular por separado
		 * la demanda de agua decadalmente
		 */
		for (PlaneacionInfo planeacion : lstPlaneacion) {

			/*
			 * calculamos los volumenes por separado, segun la decada en la que se hayan
			 * sembrado
			 */
			List<Map<String, Object>> lstDemanda1 = calculoVolumenDecadal(planeacion.getMesNumerico(), 0,
					planeacion.getPeriodo1(), config, cultivo.getLstKc());

			List<Map<String, Object>> lstDemanda2 = calculoVolumenDecadal(planeacion.getMesNumerico(), 1,
					planeacion.getPeriodo2(), config, cultivo.getLstKc());

			List<Map<String, Object>> lstDemanda3 = calculoVolumenDecadal(planeacion.getMesNumerico(), 2,
					planeacion.getPeriodo3(), config, cultivo.getLstKc());

			List<Map<String, Object>> lstDemanda = this.sumarVolumenesDecadales(lstDemanda1,lstDemanda2,lstDemanda3);

			// agregamos el volumen y el nombre del cultivo
			planeacion.setDemanda(lstDemanda);
			planeacion.setCultivo(cultivo.getNombre());

		}

		return lstPlaneacion;
	}

	private List<Map<String, Object>> sumarVolumenesDecadales(List<Map<String, Object>> lstDemanda1,
			List<Map<String, Object>> lstDemanda2, List<Map<String, Object>> lstDemanda3) {

		List<Map<String, Object>> lstDemanda = new ArrayList<>();

		// sumamos los volumenes en las decadas en las que se interponieron
		for (int i = 0; i < lstDemanda1.size(); i++) {

			Map<String, Object> map1 = lstDemanda1.get(i);
			Map<String, Object> map2 = lstDemanda2.get(i);
			Map<String, Object> map3 = lstDemanda3.get(i);

			double lstVolumen1[] = (double[]) map1.get("decada");
			double lstVolumen2[] = (double[]) map2.get("decada");
			double lstVolumen3[] = (double[]) map3.get("decada");

			// sumamos los volumenes
			double lstVolumen[] = { lstVolumen1[0] + lstVolumen2[0] + lstVolumen3[0],
					lstVolumen1[1] + lstVolumen2[1] + lstVolumen3[1],
					lstVolumen1[2] + lstVolumen2[2] + lstVolumen3[2] };

			Map<String, Object> demanda = lstDemanda1.get(i);

			demanda.put("mes", map1.get("mes"));
			demanda.put("decada", lstVolumen);

			lstDemanda.add(demanda);
		}

		return lstDemanda;
	}

	/**
	 * se calculara el consumo de agua de un cultivo sembrado en un mes y decada
	 * especificada
	 * 
	 * @param mes
	 *            mes de siembra del cultivo
	 * @param indexDecada
	 *            especifica si es la primera (0), segunda (1) o tercera decada (2)
	 * @param area
	 *            area sembrada del cultivo
	 * @param config
	 *            datos registrado en la entidad config
	 * @param lstKc
	 *            kc del cultivo especificado
	 * @return volumnes consumidos decadalmente
	 */
	private List<Map<String, Object>> calculoVolumenDecadal(int mes, int indexDecada, double area, Config config,
			List<Kc> lstKc) {

		// necesitamos saber si es el primer almacenamiento para calcularlo
		boolean primerAlmacenamiento = true;
		// guardamos el almacenamiento que resulta del calculo de la demanda hidrica
		double almacenamiento = 0;

		/*
		 * obtenemos el mes de la planeacion para despues poder ubicarnos en los meses
		 * que siguen en la demanda de agua
		 */
		// int mes = planeacion.getMesNumerico();

		// List<Kc> lstKc = cultivo.getLstKc();

		/*
		 * necesitamos tener ordenado los kc por orden para poder organizar bien la
		 * informacion
		 */
		// lstKc.sort(Comparator.comparing(Kc::getDecada));

		// iremos guardado el meses de la demanda de agua y su valor decadalmente
		List<Map<String, Object>> lstDemanda = new ArrayList<>();

		// guardaremos el mes de la demanda y su valor decadalmente
		Map<String, Object> demanda = new HashMap<>();

		/*
		 * valor de la demanda de agua decadalmente siendo la posicion 0 decada 1 y la
		 * posicion 2 la decada 3
		 */
		double lstVolumen[] = new double[3];

		// informacion climatologiaca de un mes determinado con sus valores decadalmente
		Decada decada = new Decada();

		/*
		 * si la decada es diferente de 0 debemos consultar primero los datos de
		 * climatologia
		 */
		if (indexDecada != 0) {
			decada = decadaService.probabilidadDel75(mes);
			/*
			 * aumentamos el valor del mes para que asi se vaya consultando los valores
			 * mensualmente cada 3 decadas
			 */
			mes++;
		}

		// iremos ubicando en que decada estamos
		// int indexDecada = 0;

		// convertimos la lamina de metros a milimetros
		double lamina = config.getLamina() * 1000;

		/*
		 * nos indicara si los volumenes fueron agregados a la lista
		 */
		boolean guardado = false;

		/*
		 * recorremos los kc del cultivo e iremos haciendo los calculos de la demanda de
		 * agua
		 */
		for (Kc kc : lstKc) {
			/*
			 * cada vez que inicie una iteracion señanalos que los volumenes que se
			 * calcularan aun no se han almacenado en la lista
			 */
			guardado = false;

			/*
			 * cada vez que el valor sea 0 significa que debemos consultar los valores
			 * climatologicos en el mes que corresponde
			 */
			if (indexDecada == 0) {
				decada = decadaService.probabilidadDel75(mes);
				/*
				 * aumentamos el valor del mes para que asi se vaya consultando los valores
				 * mensualmente cada 3 decadas
				 */
				mes++;
			}

			// almacenara los valores climatologicos de la decada determinada
			ClimatologiaDatos climatologiaDatos = null;

			// obtenemos los valores segun la decada en la que se encuentra la iteracion
			if (indexDecada == 0) {

				climatologiaDatos = decada.getDecada1();

			} else if (indexDecada == 1) {

				climatologiaDatos = decada.getDecada2();

			} else if (indexDecada == 2) {

				climatologiaDatos = decada.getDecada3();

			}

			// obtenemos los valores necesarios para los calculos
			double pricipitacion = climatologiaDatos.getPrecipitacion();
			double evaporacion = climatologiaDatos.getEvaporacion();
			// double area = planeacion.getTotal();

			if (primerAlmacenamiento) {

				primerAlmacenamiento = false;
				almacenamiento = lamina;

			}

			// calculamos el volumen de la demanda de agua
			double valores[] = calcularVolumenDemanda(pricipitacion, evaporacion, kc.getKc(), area, lamina,
					config.getEficiencia(), almacenamiento);

			// tomamos el volumen
			double volumen = valores[0];

			// guardamos el almacenamiento
			almacenamiento = valores[1];

			// almacenamos el volumen en la lista en el index que le corresponde
			lstVolumen[indexDecada] = volumen;

			/*
			 * aumentamos el index para que rn la siguiente iteracion se almacene en la
			 * decada correxpondiente
			 */
			indexDecada++;

			// si el index es 3, significa que pasamos al sieguiente mes
			if (indexDecada == 3) {

				/*
				 * almacenamos la informacion que llevavamos guardada, se le debe restar al mes
				 * 1 porque ya le habiamos sumado 1 al mes al principio de la iteracion
				 */
				demanda.put("mes", formatoFechas.mesNumericoAMesTexto(mes - 1));
				demanda.put("decada", lstVolumen);

				/*
				 * agregamos a la lista para que esta despues se almacene en el objeto de
				 * planeacion
				 */
				lstDemanda.add(demanda);

				/*
				 * reiniciamos los objetos para que no sobreescriba los antigos valores por los
				 * futuros valores que se calculen en las siguientes iteraciones
				 */
				demanda = new HashMap<>();
				lstVolumen = new double[3];

				// dejamos la decada en 0 porque iniciamos un nuevo mes
				indexDecada = 0;

				/*
				 * señalamos que el volumen calculado ya ha sido agregado a la lista
				 */
				guardado = true;

			}

		}

		/*
		 * agregamos los ultimos valores que no se almacenaron en la lista
		 */
		if (!guardado) {
			/*
			 * almacenamos la informacion que llevavamos guardada, se le debe restar al mes
			 * 1 porque ya le habiamos sumado 1 al mes al principio de la iteracion
			 */
			demanda.put("mes", formatoFechas.mesNumericoAMesTexto(mes - 1));
			demanda.put("decada", lstVolumen);

			/*
			 * agregamos a la lista para que esta despues se almacene en el objeto de
			 * planeacion
			 */
			lstDemanda.add(demanda);
		}

		//System.out.println("\n\n fin primer cultivo \n\n");

		return lstDemanda;

	}

	/**
	 * calculara la demanda de agua que requiere un cultivo con determinada area
	 * sembrada
	 * 
	 * @param pricipitacion
	 *            informacion climatologica necesaria
	 * @param evaporacion
	 *            informacion climatologica necesaria
	 * @param kc
	 *            constante del cultivo para realizar los calculos
	 * @param areaTotal
	 *            area total sembrada del cultivo
	 * @param preAlm
	 *            almacenamiento
	 * @return demanda de agua y excedente, siendo la posicion 0 el volumen y la
	 *         posicion 1 el excedente
	 */
	private double[] calcularVolumenDemanda(double precipitacion, double evaporacion, double kc, double areaTotal,
			double lamina, double eficiencia, double preAlm) {

		double ev = evaporacion;
		double evp = ev * 0.8;
		double evt = evp * kc;
		double pe = this.calcularPrecipitacionEfectiva(lamina, evt, precipitacion);
		double alm = preAlm + pe;
		double nr = alm - evt;

		if (nr > 0) {
			nr = 0;
		}

		double dbr = nr / eficiencia;
		double volumen = dbr * 10 * areaTotal * -1;

		double exc = nr < 0 ? 0 : (alm - evt) / eficiencia;

//		System.out.println("lam " + lamina + " evt " + evt + " pt " + precipitacion + "nr - " + nr + " pe - " + pe
//				+ " exc - " + exc + " vol - " + volumen + " - alm " + alm + "\n evp " + evp + " kc " + kc + " area "
//				+ areaTotal + " dbr " + dbr);
		double valores[] = { volumen, exc };

		return valores;
	}

	/**
	 * se hara el calculo de la precipitacion efectiva
	 * 
	 * @param lamina
	 *            lamina promedio del distrito
	 * @param evt
	 *            evapotranspiracion
	 * 
	 * @param pt
	 *            precipitacion
	 * @return precipitacion efectivaS
	 */
	private double calcularPrecipitacionEfectiva(double lamina, double evt, double pt) {

		double d = lamina;
		double f = 0.531747 + 0.011621 * d - 0.000089 * Math.pow(d, 2) + 0.00000023 * Math.pow(d, 3);
		double pe = f * (1.252474 * Math.pow(pt, 0.82416) - 2.935224) * Math.pow(10, 0.00095 * evt);

		//System.out.println("pe " + pe);

		return pe >= 0 ? pe : 0;
	}

	/**
	 * se calcula el caudal de metrios cubicos por segundo a m cubicos
	 * 
	 * @param q
	 *            caudal
	 * @param horas
	 *            horas de riego
	 * @param dias
	 *            dias de riego
	 * @return
	 */
	private double calcularCauadal(double q, double horas, int dias) {

		double caudal = q * horas * (double) dias * (double) 3600;

		return caudal;
	}

	@Override
	public List<Integer> buscarPredioIdRangoFecha(int year, short mes1, short mes2, int unidad) {

		return cultivoPredioRepo.buscarPredioIdRangoFecha(year, mes1, mes2, unidad);
	}

	@Override
	public List<CultivoPredio> buscarPorPredioIdRangoFecha(int id, int year, short mes1, short mes2) {

		return cultivoPredioRepo.buscarPorPredioIdRangoFecha(id, year, mes1, mes2);
	}

	@Override
	public List<Integer> buscarPredioIdRangoFechaUnidadId(int unidad, int year, short mes1, short mes2) {

		return cultivoPredioRepo.buscarPredioIdRangoFechaUnidadId(unidad, year, mes1, mes2);
	}

	@Override
	public List<Integer> buscarPredioIdRangoFechaZonaId(int zona, int year, short mes1, short mes2) {

		return cultivoPredioRepo.buscarPredioIdRangoFechaZonaId(zona, year, mes1, mes2);
	}

	@Override
	public List<Integer> buscarPredioIdRangoFechaSeccionId(int seccion, int year, short mes1, short mes2) {

		return cultivoPredioRepo.buscarPredioIdRangoFechaSeccionId(seccion, year, mes1, mes2);
	}

	@Override
	public List<Integer> buscarPredioIdRangoFechaCanalId(int canal, int year, short mes1, short mes2) {

		return cultivoPredioRepo.buscarPredioIdRangoFechaCanalId(canal, year, mes1, mes2);
	}

	@Override
	public double buscarHectareasPorPredioIdYFecha(int predio, LocalDate fecha) {

		/*
		 * consultamos el cultivo con mayor tiempo de gestacion para tomarlo como tiempo
		 * maximo en el que un predio puede estar de siembra y asi hacer poder asi tener
		 * un rango de tiempo para poder consultar las hectareas sembradas, le restamos
		 * uno porque hay que contar el mes actual
		 */
		int maxMes = cultivoService.maxMes() - 1;

		int year = fecha.getYear();
		short max = (short) fecha.getMonthValue();
		short min = (short) (max - maxMes);

		//System.out.println(year + " " + max + " " + min);

		Double hectareas = cultivoPredioRepo.buscarHectareasPorPredioIdYFecha(predio, year, min, max);

		return hectareas == null ? 0 : hectareas;
	}

}
