/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.jefedepartamento;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Departamento;
import domain.Empleado;
import domain.EmpleadoForm;
import services.DepartamentoService;
import services.EmpleadoFormService;
import services.EmpleadoService;

@Controller
@RequestMapping("/jefedepartamento/empleado")
public class EmpleadoController extends AbstractController {
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	EmpleadoService empleadoService;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@Autowired
	EmpleadoFormService empleadoFormService;
	
	
	// Constructors -----------------------------------------------------------
	
	public EmpleadoController() {
		super();
	}
		
	// Create ---------------------------------------------------------------		

	@RequestMapping("/create")
	public ModelAndView create() {
		ModelAndView result;
		EmpleadoForm empleadoForm;
		List<Departamento> departamentos;
		
		empleadoForm = empleadoFormService.createForm();
		departamentos = new ArrayList<Departamento>(departamentoService.findAll());
		
		result = new ModelAndView("empleado/create");
		result.addObject("empleadoForm", empleadoForm);
		result.addObject("departamentos", departamentos);
		
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid EmpleadoForm empleadoForm, BindingResult binding) {
		ModelAndView result;
		List<Departamento> departamentos;
		Empleado empleado;
		
		departamentos = new ArrayList<Departamento>(departamentoService.findAll());
		
		if (binding.hasErrors()) {
			
			result = new ModelAndView("empleado/create");
			result.addObject("empleadoForm", empleadoForm);
			result.addObject("departamentos", departamentos);
			
		} else {
			try {
				empleado = empleadoFormService.reconstruct(empleadoForm, binding);
				
				result = new ModelAndView("empleado/verification");
				
				if(empleado!=null) {
					result.addObject("title", "empleado.creado");
				}else {
					result.addObject("title", "empleado.duplicado");
				}
			} catch (Throwable oops) {
				System.out.println(oops.getMessage());

				result = new ModelAndView("empleado/verification");
				result.addObject("title", "empleado.errorInterno");
			}
		}

		return result;
	}
}