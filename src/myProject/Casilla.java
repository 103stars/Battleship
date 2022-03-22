package myProject;

import java.awt.*;

import javax.swing.JButton;

/**
 * This class is used to assign properties to each cell
 * @author Alejandro Lasso
 * @version v.1.0.0 date:21/03/2022
 */
public class Casilla {
	private EstadoCasilla estado;
	private String coordenada;
	private JButton casilla;
	private Color color;

	/**
	 *
	 * Determines what happens when a cell is clicked
	 */
	public void jugada() {
		this.casilla.setForeground(Color.red);
		if(this.estado.equals(EstadoCasilla.OCUPADA)) {
			this.estado = EstadoCasilla.ATACADA;
			this.casilla.setBackground(Color.orange);
			this.casilla.setEnabled(false);
		} else if(this.estado.equals(EstadoCasilla.VACIA)) {

			this.estado = EstadoCasilla.FALLO;
			this.casilla.setFont(new Font("", Font.BOLD, 20));
			this.casilla.setText("X");
		}
		this.casilla.setEnabled(false);
	}
	/**
	 * Setters and getters
	 */
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

}
