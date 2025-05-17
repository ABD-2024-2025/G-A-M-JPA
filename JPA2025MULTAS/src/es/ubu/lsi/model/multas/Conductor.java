package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Modelo de datos de un conductor.
 * 
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara Abejón</a>
 * @author <a href="mailto:mmg1065@alu.ubu.es">María Molina</a>
 * 
 * @version 1.5
 * @since 1.5
 */
@Entity
@Table(name="Conductor")
public class Conductor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * NIF del conductor.
	 */
	@Id
	private String NIF;
	
	/**
	 * Nombre del conductor.
	 */
	private String nombre;
	
	/**
	 * Apellido del conductor.
	 */
	private String apellido;
	
	/**
	 * Dirección del conductor.
	 */
	@Embedded
	private DireccionPostal direccion;
	
	/**
	 * Puntos del conductor.
	 */
	private Integer puntos;
	
	/**
	 * Vehiculo del conductor.
	 */
	@ManyToOne
	@JoinColumn(name = "idauto")
	private Vehiculo vehiculo;
	
	/**
	 * Incidencias del conductor.
	 */
    @OneToMany(mappedBy="NIF", cascade=CascadeType.ALL)
	private Set<Incidencia> incidencias;
    
    /**
	 * Constructor por defecto.
	 */
	public Conductor() {}
	
	/**
	 * Getter del NIF del conductor.
	 * 
	 * @return NIF del conductor.
	 */
	public String getNIF() {
		return this.NIF;
	}

	/**
	 * Setter del NIF del conductor.
	 * 
	 * @param NIF del conductor.
	 */
	public void setNIF(String NIF) {
		this.NIF = NIF;
	}   

	/**
	 * Getter del nombre del conductor.
	 * 
	 * @return Nombre del conductor.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Setter del nombre del conductor.
	 * 
	 * @param nombre del conductor.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter del apellido del conductor.
	 * 
	 * @return Apellido del conductor.
	 */
	public String getApellido() {
		return this.apellido;
	}
	
	/**
	 * Setter del apellido del conductor.
	 * 
	 * @param apellido del conductor.
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Getter de la direccion del conductor.
	 * 
	 * @return Direccion del conductor.
	 */
	public String getDireccion() {
		return this.direccion.toString();
	}

	/**
	 * Setter de la direccion del conductor.
	 * 
	 * @param direccion del conductor.
	 */
	public void setDireccion(DireccionPostal direccion) {
		this.direccion = direccion;
	}

	/**
	 * Getter de los puntos del conductor.
	 * 
	 * @return Puntos del conductor.
	 */
	public Integer getPuntos() {
		return this.puntos;
	}

	/**
	 * Setter de los puntos del conductor.
	 * 
	 * @param puntos del conductor.
	 */
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	/**
	 * Getter del vehiculo del conductor.
	 * 
	 * @return Vehiculo del conductor.
	 */
    public Vehiculo getVehiculos() {
    	return this.vehiculo;
    }

    /**
     * Setter del vehiculo del conductor.
     * 
     * @param vehiculo del conductor.
     */
    public void setVehiculos(Vehiculo vehiculo) {
    	this.vehiculo = vehiculo;
    }
    
    /**
	 * Getter de las incidencias del conductor.
	 * 
	 * @return Incidencias del conductor.
	 */
    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }
    
    /**
     * Setter de las incidencias del conductor.
     * 
     * @param incidencias del conductor.
     */
    public void setIncidencias(Set<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
    
    /**
	 * Añade una incidencia al conductor.
	 * 
	 * @param inc Incidencia a añadir.
	 */
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