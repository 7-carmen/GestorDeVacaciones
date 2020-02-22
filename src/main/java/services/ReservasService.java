package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Reservas;
import repositories.ReservasRepository;

@Service
@Transactional

public class ReservasService {;
	// Managed repository -----------------------------------------------------

		@Autowired
		private ReservasRepository reservasRepository;
		
		// Supporting services ----------------------------------------------------
		
		// Constructors -----------------------------------------------------------
		
		public ReservasService(){
			super();
		}
		
		// Simple CRUD methods ----------------------------------------------------
		
		public Reservas create() {
			Reservas result;
			
			result = new Reservas();
			
			return result;
		}
		
		public Reservas save(Reservas reservas) {
			Assert.notNull(reservas, "message.error.alert.notNull");
			
			reservas = reservasRepository.save(reservas);
				
			return reservas;
		}
		
		public void delete(Reservas reservas) {
			Assert.notNull(reservas, "message.error.alert.notNull");
							
			reservasRepository.delete(reservas);
		}
		
		public Collection<Reservas> findAll(){
			Collection<Reservas> result;
			
			result = reservasRepository.findAll();
			
			return result;
		}
		
		public Reservas findOne(int reservas){
			Reservas result;
			
			result = reservasRepository.findOne(reservas);
			
			return result;
		}
		
		// Other business methods -------------------------------------------------7

}
