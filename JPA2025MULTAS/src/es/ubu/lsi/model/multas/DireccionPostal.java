package es.ubu.lsi.model.multas;

import java.io.Serializable;

import javax.persistence.*;

/**
 * DireccionPostal. Clase que representa una dirección postal.
 * Es una clase embebida que se utiliza en otras entidades
 * como Conductor y Vehiculo.
 * 
 * @author <a href="https://joseleelportfolio.vercel.app">José Gallardo</a>
 * 
 * @version 1.5
 * @since 1.5
 */
@Embeddable
public class DireccionPostal implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Dirección del conductor.
	 */
	private String direccion;
	
	/**
	 * Código postal del conductor.
	 */
	private String cp;
	
	/**
	 * Ciudad del conductor.
	 */
	private String ciudad;

	/**
	 * Constructor por defecto.
	 */
	public DireccionPostal() {}
	
	/**
	 * Setter de la dirección del conductor.
	 * 
	 * @param direccion Dirección del conductor.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Getter de la dirección del conductor.
	 * 
	 * @return Dirección del conductor.
	 */
	public String getDireccion() {
		return this.direccion;
	}
	
	/**
	 * Setter del código postal del conductor.
	 * 
	 * @param cp Código postal del conductor.
	 */
	public void setCP(String cp) {
		this.cp = cp;
	}
	
	/**
	 * Getter del código postal del conductor.
	 * 
	 * @return Código postal del conductor.
	 */
	public String getCP() {
		return this.cp;
	}
	
	/**
	 * Setter de la ciudad del conductor.
	 * 
	 * @param ciudad Ciudad del conductor.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	/**
	 * Getter de la ciudad del conductor.
	 * 
	 * @return Ciudad del conductor.
	 */
	public String getCiudad() {
		return this.ciudad;
	}
	
    @Override
    public String toString() {
        return "DireccionPostal [direccion=" + getDireccion() + 
               ", cp=" + getCP() + ", ciudad=" + getCiudad() + "]";
    }
}

