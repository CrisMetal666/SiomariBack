package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.EstructuraControl;
import com.siomari.repository.IEstructuraControlRepository;
import com.siomari.service.IEstructuraControlService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class EstructuraControlServiceImpl implements IEstructuraControlService {

	@Autowired
	private IEstructuraControlRepository estructuraControlRepo;

	@Override
	public EstructuraControl registrar(EstructuraControl estructuraControl) {

		return estructuraControlRepo.save(estructuraControl);
	}

	@Override
	public EstructuraControl actualizar(EstructuraControl estructuraControl) {

		return estructuraControlRepo.save(estructuraControl);
	}

	@Override
	public EstructuraControl listarPorCodigo(String codigo) {

		EstructuraControl estructuraControl = estructuraControlRepo.listarPorCodigo(codigo);

		if (estructuraControl == null) {
			estructuraControl = new EstructuraControl();
		}

		return estructuraControl;
	}

	@Override
	public EstructuraControl calibrar(EstructuraControl estructuraControl) {

		// usamos el metodo de regresion potencial para calcular la ecuacion
		double ecuacion[] = this.metodoRegresionPotencial(estructuraControl.getX(), estructuraControl.getY());

		// asignamos los valores calculados al objeto para ser persistidos
		estructuraControl.setCoeficiente(ecuacion[0]);
		estructuraControl.setExponente(ecuacion[1]);

		estructuraControlRepo.save(estructuraControl);

		return estructuraControl;
	}

	/**
	 * se calculara la ecuacion de una potencia (y = Ax^B) segun los puntos enviados
	 * 
	 * @param x
	 *            puntos correspondientes al eje x
	 * @param y
	 *            puntos correspondientes al eje y
	 * @return posicion 0 = A, posicion 1 = B
	 */
	private double[] metodoRegresionPotencial(double x[], double y[]) {

		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumX2 = 0;
		int n = x.length;

		for (int i = 0; i < n; i++) {

			sumX += Math.log10(x[i]);
			sumY += Math.log10(y[i]);
			sumXY += Math.log10(x[i]) * Math.log10(y[i]);
			sumX2 += Math.pow(Math.log10(x[i]), 2);

		}

		double b = (n * sumXY - sumX * sumY) / (n * sumX2 - Math.pow(sumX, 2));
		double aPrima = (sumY / n) - b * (sumX / n);

		double a = Math.pow(10, aPrima);

		return new double[] { a, b };
	}

	@Override
	public List<EstructuraControl> buscarPorCodigo(String codigo) {
		
		return estructuraControlRepo.buscarPorCodigo(codigo);
	}

	@Override
	public EstructuraControl buscarIdPorCodigo(String codigo) {
		
		EstructuraControl estructuraControl = estructuraControlRepo.buscarIdPorCodigo(codigo);
		
		if(estructuraControl == null) {
			estructuraControl = new EstructuraControl();
		}
		
		return estructuraControl;
	}

	@Override
	public List<EstructuraControl> buscarCodigoCoeficienteExponentePorCodigo(String codigo) {
		
		return estructuraControlRepo.buscarCodigoCoeficienteExponentePorCodigo(codigo);
	}

}
