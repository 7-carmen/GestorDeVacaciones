package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Empleado;
import repositories.EmpleadoRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class EmpleadoService {

	// Managed repository -----------------------------------------------------

		@Autowired
		private EmpleadoRepository empleadoRepository;
		
		// Supporting services ----------------------------------------------------
		
		// Constructors -----------------------------------------------------------
		
		public EmpleadoService(){
			super();
		}
		
		// Simple CRUD methods ----------------------------------------------------
		
		public Empleado create() {
			Empleado result;
			
			result = new Empleado();
			
			return result;
		}
		
		public Empleado save(Empleado empleado) {
			Assert.notNull(empleado, "message.error.alert.notNull");
			
			empleado = empleadoRepository.save(empleado);
				
			return empleado;
		}
		
		public void delete(Empleado emplado) {
			Assert.notNull(emplado, "message.error.alert.notNull");
							
			empleadoRepository.delete(emplado);
		}
		
		public Collection<Empleado> findAll(){
			Collection<Empleado> result;
			
			result = empleadoRepository.findAll();
			
			return result;
		}
		
		public Empleado findOne(int emplado){
			Empleado result;
			
			result = empleadoRepository.findOne(emplado);
			
			return result;
		}
		
		// Other business methods -------------------------------------------------7
		
		/**
		 * Devuelve el user que está realizando la operación
		 */
		public Empleado findByPrincipal(){
			Empleado result;
			UserAccount userAccount;
			
			userAccount = LoginService.getPrincipal();
			Assert.notNull(userAccount);
			result = empleadoRepository.findByUserAccountId(userAccount.getId());
			Assert.notNull(result);
			
			return result;
		}
	
}
