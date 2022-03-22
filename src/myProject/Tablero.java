package myProject;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This class is used to create the main logic behind the game
 * @author Alejandro Lasso
 * @version v.1.0.0 date:21/03/2022
 */
public class Tablero {

	private List<Casilla> casillas;
	private boolean habilitado;
	private List<Barco> portaAviones;
	private List<Barco> submarinos;
	private List<Barco> destructores;
	private List<Barco> fragatas;
	boolean esDestruido = true;
	private int indexBarco;
	private int hundidos;


	boolean cambiarColor = false;

	public void setCambiarColor(boolean cambiarColor) {
		this.cambiarColor = cambiarColor;
	}


	/**
	 * Arraylists are started
	 */
	public Tablero() {
		this.casillas = new ArrayList<>();
		this.portaAviones = new ArrayList<>();
		this.submarinos = new ArrayList<>();
		this.destructores = new ArrayList<>();
		this.fragatas = new ArrayList<>();
		this.habilitado = true;
	}



	/**
	 * Dictates how many boats of each type are going to be placed in the board
	 */
	public void crearPartida() {
		if(habilitado && cambiarColor==false) {
			this.fragatas = crearEmbarcaciones(1, 4 , new Color(75,155,214));
			this.destructores = crearEmbarcaciones(2, 3, new Color(75,155,214));
			this.submarinos = crearEmbarcaciones(3, 2, new Color(75,155,214));
			this.portaAviones = crearEmbarcaciones(4, 1, new Color(75,155,214));
		}else{
			this.fragatas = crearEmbarcaciones(1, 4 , Color.MAGENTA);
			this.destructores = crearEmbarcaciones(2, 3, Color.BLUE);
			this.submarinos = crearEmbarcaciones(3, 2, Color.GRAY);
			this.portaAviones = crearEmbarcaciones(4, 1, Color.green);
		}
	}



	/**
	 * Checks if a boat is destroyed and makes it red
	 * @param barcos
	 */
	public void validadBarcos(List<Barco> barcos) {
		barcos.forEach(barco ->{
			indexBarco = 0;
			esDestruido = true;
			barco.getEmbarcacion().forEach(cas ->{
				indexBarco++;
				if(cas.getEstado().equals(EstadoCasilla.OCUPADA)) {
					esDestruido = false;
				}
				if(indexBarco == barco.getEmbarcacion().size() && esDestruido ) {
					barco.getEmbarcacion().forEach(cas1 ->{
						cas1.getCasilla().setBackground(Color.red);
						cas1.getCasilla().setEnabled(false);
						cas1.setEstado(EstadoCasilla.HUNDIDO);
					});
				}
			});

		});

	}

	/**
	 *Uses the above method for every type of boat
	 *
	 */
	public void validarTablero() {
		validadBarcos(this.fragatas);
		validadBarcos(this.destructores);
		validadBarcos(this.submarinos);
		validadBarcos(this.portaAviones);
	}


	/**
	 *
	 * Displays the sum of destroyed boats
	 * @return
	 */
	public int verDestruidos() {
		return calcularDestruidos(destructores)+ calcularDestruidos(fragatas) + calcularDestruidos(portaAviones) + calcularDestruidos(submarinos);
	}


	/**
	 * Counts how many boats are destroyed
	 * @param barcos
	 * @return
	 */
	public int calcularDestruidos(List<Barco> barcos) {
		hundidos = 0;
		barcos.forEach(barco ->{
			indexBarco = 0;
			esDestruido = true;
			barco.getEmbarcacion().forEach(cas ->{
				indexBarco++;
				if(!(cas.getEstado().equals(EstadoCasilla.HUNDIDO))) {
					esDestruido = false;
				}
				if(indexBarco == barco.getEmbarcacion().size() && esDestruido ) {
					hundidos++;
				}
			});

		});
		return hundidos;
	}

	/**
	 * Most important method in the class, it creates all the boats checks each position to see
	 * if a boat can be placed in said position, could be either vertically or horizontally
	 *
	 * @param tipo Type of boat like destructor or submarine
	 * @param cantidadBarcos amount of boats placed in the board
	 * @param color color of each type of boat placed in player's board
	 * @return
	 */
	private List<Barco> crearEmbarcaciones(int tipo, int cantidadBarcos, Color color) {
		String[] puntos = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		int x = 0;
		int y = 0;
		List<Barco> barcos = new ArrayList<>();
		List<Casilla> embarcacion = new ArrayList<>();
		while (!(barcos.size() == cantidadBarcos)) {
			Barco barco = new Barco();
			Random aleatorio = new Random();
			x = aleatorio.nextInt(10) + 1;
			y = aleatorio.nextInt(10) + 1;
			while (true) {
				int direccion = aleatorio.nextInt(2) + 1;

				int letraPunto = x;
				int numeroPunto = y;
				embarcacion = new ArrayList<>();
				for (int j = 0; j < tipo; j++) {
					int puntomovible = j;
					Casilla cas = null;
					if (direccion == 1) {
						if (tipo <= (10 - (y-1))) {
							cas = this.casillas.stream().filter(
											casilla -> (puntos[(letraPunto - 1)] + (numeroPunto + puntomovible)).equals(casilla.getCoordenada()))
									.findAny().orElse(null);
						} else {
							barcos.forEach(bar -> {
								bar.getEmbarcacion().forEach(e ->{
									e.setEstado(EstadoCasilla.VACIA);
									e.getCasilla().setBackground(new Color(75,155,214));
								});
							});
							embarcacion.forEach(emb->{
								emb.setEstado(EstadoCasilla.VACIA);
								emb.getCasilla().setBackground(new Color(75,155,214));
							});
							embarcacion.clear();
							barco.getEmbarcacion().clear();
							barcos.clear();
							x = aleatorio.nextInt(10) + 1;
							y = aleatorio.nextInt(10) + 1;
							break;
						}
					} else {
						if (tipo <= (10 -(x-1))  ) {
							cas = this.casillas.stream()
									.filter(casilla -> (puntos[letraPunto + (puntomovible - 1)] + numeroPunto).equals(casilla.getCoordenada()))
									.findAny().orElse(null);
						} else {
							barcos.forEach(bar -> {
								bar.getEmbarcacion().forEach(e ->{
									e.setEstado(EstadoCasilla.VACIA);
									e.getCasilla().setBackground(new Color(75,155,214));
								});
							});
							embarcacion.forEach(emb->{
								emb.setEstado(EstadoCasilla.VACIA);
								emb.getCasilla().setBackground(new Color(75,155,214));
							});
							embarcacion.clear();
							barco.getEmbarcacion().clear();
							barcos.clear();
							x = aleatorio.nextInt(10) + 1;
							y = aleatorio.nextInt(10) + 1;
							break;
						}
					}
					int idx = this.casillas.indexOf(cas);

					barco.setCantidad(tipo);

					if (cas != null) {
						if (this.casillas.get(idx).getEstado().equals(EstadoCasilla.VACIA)) {
							this.casillas.get(idx).setEstado(EstadoCasilla.OCUPADA);
							this.casillas.get(idx).getCasilla().setBackground(color);
							embarcacion.add(this.casillas.get(idx));
						} else {

							barcos.forEach(bar -> {
								bar.getEmbarcacion().forEach(e ->{
									e.setEstado(EstadoCasilla.VACIA);
									e.getCasilla().setBackground(new Color(75,155,214));
								});
							});
							embarcacion.forEach(emb->{
								emb.setEstado(EstadoCasilla.VACIA);
								emb.getCasilla().setBackground(new Color(75,155,214));
							});
							embarcacion.clear();
							barco.getEmbarcacion().clear();
							barcos.clear();
							x = aleatorio.nextInt(10) + 1;
							y = aleatorio.nextInt(10) + 1;
							break;
						}

					}
				}

				if(embarcacion.size() == tipo) {
					if (barco != null) {
						barco.setEmbarcacion(embarcacion);
						barcos.add(barco);
					}
					break;
				} else {
					barcos.clear();
					break;
				}
			}
		}
		return barcos;
	}

	/**
	 * Setters and getters.
	 */

	public List<Casilla> getCasillas() {
		return casillas;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}
	public boolean isHabilitado() {
		return habilitado;
	}

}