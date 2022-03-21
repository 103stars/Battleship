package myProject;

import java.util.ArrayList;
import java.util.List;

class Barco {
	public Barco(){
		this.embarcacion = new ArrayList<>();
	}
	private int cantidad;
	private List<Casilla> embarcacion;
	
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public List<Casilla> getEmbarcacion() {
		return embarcacion;
	}

	public void setEmbarcacion(List<Casilla> embarcacion) {
		this.embarcacion = embarcacion;
	}
	

}