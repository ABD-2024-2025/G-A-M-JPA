package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.util.Date;
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

    /**
     * Fecha de la incidencia.
     */
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    /**
	 * NIF del conductor al que se le asocia la incidencia.
	 */
    @Id
    @Column(name = "NIF")
    private String NIF;
    
    /**
	 * Datos del conductor al que se le asocia la incidencia.
	 * No es insertable ni actualizable porque se obtiene a partir del NIF.
	 */
    @ManyToOne
    @JoinColumn(name = "NIF", insertable = false, updatable = false)
    private Conductor conductor;
    
    /**
     * Anotación de la incidencia. Es el asunto de la incidencia.
     */
    private String anotacion;
    
    /**
	 * Tipo de incidencia. Tiene más información al respecto de la incidencia.
	 */
    @ManyToOne
    @JoinColumn(name = "idTipo")
    private TipoIncidencia idTipo;

    /**
	 * Constructor por defecto.
	 */
    public Incidencia() {}

    /**
     * Getter del atributo fecha.
     * 
     * @return fecha de la incidencia.
     */
    public Date getFecha() {
        return this.fecha;
    }

    /**
	 * Setter del atributo fecha.
	 * 
	 * @param fecha fecha de la incidencia.
	 */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
	 * Getter del atributo NIF.
	 * 
	 * @return NIF del conductor al que se le asocia la incidencia.
	 */
    public String getNIF() {
        return this.NIF;
    }

    /**
     * Setter del atributo NIF.
     * 
     * @param NIF NIF del conductor al que se le asocia la incidencia.
     */
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }
    
    /**
     * Getter del atributo conductor.
     * 
     * @return conductor al que se le asocia la incidencia.
     */
    public Conductor getConductor() {
        return conductor;
    }
    
    /**
	 * Setter del atributo conductor.
	 * 
	 * @param conductor conductor al que se le asocia la incidencia.
	 */
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
        this.NIF = conductor.getNIF();
    }   
    
    /**
	 * Getter del atributo idTipo.
	 * 
	 * @return idTipo de la incidencia.
	 */
    public TipoIncidencia getTipoIncidencia() {
        return idTipo;
    }
    
    /**
     * Setter del atributo idTipo.
     * 
     * @param tipoIncidencia tipo de incidencia.
     */
    public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
        this.idTipo = tipoIncidencia;
    }
    
    /**
     * Getter del atributo anotacion.
     * 
     * @return anotacion de la incidencia.
     */
    public String getAnotacion() {
        return this.anotacion;
    }
    
    /**
     * Setter del atributo anotacion.
     * 
     * @param anotacion anotacion de la incidencia.
     */
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
