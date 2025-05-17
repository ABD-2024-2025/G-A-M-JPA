package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Clase que representa la información de una incidencia asociada a un conductor.
 * 
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara Abejón</a>
 * 
 * @version 1.5
 * @since 1.5
 */
@Entity
@Table(name="TipoIncidencia")
public class TipoIncidencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador de la incidencia.
	 */
	@Id
	private Integer id;
	
	/**
	 * Descripción de la incidencia.
	 */
	private String descripcion;
	
	/**
	 * Valor de la incidencia.
	 */
	private int valor;
	
	/**
	 * Lista de incidencias asociadas a este tipo de incidencia.
	 * En caso de eliminar, se eliminarán las incidencias asociadas en cascada.
	 */
	@OneToMany(mappedBy = "idTipo", cascade = CascadeType.ALL)
	private List<Incidencia> incidencias;
	
	/**
	 * Constructor por defecto.
	 */
	public TipoIncidencia() {}
	
	/**
	 * Getter del identificador de la incidencia.
	 * 
	 * @return Identificador de la incidencia.
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Setter del identificador de la incidencia.
	 * 
	 * @param id Identificador de la incidencia.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Getter de la descripción de la incidencia.
	 * 
	 * @return Descripción de la incidencia.
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	
	/**
	 * Setter de la descripción de la incidencia.
	 * 
	 * @param descripcion Descripción de la incidencia.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Getter del valor de la incidencia.
	 * 
	 * @return Valor de la incidencia.
	 */
	public int getValor() {
		return this.valor;
	}
	
	/**
	 * Setter del valor de la incidencia.
	 * 
	 * @param valor Valor de la incidencia.
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	/**
	 * Getter de la lista de incidencias asociadas a este tipo de incidencia.
	 * 
	 * @return Lista de incidencias asociadas a este tipo de incidencia.
	 */
	public List<Incidencia> getIncidencias() {
		return this.incidencias;
	}
	
	/**
	 * Setter de la lista de incidencias asociadas a este tipo de incidencia.
	 * 
	 * @param incidencias Lista de incidencias asociadas a este tipo de incidencia.
	 */
	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	
	/**
	 * Añade una incidencia a la lista de incidencias asociadas a este tipo de incidencia.
	 * 
	 * @param incidencia Incidencia a añadir.
	 */
	public void addIncidencia(Incidencia incidencia) {
		getIncidencias().add(incidencia);
		incidencia.setTipoIncidencia(this);
	}
	
	/**
	 * Elimina una incidencia de la lista de incidencias asociadas a este tipo de incidencia.
	 * 
	 * @param incidencia Incidencia a eliminar.
	 */
	public void removeIncidencia(Incidencia incidencia) {
		getIncidencias().remove(incidencia);
		incidencia.setTipoIncidencia(null);
	}
	
	/**
	 * Método que devuelve el número de puntos asociados a la incidencia.
	 * 
	 * @return Número de puntos asociados a la incidencia.
	 */
	public int getPuntos() {
		return this.valor;
	}

	@Override
	public String toString() {
	    int numIncidencias = (incidencias == null) ? 0 : incidencias.size();
	    return "TipoIncidencia [id=" + getId() + ", descripcion=" + getDescripcion() + 
	           ", valor=" + getValor() + ", numeroIncidencias=" + numIncidencias + "]";
	}

}
