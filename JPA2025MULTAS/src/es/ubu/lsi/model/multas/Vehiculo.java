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
    @ManyToMany(mappedBy = "vehiculos")
    private List<Conductor> conductores;

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
	
    public List<Conductor> getConductores() {
        return this.conductores;
    }

    public void setConductores(List<Conductor> conductores) {
        this.conductores = conductores;
    }
    
    public void addConductor(Conductor conductor) {
		this.conductores.add(conductor);
		conductor.getVehiculos().add(this);
	}
    
    public void removeConductor(Conductor conductor) {
    	this.conductores.remove(conductor);
    	conductor.getVehiculos().remove(this);
    }
   
   	@Override
	public String toString() {
		return "Vehiculo [IdAuto=" + getIdAuto() + ", Nombre=" + getNombre() + 
			", Direccion=" + getDireccion() + "]" + "\nConductores: " + getConductores();
	}
}