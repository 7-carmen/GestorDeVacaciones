/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.empleado;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.DiasPersonales;
import domain.Reservas;
import domain.Vacaciones;
import services.DiasPersonalesService;
import services.ReservasService;
import services.VacacionesService;

@Controller
@RequestMapping("/reservas")
public class ReservasController extends AbstractController {

  // Supporting services ----------------------------------------------------

  @Autowired
  ReservasService reservasService;

  @Autowired
  VacacionesService vacacionesService;

  @Autowired
  DiasPersonalesService diasPersonalesService;

  // Constructors -----------------------------------------------------------

  public ReservasController() {
    super();
  }

  // Create ------------------------------------------------------------------    

  @RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
  public ModelAndView save(@Valid Reservas reserva, BindingResult binding) {
    ModelAndView result;
    String fecha;
    Vacaciones vacaciones;
    DiasPersonales diasPersonales;

    if (binding.hasErrors()) {
      System.out.println(binding);
      result = null;
    } else {
      try {
        fecha = reserva.getFecha().substring(8, 10)+"/"+reserva.getFecha().substring(5, 7)+"/"+reserva.getFecha().substring(0, 4);
        vacaciones = reserva.getEmpleado().getVacaciones();
        diasPersonales = reserva.getEmpleado().getDiasPersonales();

        reserva.setFecha(fecha);
        reserva = reservasService.save(reserva);

        if(reserva.getTipo().equals("Vacaciones")) {
          vacaciones.setDias_usados(vacaciones.getDias_usados()+1);
          vacacionesService.save(vacaciones);
        }else {
          diasPersonales.setDias_usados(diasPersonales.getDias_usados()+1);
          diasPersonalesService.save(diasPersonales);
        }


        result = new ModelAndView("redirect:/");
      } catch (Throwable oops) {
        System.out.println(oops);
        result = null;
      }
    }

    return result;
  }
}