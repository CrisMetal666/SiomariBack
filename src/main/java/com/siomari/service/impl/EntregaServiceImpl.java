package com.siomari.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.CultivoPredio;
import com.siomari.model.Entrega;
import com.siomari.model.ManejoAgua;
import com.siomari.model.dto.DistribucionAgua;
import com.siomari.model.dto.EntregaInfo;
import com.siomari.model.dto.Facturacion;
import com.siomari.repository.IEntregaRepository;
import com.siomari.service.IConfigService;
import com.siomari.service.ICultivoPredioService;
import com.siomari.service.ICultivoService;
import com.siomari.service.IEntregaService;
import com.siomari.service.IManejoAguaService;
import com.siomari.service.IPredioService;
import com.siomari.service.IUsuarioService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class EntregaServiceImpl implements IEntregaService {

	@Autowired
	private IEntregaRepository entregaRepo;

	@Autowired
	private IPredioService predioService;

	@Autowired
	private ICultivoService cultivoService;

	@Autowired
	private ICultivoPredioService cultivoPredioService;

	@Autowired
	private IConfigService configService;

	@Autowired
	private IManejoAguaService manejoAguaService;
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public Entrega registrar(Entrega entrega) {

		return entregaRepo.save(entrega);
	}

	@Override
	public Facturacion caudalServidoPorRangoFecha(String txtInicio, String txtFin, int predio) {

		// convertimos la fecha en string a localdate
		LocalDate inicio = LocalDate.parse(txtInicio, DateTimeFormatter.ISO_DATE);
		LocalDate fin = LocalDate.parse(txtFin, DateTimeFormatter.ISO_DATE);

		/*
		 * como en la base de datos tenemos un datetime, debemos asignaerle los minutos
		 * a las fechas para poder hacer la consulta correctamente
		 */
		LocalTime tiempo1 = LocalTime.of(0, 0, 0);
		LocalTime tiempo2 = LocalTime.of(23, 59, 59);

		// createmos los locaDateTime
		LocalDateTime inferior = LocalDateTime.of(inicio, tiempo1);
		LocalDateTime superior = LocalDateTime.of(fin, tiempo2);

		// traemos la data
		List<Entrega> lst = entregaRepo.buscarEntregaSuspensionPorRangoFechas(inferior, superior, predio);

		// sumatoria de los segundos
		long sumSeconds = 0;
		
		// objeto principal que almacenara la informacion
		Facturacion facturacion = new Facturacion();
		
		// traemos el nombre del predio y del usuario
		String nombrePredio = predioService.buscarNombrePorId(predio);
		String nombreUsuario = usuarioService.buscarNombrePorPredioId(predio);
		
		facturacion.setNombrePredio(nombrePredio);
		facturacion.setNombreUsuario(nombreUsuario == null ? "Sin definir" : nombreUsuario);

		// obejto donde armaremos el json
		List<EntregaInfo> entregas = new ArrayList<>();

		// obtenemos el modulo de riego para calcular el caudal servido
		double moduloRiego = predioService.listarModuloRiegoPorId(predio);

		// valor del metro cubico
		double valor = configService.getCosto();

		for (Entrega e : lst) {

			// tomamos los segundos que hay entre las dos fechas
			long segundos = Math.abs(e.getEntrega().until(e.getSuspension(), ChronoUnit.SECONDS));

			// metros cubicos servidos
			double m3 = segundos * moduloRiego / 1000;

			// costo del agua servida
			double costo = m3 * valor;

			// Le damos el formato deseado a la fecha
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy h:m a");
			// rango de fecha en string
			String fecha = e.getEntrega().format(formato) + " - " + e.getSuspension().format(formato);

			// armamos el objeto para adicionarlo a la lista
			EntregaInfo entrega = new EntregaInfo();

			entrega.setFecha(fecha);
			entrega.setCosto(costo);
			entrega.setM3(m3);

			entregas.add(entrega);

			// sumamos los segundos para hacer el total
			sumSeconds += segundos;
		}

		// total
		if (sumSeconds != 0) {
			double m3 = sumSeconds * moduloRiego / 1000;
			double costo = m3 * valor;

			EntregaInfo entrega = new EntregaInfo();

			entrega.setCosto(costo);
			entrega.setM3(m3);

			entregas.add(entrega);
		}
		
		// agregamos la lista con el agua entregada
		facturacion.setLstEntregaInfo(entregas);

		return facturacion;
	}

	@Override
	public List<DistribucionAgua> distribucionDeAgua(int tipo, int id, String txtFecha) {

		LocalDate fecha = LocalDate.parse(txtFecha, DateTimeFormatter.ISO_DATE);

		/*
		 * consultamos cual es la cantidad de meses de gestacion de los cultivos
		 * registrados para hacer la consulta del intervalo de tiempo de la fecha que
		 * llega por parametro a la cantidad de meses de gestacion atras
		 */
		int mesesGestacionMayor = cultivoService.maxMes();

		/*
		 * obtenemos el año y mes de la consulta y restamos los meses de gestacion para
		 * obtener el rango por el cual haremos la consulta
		 */
		int year = fecha.getYear();
		short mes2 = (short) fecha.getMonthValue();
		short mes1 = (short) (mes2 - mesesGestacionMayor + 1);

		// traemos el id de los predios que sembraron durante ese rango de tiempo
		List<Integer> lstPredioId = cultivoPredioService.buscarPredioIdRangoFecha(year, mes1, mes2);

		if (lstPredioId.size() == 0)
			return new ArrayList<>();

		/*
		 * necesitamos sumar los caudales servidos por cultivo, asi que, la clave del
		 * map sera el id el cultivo para asi poder identificar los diferentes cultivos
		 * e ir sumando sus caudales
		 */
		Map<Integer, DistribucionAgua> sumCaudalServido = new HashMap<>();

		for (Integer item : lstPredioId) {

			List<CultivoPredio> lstCultivoPredio = cultivoPredioService.buscarPorPredioIdRangoFecha(item, year, mes1,
					mes2);

			// sumCaudalServido =
			this.calcularCaudalServidoPorCultivos(lstCultivoPredio, year, mes1, mes2, sumCaudalServido, item);
		}

		// creamos la lista para poder dejar el total al final del array
		List<DistribucionAgua> lst = new ArrayList<>();

		for (Integer x : sumCaudalServido.keySet()) {

			/*
			 * evitamos agregar a la lista el key -1, el cual indica que es el total, para
			 * poder dejarlo en ultimo lugar
			 */
			if (x != -1) {
				lst.add(sumCaudalServido.get(x));
			}
		}

		DistribucionAgua total = sumCaudalServido.get(-1);

		// si el total es nulo significa que no hay informacion que procesar
		if (total == null)
			return new ArrayList<>();

		this.caudalDerivado(total, mes1, mes2, year);

		this.calcularDerivadosLaminasEficiencia(lst, total);

		lst.add(total);

		return lst;
	}

	/**
	 * se calculara los caudales derivdos por cultivos, laminas netas y brutas,
	 * eficiencia
	 * 
	 * @param lst
	 *            lista por cultivo con los caudales servidos, superficie
	 * @param total
	 *            total de superficie, caudales servidos y derivados
	 */
	private void calcularDerivadosLaminasEficiencia(List<DistribucionAgua> lst, DistribucionAgua total) {

		if (lst.size() == 0)
			return;

		/*
		 * para los totales de las laminas netas, brutas y las eficiencias se necesitan
		 * sacar los promedios
		 */
		double netaMes = 0;
		double netaAcumu = 0;
		double brutaMes = 0;
		double brutaAcumu = 0;
		double eficMes = 0;
		double eficAcumu = 0;

		for (DistribucionAgua item : lst) {

			/*
			 * en todas las operaciones que se deban dividir, nos aseguraremos que el
			 * divisor no sea 0
			 */

			// calculo derivado mes
			if (total.getCaudalServidoMes() != 0) {
				double derivadoMes = total.getCaudalDerivadoMes() * item.getCaudalServidoMes()
						/ total.getCaudalServidoMes();
				item.setCaudalDerivadoMes(derivadoMes);
			}

			// calculo derivado acumulado
			if (total.getCaudalServidoAcumulado() != 0) {
				double derivadoAcumulado = total.getCaudalDerivadoAcumulado() * item.getCaudalServidoAcumulado()
						/ total.getCaudalServidoAcumulado();
				item.setCaudalDerivadoAcumulado(derivadoAcumulado);
			}

			if (item.getSuperficieMes() != 0) {

				double nMes = item.getCaudalServidoMes() / item.getSuperficieMes();
				item.setLaminaNetaMes(nMes);

				double bMes = item.getCaudalDerivadoMes() / item.getSuperficieMes();
				item.setLaminaBrutaMes(bMes);

				// sumamos para calcular el total
				netaMes += nMes;
				brutaMes += bMes;
			}

			if (item.getSuperficieFisica() != 0) {

				double nAcumu = item.getCaudalServidoAcumulado() / item.getSuperficieFisica();
				item.setLaminaNetaAcumulado(nAcumu);

				double bAcumu = item.getCaudalDerivadoAcumulado() / item.getSuperficieFisica();
				item.setLaminaBrutaAcumulado(bAcumu);

				// sumamos para calcular el total
				netaAcumu += nAcumu;
				brutaAcumu += bAcumu;
			}

			if (item.getCaudalDerivadoMes() != 0) {

				double efiMes = item.getCaudalServidoMes() / item.getCaudalDerivadoMes();
				item.setEficienciaMes(efiMes);

				// sumamos para calcular el total
				eficMes += efiMes;
			}

			if (item.getCaudalDerivadoAcumulado() != 0) {

				double efiAcumu = item.getCaudalServidoAcumulado() / item.getCaudalDerivadoAcumulado();
				item.setEficienciaAcumulado(efiAcumu);

				eficAcumu += efiAcumu;
			}

		}

		// obtenemos el tamaño de la lista para calcular los promedios para el total
		int n = lst.size();

		total.setLaminaNetaMes(netaMes / n);
		total.setLaminaNetaAcumulado(netaAcumu / n);
		total.setLaminaBrutaMes(brutaMes / n);
		total.setLaminaBrutaAcumulado(brutaAcumu / n);
		total.setEficienciaMes(eficMes / n);
		total.setEficienciaAcumulado(eficAcumu / n);
	}

	/**
	 * se calculara el caudal derivado y se le asigara el valor al objeto
	 * 
	 * @param total
	 *            objeto al cual se le asignara el caudal derivado
	 * @param mes1
	 *            mes inferior para hacer el filtrado
	 * @param mes2
	 *            mes superior para hacer el filtrado
	 * @param año
	 *            para hacer el filtrado
	 */
	private void caudalDerivado(DistribucionAgua total, short mes1, short mes2, int year) {

		int horasRiego = configService.getHorasRiego();

		int derivadoMes = 0;
		int derivadoAcumulado = 0;

		// obtenemos el caudal servido acumulado
		List<ManejoAgua> lstAcumulado = manejoAguaService.buscarServidoAreaPorMesMenor(mes1, mes2 - 1, year);

		// calculamos el volumen
		for (ManejoAgua x : lstAcumulado) {

			double caudal = 60d * 60d * horasRiego * x.getqServido() * x.getArea() * 10000d;

			derivadoAcumulado += caudal;
		}

		// obtenemos el caudal del mes
		List<ManejoAgua> lstMes = manejoAguaService.buscarServidoAreaPorMesIgual(mes2, year);

		// calculamos el volumen
		for (ManejoAgua x : lstMes) {

			double caudal = 60d * 60d * horasRiego * x.getqServido() * x.getArea() * 10000d;

			derivadoMes += caudal;
		}

		total.setCaudalDerivadoAcumulado(derivadoAcumulado);
		total.setCaudalDerivadoMes(derivadoMes);

	}

	/**
	 * se calculara los caudales servidos a cada cultivo segun las entregas que se
	 * hayan registrado
	 * 
	 * @param lstCultivoPredio
	 *            lista del plan de siembra que contiene la informacion necesaria
	 *            para los calculos
	 * @param year
	 *            año que se usara para el filtradp
	 * @param mes1
	 *            mes inferior para el filtrado
	 * @param mes2
	 *            mes superior para el filtrado
	 * @param sumCaudalServido
	 *            referencia a memoria del map que contiene la suma de caudalaes por
	 *            cultivos
	 * @param id
	 *            id del predio
	 */
	private void calcularCaudalServidoPorCultivos(List<CultivoPredio> lstCultivoPredio, int year, short mes1,
			short mes2, Map<Integer, DistribucionAgua> sumCaudalServido, int id) {

		// obtenemos el mes en el que empezo el plan de siembra
		short mes = lstCultivoPredio.get(0).getPlanSiembraId().getMes();
		// obtenemos los meses de gestacion del cultivo
		int mesesGestacion = lstCultivoPredio.get(0).getCultivoId().getMeses();
		// obetenemos el id del cultivo
		int idCultivo = lstCultivoPredio.get(0).getCultivoId().getId();
		// modulo de riego del predio
		double moduloRiego = lstCultivoPredio.get(0).getPredioId().getModuloRiego();

		/*
		 * creamos una array que almacene las hectareas, el mes que empieza la siembra y
		 * cuando acaba
		 */
		double data[][] = new double[lstCultivoPredio.size()][3];
		// hectareas que se tienen sembradas
		double hectareasFisicas = 0;

		/*
		 * rellenamos el array con las hectareas en la primera posicion, inicio de
		 * siembra en la segunda y fin de la siembra en la tercera posicion
		 */
		for (int i = 0; i < lstCultivoPredio.size(); i++) {

			CultivoPredio cp = lstCultivoPredio.get(i);

			data[i][0] = cp.getHectareas();
			data[i][1] = cp.getPlanSiembraId().getMes();
			data[i][2] = data[i][1] - 1 + mesesGestacion;

			// sumamos las hectareas que se tienen sembradas
			hectareasFisicas += cp.getHectareas();
		}

		// creamos los obejos localdate con los valores enviados
		LocalDateTime fecha1 = LocalDateTime.of(year, mes, 1, 0, 0);
		// obtenemos la fecha final del mes
		LocalDateTime fecha2 = LocalDateTime.of(year, mes2 + 1, 1, 0, 0).plusMinutes(-1);

		/*
		 * obtenemos el agua servida desde el plan de siempra a la fecha en que se hizo
		 * la consulta
		 */
		List<Entrega> lstEntregas = entregaRepo.buscarEntregaSuspensionPorRangoFechas(fecha1, fecha2, id);

		if (lstEntregas.size() == 0)
			return;

		/*
		 * creamos la fecha del mes actual para hacer mas faciles las condiciones y
		 * poder calcular los caudales servidos acumulados y los del mes
		 */
		LocalDateTime fecha = LocalDateTime.of(year, mes2, 1, 0, 0);
		// sumaremos las hectareas que se riegen
		double hectareasRegadas = 0;
		// caudales servidos acumulado
		double caudalAcumulado = 0;
		// caudal servido en el mes
		double caudal = 0;

		// por ahora asumiremos que siembran el mismo cultivo
		for (Entrega entrega : lstEntregas) {

			LocalDateTime f1 = entrega.getEntrega(); // obtenemos el inicio del riego
			LocalDateTime f2 = entrega.getSuspension(); // obtenemos el corte del riego

			/*
			 * Tenemos tres posibilidades que tenemos que evaluar y asi mismo actuamos en
			 * sumar los caudales acumulados o los del mes
			 */
			if (f1.isBefore(fecha) && f2.isBefore(fecha)) {

				/*
				 * sumaremos las hectareas que se regaran
				 */
				double hectareas = 0;

				for (double d[] : data) {
					/*
					 * nos aseguramos que el cultivo sembrado este dentro del rango de cosecha para
					 * asi contar sus hectareas para el calculo de caudal servido
					 */
					if (f1.getMonthValue() >= d[1] && f1.getMonthValue() <= d[2]) {
						hectareas += d[0];
					}
				}

				// obtenemos los segundos de riego
				long segundos = Math.abs(f1.until(f2, ChronoUnit.SECONDS));

				// obtenemos el caudal servido en el tiempo de riego
				double servido = hectareas * 10000 * segundos * moduloRiego / 1000d;

				// sumamos el caudal servido
				caudalAcumulado += servido;

			} else if (f1.isBefore(fecha) && (f2.isAfter(fecha) || f2.isEqual(fecha))) {

				/*
				 * sumaremos las hectareas que se regaran
				 */
				double hectareas = 0;

				for (double d[] : data) {
					/*
					 * nos aseguramos que el cultivo sembrado este dentro del rango de cosecha para
					 * asi contar sus hectareas para el calculo de caudal servido
					 */
					if (f1.getMonthValue() >= d[1] && f1.getMonthValue() <= d[2]) {
						hectareas += d[0];
					}
				}
				/*
				 * como la fecha en que se empezo a regar es anterior a la fecha actual, debemos
				 * separar los dos riegos para los acumulados y los del mes
				 */
				LocalDateTime fFin = LocalDateTime.of(year, f1.getMonthValue(), 23, 59, 59);
				LocalDateTime fInicio = LocalDateTime.of(year, f2.getMonthValue(), 0, 0, 0);

				// segundos de riego acumulados
				long sAcumulados = Math.abs(f1.until(fFin, ChronoUnit.SECONDS));

				// segundos de riego en el mes
				long sMes = Math.abs(fInicio.until(f2, ChronoUnit.SECONDS));

				// obtenemos el caudal servido en el tiempo de riego
				double cAcumulado = hectareas * 10000 * sAcumulados * moduloRiego / 1000d;
				double cMes = hectareas * 10000 * sMes * moduloRiego / 1000d;

				// sumamos las hectareas regadas
				hectareasRegadas += hectareas;
				// sumamos el caudal servido
				caudalAcumulado += cAcumulado;
				caudal += cMes;

			} else if ((f1.isAfter(fecha) || f1.isEqual(fecha)) && (f2.isAfter(fecha) || f2.isEqual(fecha))) {

				/*
				 * sumaremos las hectareas que se regaran
				 */
				double hectareas = 0;

				for (double d[] : data) {
					/*
					 * nos aseguramos que el cultivo sembrado este dentro del rango de cosecha para
					 * asi contar sus hectareas para el calculo de caudal servido
					 */
					if (f1.getMonthValue() >= d[1] && f1.getMonthValue() <= d[2]) {
						hectareas += d[0];
					}
				}

				// obtenemos los segundos de riego
				long segundos = Math.abs(f1.until(f2, ChronoUnit.SECONDS));

				// obtenemos el caudal servido en el tiempo de riego
				double servido = hectareas * 10000 * segundos * moduloRiego / 1000d;

				// sumamos el caudal servido
				caudal += servido;
				// sumamos las hectareas
				hectareasRegadas += hectareas;

			}

		}

		/*
		 * ahora los datos calculados los ingresamos al map que se ha enviado por
		 * parametro, la llave sera el id del cultivo
		 */
		DistribucionAgua distribucionAgua = sumCaudalServido.get(idCultivo);

		// si esta nulo le asignamos el nombre del cultivo
		if (distribucionAgua == null) {

			distribucionAgua = new DistribucionAgua();
			distribucionAgua.setCultivo(lstCultivoPredio.get(0).getCultivoId().getNombre());

		}

		// pasamos la informacion al objeto del map
		distribucionAgua.setSuperficieFisica(distribucionAgua.getSuperficieFisica() + hectareasFisicas);
		distribucionAgua.setSuperficieMes(distribucionAgua.getSuperficieMes() + hectareasRegadas);
		distribucionAgua.setCaudalServidoAcumulado(distribucionAgua.getCaudalServidoAcumulado() + caudalAcumulado);
		distribucionAgua.setCaudalServidoMes(distribucionAgua.getCaudalServidoMes() + caudal);

		sumCaudalServido.put(idCultivo, distribucionAgua);

		// en el map habra un objeto que ira sumando para llevar el total
		DistribucionAgua total = sumCaudalServido.get(-1);

		if (total == null)
			total = new DistribucionAgua();

		total.setSuperficieFisica(total.getSuperficieFisica() + hectareasFisicas);
		total.setSuperficieMes(total.getSuperficieMes() + hectareasRegadas);
		total.setCaudalServidoAcumulado(total.getCaudalServidoAcumulado() + caudalAcumulado);
		total.setCaudalServidoMes(total.getCaudalServidoMes() + caudal);

		sumCaudalServido.put(-1, total);

	}

}
