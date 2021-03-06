package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository actorRepository;
	
	// Supporting services ----------------------------------------------------
	
	// Constructors -----------------------------------------------------------
	
	public ActorService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public Collection<Actor> findAll(){
		Collection<Actor> result;
		
		result = actorRepository.findAll();
		
		return result;
	}
	
	public Actor findOne(int actorId){
		Actor result;
		
		result = actorRepository.findOne(actorId);
		
		return result;
	}
	
	// Other business methods -------------------------------------------------7

	/**
	 *  Devuelve el actor que est� realizando la operaci�n
	 */
	public Actor findByPrincipal(){
		Actor result;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = actorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		
		return result;
	}

	/**
	 * Comprueba si el usuario que est� ejecutando tiene la Authority Solicitada
	 * @return boolean
	 * @param authority 
	 */
	public boolean checkAuthority(String authority){
		boolean result;
		Actor actor;
		Collection<Authority> authorities;
		result = false;

		try {
			actor = this.findByPrincipal();
			authorities = actor.getUserAccount().getAuthorities();
			
			for (Authority a : authorities) {
				if(a.getAuthority().equalsIgnoreCase(authority)){
					result = true;
					break;
				}
			}
		} catch (IllegalArgumentException e) {
			result = false;
		}
		return result;
	}
	
	/**
	 * Comprueba si un usuario est� autenticado
	 */
	public boolean checkLogin(){
		boolean result;
		
		result = true;
		
		try{
			this.findByPrincipal();
		} catch (Throwable e) {
			result = false;
		}
		return result;
	}	
}
