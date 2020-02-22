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

import domain.Vacaciones;
import services.VacacionesService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Supporting services ----------------------------------------------------
	
	@Autowired
	VacacionesService vacacionesService;
	
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
		List<Vacaciones> vacaciones;
		
		
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		vacaciones = new ArrayList<Vacaciones>(vacacionesService.findAll());
		
		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("moment", moment);
		result.addObject("vacaciones", vacaciones);

		return result;
	}
}