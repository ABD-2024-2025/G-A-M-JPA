package es.ubu.lsi.model.multas;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Clase que representa la información de un vehículo asociado a un conductor.
 * 
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara ABejón</a>
 * 
 * @version 1.5
 * @since 1.5
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
    @JoinColumn(name = "NIFCONDUCTOR") // Cambiado de "NIF" a "NIFCONDUCTOR"
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
    
    public void setDireccion(DireccionPostal direccion) {
        this.direccion = direccion;
    }
    
    public Conductor getConductor() {
        return this.conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }
   
    @Override
    public String toString() {
        return "Vehiculo [IdAuto=" + getIdAuto() + ", Nombre=" + getNombre() + 
            ", Direccion=" + getDireccion() + "]" + "\nConductor: " + getConductor();
    }
}
