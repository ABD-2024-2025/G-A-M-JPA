package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.util.List;

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
    @Column(name = "idauto")
    private String idauto;
    private String nombre;
    
    @Embedded
    private DireccionPostal direccion;
    

    /* ANOTACION NUEVA */
    @OneToMany(mappedBy = "vehiculo")
    private List<Conductor> conductores;
    
    /* ANOTACION ANTIGUA
     * @ManyToOne
    @JoinColumn(name = "NIFCONDUCTOR") // Cambiado de "NIF" a "NIFCONDUCTOR"
    private Conductor conductor;*/
    

    public Vehiculo() {
        super();
    }   
    
    public String getIdAuto() {
        return this.idauto;
    }
    
    public void setIdAuto(String idauto) {
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

    /* METODO NUEVO GETCONDUCTOR*/
    public List<Conductor> getConductores() {    	
        return this.conductores;
    }
    
    /* METODO ANTIGUO
    public Conductor getConductor() {
        return this.conductor;
    }*/

    /* METODO ANTIGUO
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }*/
    
    /* METODO NUEVO SETVEHICULO*/
    public void setConductor(List<Conductor> conductor) {
    	this.conductores = conductor;
    }

    /* METODO ANTIGUO
    @Override
    public String toString() {
        return "Vehiculo [IdAuto=" + getIdAuto() + ", Nombre=" + getNombre() + 
            ", Direccion=" + getDireccion() + "]" + "\nConductores: " + getConductores();
    }*/
    
    /* METODO NUEVO */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehiculo [IdAuto=").append(getIdAuto())
          .append(", Nombre=").append(getNombre())
          .append(", Direccion=").append(getDireccion())
          .append("]\nConductores: ");
        if (conductores != null) {
            for (Conductor c : conductores) {
                sb.append(c.getNIF()).append(" "); // Solo el identificador, para evitar recursión
            }
        }
        return sb.toString();
    }
}
