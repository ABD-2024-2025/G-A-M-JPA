package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Clase que representa la información de una incidencia asociada a un conductor.
 * 
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara ABejón</a>
 * 
 * @version 1.5
 * @since 1.5
 */
@Entity
@Table(name="TipoIncidencia")
public class TipoIncidencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String descripcion;
	private int valor;
	@OneToMany(mappedBy = "idTipo", cascade = CascadeType.ALL)
	private List<Incidencia> incidencias;
	
	public TipoIncidencia() {
		super();
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getValor() {
		return this.valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public List<Incidencia> getIncidencias() {
		return this.incidencias;
	}
	
	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	
	public void addIncidencia(Incidencia incidencia) {
		getIncidencias().add(incidencia);
		incidencia.setTipoIncidencia(this);
	}
	
	public void removeIncidencia(Incidencia incidencia) {
		getIncidencias().remove(incidencia);
		incidencia.setTipoIncidencia(null);
	}
	
	public int getPuntos() {
		return this.valor;
	}
	
	/* ANTIGUA 
	@Override
	public String toString() {
		return "TipoIncidencia [id=" + getId() + ", descripcion=" + getDescripcion() + 
				", valor=" + getValor() + ", incidencias=" + getIncidencias() + "]";
	}*/
	
	/* NUEVA */
	@Override
	public String toString() {
	    int numIncidencias = (incidencias == null) ? 0 : incidencias.size();
	    return "TipoIncidencia [id=" + getId() + ", descripcion=" + getDescripcion() + 
	           ", valor=" + getValor() + ", numeroIncidencias=" + numIncidencias + "]";
	}

}
