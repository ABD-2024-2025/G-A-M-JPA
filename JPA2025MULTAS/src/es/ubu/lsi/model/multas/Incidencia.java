package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

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
    private TipoIncidencia tipoIncidencia;

    public Incidencia() {
    }  

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        return tipoIncidencia;
    }

    public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
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
