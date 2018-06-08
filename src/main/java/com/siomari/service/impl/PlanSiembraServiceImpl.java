package com.siomari.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.PlanSiembra;
import com.siomari.repository.IPlanSiembraRepository;
import com.siomari.service.IPlanSiembraService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class PlanSiembraServiceImpl implements IPlanSiembraService {
	
	@Autowired
	private IPlanSiembraRepository planSiembraRepo;

	@Override
	public void registrar(PlanSiembra planSiembra) {
		
		if(planSiembra.getId() != 0) return;
		
		planSiembraRepo.save(planSiembra);
	}

	@Override
	public boolean existePorYearMesPeriodo(int year, short mes, short periodo) {
		
		boolean res = false;
		
		if(planSiembraRepo.buscarPorYearMesPeriodo(year, mes, periodo) != null) res = true;
		
		return res;
	}
	
	@Override
	public Integer buscarIdPorYearMesPeriodo(int year, short mes, short periodo) {
		
		Integer id = planSiembraRepo.buscarPorYearMesPeriodo(year, mes, periodo);
		
		// si no existe el plan siembra lo registramos  y obtenemos el id
		if(id == null) {
			PlanSiembra planSiembra = new PlanSiembra(year, mes, periodo);
			planSiembraRepo.save(planSiembra);
			id = planSiembra.getId();
		}
		
		return id;
	}

}
