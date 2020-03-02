package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import domain.DiasPersonales;
import domain.Empleado;
import domain.EmpleadoForm;
import domain.Vacaciones;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class EmpleadoFormService {

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private VacacionesService vacacionesService;

	@Autowired
	private DiasPersonalesService diasPersonalesService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private EmpleadoService empleadoService;


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
		List<Authority> authorities;
		Authority authority;
		Vacaciones vacaciones;
		DiasPersonales diasPersonales;

		// Chequear nombre de usuario único
		
		if(userAccountService.findByUserNameWithoutAsserts(empleadoForm.getUsername())==null) {
			usernameExist = false;
		}

		if (!usernameExist) {

			// UserAccount

			userAccount = new UserAccount();
			userAccount.setUsername(empleadoForm.getUsername());
			userAccount.setPassword(empleadoForm.getPassword());
			
			authorities = new ArrayList<Authority>();
			authority = new Authority();
			authority.setAuthority("EMPLEADO");
			authorities.add(authority);
			
			userAccount.setAuthorities(authorities);
			
			userAccount = userAccountService.encodePassword(userAccount);
			userAccount = userAccountService.save(userAccount);

			// Vacaciones
			
			vacaciones = new Vacaciones();
			vacaciones.setDias_totales(empleadoForm.getVacaciones());
			vacaciones.setDias_usados(0);
			
			vacaciones = vacacionesService.save(vacaciones);
			
			//Dias Personales
			
			diasPersonales = new DiasPersonales();
			diasPersonales.setDias_totales(empleadoForm.getDiasPersonales());
			diasPersonales.setDias_usados(0);
			
			diasPersonales = diasPersonalesService.save(diasPersonales);
			
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
		
		return empleado;

	}
}