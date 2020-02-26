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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		
	// Index ------------------------------------------------------------------		

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
}