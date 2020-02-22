/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.Empleado;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController extends AbstractController {

	// Constructors -----------------------------------------------------------
	
	public EmpleadoController() {
		super();
	}
		
	// Index ---------------------------------------------------------------		

	@RequestMapping("/index")
	public ModelAndView action1() {
		ModelAndView result;
				
		result = new ModelAndView("empleado/index");
		
		return result;
	}
	
}