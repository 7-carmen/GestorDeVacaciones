package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.DiasPersonales;
import repositories.DiasPersonalesRepository;

@Service
@Transactional
public class DiasPersonalesService {

	// Managed repository -----------------------------------------------------

		@Autowired
		private DiasPersonalesRepository diasPersonalesRepository;
		
		// Supporting services ----------------------------------------------------
		
		// Constructors -----------------------------------------------------------
		
		public DiasPersonalesService(){
			super();
		}
		
		// Simple CRUD methods ----------------------------------------------------
		
		public DiasPersonales create() {
			DiasPersonales result;
			
			result = new DiasPersonales();
			
			return result;
		}
		
		public DiasPersonales save(DiasPersonales diasPersonales) {
			Assert.notNull(diasPersonales, "message.error.alert.notNull");
			
			diasPersonales = diasPersonalesRepository.save(diasPersonales);
				
			return diasPersonales;
		}
		
		public void delete(DiasPersonales diasPersonales) {
			Assert.notNull(diasPersonales, "message.error.alert.notNull");
							
			diasPersonalesRepository.delete(diasPersonales);
		}
		
		public Collection<DiasPersonales> findAll(){
			Collection<DiasPersonales> result;
			
			result = diasPersonalesRepository.findAll();
			
			return result;
		}
		
		public DiasPersonales findOne(int diasPersonalesId){
			DiasPersonales result;
			
			result = diasPersonalesRepository.findOne(diasPersonalesId);
			
			return result;
		}
		
		// Other business methods -------------------------------------------------7

}
