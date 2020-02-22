/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Empleado;
import domain.Reservas;
import domain.Vacaciones;
import services.ActorService;
import services.EmpleadoService;
import services.VacacionesService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Supporting services ----------------------------------------------------
	
	@Autowired
	VacacionesService vacacionesService;
	
	@Autowired
	ActorService actorService;
	
	@Autowired
	EmpleadoService empleadoService;
	
	// Constructors -----------------------------------------------------------
	
	public WelcomeController() {
		super();
	}
		
	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required=false, defaultValue="John Doe") String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		Empleado empleado;
		Vacaciones vacaciones;
		List<Reservas> reservas;
		
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		
		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("moment", moment);
		
		if(actorService.checkLogin()) {
			
			empleado = empleadoService.findOne(actorService.findByPrincipal().getId());
			vacaciones = empleado.getVacaciones();
			reservas = new ArrayList<Reservas>(empleado.getReservas());
			
			result.addObject("vacaciones", vacaciones);
			result.addObject("reservas", reservas);
			result.addObject("empleado", empleado);
		}

		return result;
	}
}