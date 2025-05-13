package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Conductor
 *
 */
@Entity
@Table(name="Conductor")
public class Conductor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String NIF;
	private String nombre;
	private String apellido;
	@Embedded
	private DireccionPostal direccion;
	private Integer puntos;
    @OneToMany(mappedBy="idauto")
    private Collection<Vehiculo> vehiculos;
    @OneToMany(mappedBy="conductor", cascade=CascadeType.ALL)
	private Set<Incidencia> incidencias;

	public Conductor() {
		super();
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
	
    public List<Vehiculo> getVehiculos() {
        return (List<Vehiculo>) this.vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    public void addVehiculo(Vehiculo vehiculo) {
		this.vehiculos.add(vehiculo);
		vehiculo.getConductores().add(this);
	}
    
    public void removeVehiculo(Vehiculo vehiculo) {
    	this.vehiculos.remove(vehiculo);
    	vehiculo.getConductores().remove(this);
    }
    
    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }
    
    public void setIncidencias(Set<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
    
    public void addIncidencia(Incidencia inc) {
        this.incidencias.add(inc);
        inc.setConductor(this);
    }
   
   	@Override
	public String toString() {
		return "Conductor [NIF=" + getNIF() + ", Nombre=" + getNombre() + 
			", Apellido=" + getApellido() + ", Direccion=" + getDireccion() + 
			", Puntos=" + getPuntos() + ", Vehiculos=" + getVehiculos() + "Incidencias=" + getIncidencias() + "]";
	}
}