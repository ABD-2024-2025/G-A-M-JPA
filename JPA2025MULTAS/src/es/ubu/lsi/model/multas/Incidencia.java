package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Incidencia
 *
 */
@Entity
@Table(name="Incidencia")
public class Incidencia implements Serializable {

	@Id
	private Date fecha;
	@Id
	private String NIF;
	private String nombre;
	private String apellido;
	@OneToMany
	@JoinColumn(name = "dp_ip") 
	private DireccionPostal direccion;
	private Integer puntos;
	@ManyToMany
	@JoinColumn(name = "idauto") 
	private Vehiculo idauto;
	private static final long serialVersionUID = 1L;

	public Incidencia() {
	}  
	
	public String getNIF() {
		return this.NIF;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
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
	
	public String getDireccion() {
		return this.direccion.toString();
	}

	public void setDireccion(DireccionPostal direccion) {
		this.direccion = direccion;
	}
	
	public Integer getPuntos() {
		return this.puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
	
	public Vehiculo getIdauto() {
		return this.idauto;
	}

	public void setIdauto(Vehiculo idauto) {
		this.idauto = idauto;
	}
   
   	@Override
	public String toString() {
		return "Conductor [NIF=" + getNIF() + ", Nombre=" + getNombre() + 
			", Apellido=" + getApellido() + ", Direccion=" + getDireccion() + 
			", Puntos=" + getPuntos() + ", Vehiculo=" + getIdauto() + "]";
	}
}