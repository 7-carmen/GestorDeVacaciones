package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import domain.DiasPersonales;
import domain.Empleado;
import domain.EmpleadoForm;
import domain.Vacaciones;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class EmpleadoFormService {

	// Supporting services ----------------------------------------------------

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private VacacionesService vacacionesService;

	@Autowired
	private DiasPersonalesService diasPersonalesService;
	
	@Autowired
	private DepartamentoService departamentoService;

	// Constructors -----------------------------------------------------------

	public EmpleadoFormService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public EmpleadoForm createForm() {
		EmpleadoForm result;

		result = new EmpleadoForm();

		return result;
	}

	public Empleado reconstruct(EmpleadoForm empleadoForm, BindingResult binding) {
		Boolean usernameExist = true;
		Empleado empleado;
		UserAccount userAccount;
		Vacaciones vacaciones;
		DiasPersonales diasPersonales;

		// Chequear nombre de usuario único

		try {
			loginService.loadUserByUsername(empleadoForm.getUsername());
		} catch (UsernameNotFoundException u) {
			usernameExist = false;
		}

		if (!usernameExist) {

			// UserAccount

			userAccount = new UserAccount();
			userAccount.setUsername(empleadoForm.getUsername());
			userAccount.setUsername(empleadoForm.getPassword());

			userAccount = userAccountService.encodePassword(userAccount);
			userAccountService.save(userAccount);

			// Vacaciones
			
			vacaciones = new Vacaciones();
			vacaciones.setDias_totales(empleadoForm.getVacaciones());
			vacaciones.setDias_usados(0);
			
			vacacionesService.save(vacaciones);
			
			//Dias Personales
			
			diasPersonales = new DiasPersonales();
			diasPersonales.setDias_totales(empleadoForm.getDiasPersonales());
			diasPersonales.setDias_usados(0);
			
			diasPersonalesService.save(diasPersonales);
			
			//Emleado
			
			empleado = new Empleado();
			empleado.setAnio_entrada(empleadoForm.getAnio_entrada());
			empleado.setNombre(empleadoForm.getNombre());
			empleado.setApellidos(empleadoForm.getApellidos());
			empleado.setCorreo(empleadoForm.getCorreo());
			empleado.setDireccion(empleadoForm.getDireccion());
			empleado.setTelefono(empleadoForm.getTelefono());
			
			empleado.setUserAccount(userAccount);
			empleado.setDepartamento(departamentoService.findOne(empleadoForm.getIdDepartamento()));
			empleado.setVacaciones(vacaciones);
			empleado.setdiasPersonales(diasPersonales);
			
			empleado = empleadoService.save(empleado);
			
		} else {
			empleado = null;
		}
		return null;

	}
}