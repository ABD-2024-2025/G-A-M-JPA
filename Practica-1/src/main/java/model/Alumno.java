package model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Alumno
 *
 */
@Entity
@Table(name="Alumno")
public class Alumno implements Serializable {

	@Id
	private String NIF;
	private Integer edad;
	private String nombre;
	private String apellido;
	private static final long serialVersionUID = 1L;

	public Alumno() {
		super();
	}   
	public String getNIF() {
		return this.NIF;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
	}   
	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
   
   	@Override
	public String toString() {
		return "Alumno [NIF=" + getNIF() + ", Nombre=" + getNombre() + 
			", Apellido=" + getApellido() + ", Edad=" + getEdad() + "]";
	}
}
