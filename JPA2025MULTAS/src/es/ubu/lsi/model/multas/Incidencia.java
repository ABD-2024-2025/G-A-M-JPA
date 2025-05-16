package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 * Clase que representa una incidencia asociada a un conductor.
 * 
 * @author <a href="mailto:mmg1065@alu.ubu.es">María Molina</a>
 * 
 * @version 1.5
 * @since 1.5
 */
@Entity
@Table(name="Incidencia")
public class Incidencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Date fecha;
    
    @Id
    @Column(name = "NIF")
    private String NIF;
    
    @ManyToOne
    @JoinColumn(name = "NIF", insertable = false, updatable = false)
    private Conductor conductor;
    
    private String anotacion;
    
    @ManyToOne
    @JoinColumn(name = "idTipo")
    private TipoIncidencia idTipo;

    public Incidencia() {
    }  

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFecha(java.util.Date fecha) {
        if (fecha != null) {
            this.fecha = new java.sql.Date(fecha.getTime());
        } else {
            this.fecha = null;
        }
    }

    public String getNIF() {
        return this.NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }
    
    public Conductor getConductor() {
        return conductor;
    }
    
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
        this.NIF = conductor.getNIF();
    }   
       
    public TipoIncidencia getTipoIncidencia() {
        return idTipo;
    }

    public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
        this.idTipo = tipoIncidencia;
    }

    public void setTipo(TipoIncidencia tipo) {
        setTipoIncidencia(tipo);
    }
   
    public String getAnotacion() {
        return this.anotacion;
    }
    
    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }
    
    @Override
    public String toString() {
        return "Incidencia [fecha=" + getFecha() + ", NIF=" + getNIF() + 
                ", anotacion=" + getAnotacion() + ", tipoIncidencia=" + 
                getTipoIncidencia() + "]";
    }
}
