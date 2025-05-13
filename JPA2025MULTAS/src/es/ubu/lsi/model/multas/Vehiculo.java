package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vehiculo
 *
 */
@Entity
@Table(name="Vehiculo")
public class Vehiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer idauto;
	private String nombre;
	@Embedded
	private DireccionPostal direccion;
    @ManyToOne
	@JoinColumns(name="idauto")
    private Conductor conductor;

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
	
    public Conductor getConductor() {
        return this.conductor;
    }

    public void setConductores(Conductor conductor) {
        this.conductor = conductor;
    }
   
   	@Override
	public String toString() {
		return "Vehiculo [IdAuto=" + getIdAuto() + ", Nombre=" + getNombre() + 
			", Direccion=" + getDireccion() + "]" + "\nConductor: " + getConductor();
	}
}