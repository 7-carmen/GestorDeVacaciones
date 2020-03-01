/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.jefedepartamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.DiasPersonales;
import domain.Empleado;
import domain.Reservas;
import domain.Vacaciones;
import services.ActorService;
import services.DiasPersonalesService;
import services.EmpleadoService;
import services.ReservasService;
import services.VacacionesService;

@Controller
@RequestMapping("/jefedepartamento/reservas")
public class ReservasJefeDepartamentoController extends AbstractController {

	// Supporting services ----------------------------------------------------
	
	@Autowired
	VacacionesService vacacionesService;
	
	@Autowired
	DiasPersonalesService diasPersonalesService;
	
	@Autowired
	ActorService actorService;
	
	@Autowired
	EmpleadoService empleadoService;
	
	@Autowired
	ReservasService reservasService;
	
	// Constructors -----------------------------------------------------------
	
	public ReservasJefeDepartamentoController() {
		super();
	}
		
	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView result;
		Empleado jefedepartamento;
		List<Reservas> reservas;
	
		jefedepartamento = empleadoService.findOne(actorService.findByPrincipal().getId());
		reservas = new ArrayList<Reservas>();
		
		for(Empleado empleado: jefedepartamento.getDepartamento().getEmpleados()) {
			reservas.addAll(empleado.getReservas());
		}
		
		result = new ModelAndView("reservas/list");
		result.addObject("reservas", reservas);

		return result;
	}
	
	// Edit ------------------------------------------------------------------		

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam int idReserva, @RequestParam String accion) {
		Empleado empleado;
		Reservas reserva;
		Vacaciones vacaciones;
		DiasPersonales diasPersonales;
		
		reserva = reservasService.findOne(idReserva);
		empleado = reserva.getEmpleado();
		
		if(accion.equals("CONCEDER")) {
			reserva.setEstado("Concedidas");
			
			reservasService.save(reserva);
			
		} else if (accion.equals("DENEGAR")){
			reserva.setEstado("Denegadas");
			
			if(reserva.getTipo().equals("Vacaciones")) {
				vacaciones = empleado.getVacaciones();
				vacaciones.setDias_usados(vacaciones.getDias_usados()-1);
				vacacionesService.save(vacaciones);
			} else {
				diasPersonales = empleado.getDiasPersonales();
				diasPersonales.setDias_usados(diasPersonales.getDias_usados()-1);
				diasPersonalesService.save(diasPersonales);
			}
			
			reservasService.save(reserva);
		}
		
		return new ModelAndView("redirect:/jefedepartamento/reservas/list.do");
	}
}