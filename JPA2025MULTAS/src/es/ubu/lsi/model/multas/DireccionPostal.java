package es.ubu.lsi.model.multas;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class DireccionPostal implements Serializable {

	private static final long serialVersionUID = 1L;

	private String direccion;
	private String cp;
	private String ciudad;

	public DireccionPostal() {}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public void setCP(String cp) {
		this.cp = cp;
	}
	
	public String getCP() {
		return this.cp;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
    @Override
    public String toString() {
        return "DireccionPostal [direccion=" + getDireccion() + 
               ", cp=" + getCP() + ", ciudad=" + getCiudad() + "]";
    }
}

