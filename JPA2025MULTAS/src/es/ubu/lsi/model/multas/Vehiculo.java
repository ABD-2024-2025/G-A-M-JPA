package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vehiculo
 *
 */
@Entity
@Table(name="Vehiculo")

public class Vehiculo implements Serializable {

	@Id //Primary Key (PK)
	private Integer idauto;
	private String nombre;
	private DireccionPostal direccion;
	private static final long serialVersionUID = 1L;

	public Vehiculo() {
		super();
	}   
	
	public Integer getIdAuto() {
		return this.idauto;
	}
	public void setIdAuto(Integer idauto) {
		this.idauto = idauto;
	}  
	
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return this.direccion.toString();
	}
	public void setDireccion (DireccionPostal direccion) {
		this.direccion = direccion;
	}   
   
   	@Override
	public String toString() {
		return "Vehiculo [IdAuto=" + getIdAuto() + ", Nombre=" + getNombre() + 
			", Direccion=" + getDireccion() + "]";
	}
}