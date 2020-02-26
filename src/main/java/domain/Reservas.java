package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Reservas extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String tipo;
	private String fecha;
	private String estado;

	@NotNull
	@NotBlank
	public String getTipo() {
		return tipo;
	}

	@NotNull
	@NotBlank
	public String getFecha() {
		return fecha;
	}

	@NotNull
	@NotBlank
	public String getEstado() {
		return estado;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	// Relationships ----------------------------------------------------------
	private Empleado empleado;
	
	@Valid
	@ManyToOne(optional=false)
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
