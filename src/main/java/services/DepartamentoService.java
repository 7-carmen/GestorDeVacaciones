package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Departamento;
import repositories.DepartamentoRepository;

@Service
@Transactional
public class DepartamentoService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	// Supporting services ----------------------------------------------------
	
	// Constructors -----------------------------------------------------------
	
	public DepartamentoService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public Departamento create() {
		Departamento result;
		
		result = new Departamento();
		
		return result;
	}
	
	public Departamento save(Departamento departamento) {
		Assert.notNull(departamento, "message.error.alert.notNull");
		
		departamento = departamentoRepository.save(departamento);
			
		return departamento;
	}
	
	public void delete(Departamento departamento) {
		Assert.notNull(departamento, "message.error.alert.notNull");
						
		departamentoRepository.delete(departamento);
	}
	
	public Collection<Departamento> findAll(){
		Collection<Departamento> result;
		
		result = departamentoRepository.findAll();
		
		return result;
	}
	
	public Departamento findOne(int departamentoId){
		Departamento result;
		
		result = departamentoRepository.findOne(departamentoId);
		
		return result;
	}
	
	// Other business methods -------------------------------------------------7

}

