package domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class EmpleadoForm  {
	// Attributes -------------------------------------------------------------
	private String nombre;
	private String apellidos;
	private String correo;
	private String direccion;
	private int telefono;
	private int anio_entrada;
	private String username;
	private String password;
	private int vacaciones;
	private int diasPersonales;
	private int idDepartamento;

	@NotNull
	@NotBlank
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@NotNull
	@NotBlank
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@NotNull
	@NotBlank
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@NotNull
	@NotBlank
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getAnio_entrada() {
		return anio_entrada;
	}

	public void setAnio_entrada(int anio_entrada) {
		this.anio_entrada = anio_entrada;
	}
	
	@NotNull
	@NotBlank
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull
	@NotBlank
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(int vacaciones) {
		this.vacaciones = vacaciones;
	}

	public int getDiasPersonales() {
		return diasPersonales;
	}

	public void setDiasPersonales(int diasPersonales) {
		this.diasPersonales = diasPersonales;
	}

	@Min(0)
	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	
	

}
