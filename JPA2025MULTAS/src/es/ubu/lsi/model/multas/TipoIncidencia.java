package es.ubu.lsi.model.multas;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="TipoIncidencia")
public class TipoIncidencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String descripcion;
	private int valor;
	
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
	
	@Override
	public String toString() {
		return "TipoIncidencia [id=" + getId() + ", descripcion=" + getDescripcion() + ", valor=" + getValor() + "]";
	}
}
