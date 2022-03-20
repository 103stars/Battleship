package myProject;

import java.awt.Color;

import javax.swing.JButton;

public class Casilla {
	private EstadoCasilla estado;
	private String coordenada;
	private JButton casilla;
	private Color color;
	
	public JButton getCasilla() {
		return casilla;
	}
	public void setCasilla(JButton casilla) {
		this.casilla = casilla;
	}
	public EstadoCasilla getEstado() {
		return estado;
	}
	public void setEstado(EstadoCasilla estado) {
		this.estado = estado;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(String coordenada) {
		this.coordenada = coordenada;
	}
	
	public void jugada() {
		if(this.estado.equals(EstadoCasilla.OCUPADA)) {
			this.estado = EstadoCasilla.ATACADA;
			this.casilla.setBackground(Color.orange);
			this.casilla.setEnabled(false);
		} else if(this.estado.equals(EstadoCasilla.VACIA)) {
			this.estado = EstadoCasilla.FALLO;
			this.casilla.setBackground(Color.pink);
		}
	}
	
	
	
	
}
