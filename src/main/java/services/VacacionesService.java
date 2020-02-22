package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Vacaciones;
import repositories.VacacionesRepository;

@Service
@Transactional
public class VacacionesService {
	// Managed repository -----------------------------------------------------

			@Autowired
			private VacacionesRepository vacacionesRepository;
			
			// Supporting services ----------------------------------------------------
			
			// Constructors -----------------------------------------------------------
			
			public VacacionesService(){
				super();
			}
			
			// Simple CRUD methods ----------------------------------------------------
			
			public Vacaciones create() {
				Vacaciones result;
				
				result = new Vacaciones();
				
				return result;
			}
			
			public Vacaciones save(Vacaciones vacaciones) {
				Assert.notNull(vacaciones, "message.error.alert.notNull");
				
				vacaciones = vacacionesRepository.save(vacaciones);
					
				return vacaciones;
			}
			
			public void delete(Vacaciones vacaciones) {
				Assert.notNull(vacaciones, "message.error.alert.notNull");
								
				vacacionesRepository.delete(vacaciones);
			}
			
			public Collection<Vacaciones> findAll(){
				Collection<Vacaciones> result;
				
				result = vacacionesRepository.findAll();
				
				return result;
			}
			
			public Vacaciones findOne(int vacaciones){
				Vacaciones result;
				
				result = vacacionesRepository.findOne(vacaciones);
				
				return result;
			}
			
			// Other business methods -------------------------------------------------7
}
