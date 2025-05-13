package es.ubu.lsi.model.multas;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class DireccionPostal implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer dp_id;
	private String direccion;
	private String cp;
	private String ciudad;

	public DireccionPostal() {}
	
	public void setDP_ID(Integer dp_id) {
		this.dp_id = dp_id;
	}
	
	public Integer getDP_ID() {
		return this.dp_id;
	}
	
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
        return "DireccionPostal [dp_id=" + getDP_ID() + ", direccion=" + getDireccion() + 
               ", cp=" + getCP() + ", ciudad=" + getCiudad() + "]";
    }
}

