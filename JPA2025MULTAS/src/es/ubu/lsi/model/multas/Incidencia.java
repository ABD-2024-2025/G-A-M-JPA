package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Incidencia
 *
 */
@Entity
@Table(name="Incidencia")
public class Incidencia implements Serializable {

	@Id
	private Date fecha;
	@Id
	private String NIF;
	private String anotacion;
	@ManyToOne
	@JoinColumn(name = "idTipo")
	private TipoIncidencia tipoIncidencia;
	
	private static final long serialVersionUID = 1L;

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
	   
	public TipoIncidencia getTipoIncidencia() {
	    return tipoIncidencia;
	}

	public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
	    this.tipoIncidencia = tipoIncidencia;
	}
   
	private String getAnotacion() {
		return this.anotacion;
	}
	
	private void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
	}
	
   	@Override
	public String toString() {
		return "Incidencia [fecha=" + getFecha() + ", NIF=" + getNIF() + 
				", anotacion=" + getAnotacion() + ", tipoIncidencia=" + 
				getTipoIncidencia() + "]";
	}

}