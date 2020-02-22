package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Empleado extends Actor {
	
	//RELACIONES
	//Relaci�n con Departamento: 
	private Departamento departamento;
	
	@Valid
	@ManyToOne(optional=false)
	public Departamento getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	//Relaci�n con Vacaciones:
	private Vacaciones vacaciones;
	
	@Valid
	@OneToOne
	public Vacaciones getVacaciones() {
		return vacaciones;
	}
	
	public void setVacaciones(Vacaciones vacaciones) {
		this.vacaciones = vacaciones; 
	}
	
	
	//Relaci�n con Dias propios: 
	private DiasPersonales diasPersonales;
	
	@Valid
	@OneToOne
	public DiasPersonales getDiasPersonales() {
		return diasPersonales;
	}
	
	public void setdiasPersonales(DiasPersonales diasPersonales) {
		this.diasPersonales = diasPersonales; 
	}
	
	//Relaci�n con Reservas: 
	private Collection<Reservas> reservas;
	
	@Valid
	@OneToMany
	public Collection<Reservas> getReservas() {
		return reservas;
	}
	
	public void setReservas(Collection<Reservas> reservas) {
		this.reservas = reservas; 
	}
}
