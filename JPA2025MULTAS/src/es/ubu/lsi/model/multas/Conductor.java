package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Modelo de datos de un conductor.
 * 
 * @author <a href="https://joseleelportfolio.vercel.app">José Gallardo</a>
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara ABejón</a>
 * @author <a href="mailto:mmg1065@alu.ubu.es">María Molina</a>
 * 
 * @version 1.5
 * @since 1.5
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
	
	/* ANOTACION NUEVA */
	@ManyToOne
	@JoinColumn(name = "idauto") // Esta es la FK en la tabla Conductor
	private Vehiculo vehiculo;
	
	/* ANOTACION ANTIGUA
    @OneToMany(mappedBy="conductor") // Cambiado de "idauto" a "conductor" 
    private List<Vehiculo> vehiculos; // Renombrado de idAuto a vehiculos */
	
    @OneToMany(mappedBy="NIF", cascade=CascadeType.ALL)
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
	
	/* METODO NUEVO VEHICULO*/
    public Vehiculo getVehiculos() {
    	return this.vehiculo;
    }
    
    /* METODO ANTIGUO
    public List<Vehiculo> getVehiculos() {    	
        return this.vehiculos;
    }*/

    /* METODO NUEVO VEHICULO*/
    public void setVehiculos(Vehiculo vehiculo) {
    	this.vehiculo = vehiculo;
    }
    
    
    /* METODO ANTIGUO
    public void setVehiculos(List<Vehiculo> vehiculos) {    	
        this.vehiculos = vehiculos;
    }*/
    
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