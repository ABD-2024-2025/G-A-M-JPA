package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * Clase que representa la información de un vehículo asociado a un conductor.
 * 
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara Abejón</a>
 * @author <a href="mailto:mmg1065@alu.ubu.es">María Molina</a>
 * 
 * @version 1.5
 * @since 1.5
 */
@Entity
@Table(name="Vehiculo")
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador del vehículo.
     */
    @Id
    @Column(name = "idauto")
    private String idauto;
    
    /**
	 * Nombre del vehículo.
	 */
    private String nombre;
    
    /**
	 * Dirección del vehículo.
	 */
    @Embedded
    private DireccionPostal direccion;
    
    /**
	 * Lista de conductores asociados al vehículo.
	 */
    @OneToMany(mappedBy = "vehiculo")
    private List<Conductor> conductores;

    /**
	 * Constructor por defecto.
	 */
    public Vehiculo() {}
    
	/**
	 * Getter del identificador del vehículo.
	 * 
	 * @return ID del vehículo.
	 */
    public String getIdAuto() {
        return this.idauto;
    }
    
    /**
     * Setter del identificador del vehículo.
     * 
     * @param idauto ID del vehículo.
     */
    public void setIdAuto(String idauto) {
        this.idauto = idauto;
    }  
    
    /**
     * Getter del nombre del vehículo.
     * 
     * @return Nombre del vehículo.
     */
    public String getNombre() {
        return this.nombre;
    }
    
    /**
	 * Setter del nombre del vehículo.
	 * 
	 * @param nombre Nombre del vehículo.
	 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
	 * Getter de la dirección del vehículo.
	 * 
	 * @return Dirección del vehículo.
	 */
    public String getDireccion() {
        return this.direccion.toString();
    }
    
    /**
     * Setter de la dirección del vehículo.
     * 
     * @param direccion Dirección del vehículo.
     */
    public void setDireccion(DireccionPostal direccion) {
        this.direccion = direccion;
    }

    /**
	 * Getter de la lista de conductores asociados al vehículo.
	 * 
	 * @return Lista de conductores.
	 */
    public List<Conductor> getConductores() {    	
        return this.conductores;
    }

    /**
     * Setter de la lista de conductores asociados al vehículo.
     * 
     * @param conductor Lista de conductores.
     */
    public void setConductor(List<Conductor> conductor) {
    	this.conductores = conductor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehiculo [IdAuto=").append(getIdAuto())
          .append(", Nombre=").append(getNombre())
          .append(", Direccion=").append(getDireccion())
          .append("]\nConductores: ");
        if (conductores != null) {
            for (Conductor c : conductores) {
            	// Evitamos la recursión añadiendo solo el NIF
                sb.append(c.getNIF()).append(" ");
            }
        }
        return sb.toString();
    }
}
