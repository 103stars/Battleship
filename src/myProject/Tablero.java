package myProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {
	
	private List<Casilla> casillas;
	private boolean habilitado;
	private List<Barco> portaAviones;
	private List<Barco> submarinos;
	private List<Barco> destructores;
	private List<Barco> fragatas;
 	
	public Tablero() {
		this.casillas = new ArrayList<>();
		this.portaAviones = new ArrayList<>();
		this.submarinos = new ArrayList<>();
		this.destructores = new ArrayList<>();
		this.fragatas = new ArrayList<>();
		this.habilitado = true;
	}

	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
	public void crearPartida() {
		this.crearFragata(1,4);
	}
	
	public void validarTablero(){
		boolean fragataDestruida = true;
		this.fragatas.forEach(frag ->{
			frag.getEmbarcacion().forEach(cas -> {
				if(cas.getEstado().equals(EstadoCasilla.OCUPADA)) {
					
				}
			});
		});
	}
	
	private void crearFragata(int tipo, int cantidadBarcos) {
		String[]  fichasOponente = {"a","b","c","d","e","f","g","h","i","j"};
		
		for(int i = 0; i < tipo ; i++) {
			
			while (true){
				Random aleatorio = new Random();
				int x = aleatorio.nextInt(10)+1;
				int y = aleatorio.nextInt(10)+1;
				
				Casilla cas = this.casillas.stream().filter(casilla -> (fichasOponente[x-1] + y).equals(casilla.getCoordenada())).findAny().orElse(null);
				int idx = this.casillas.indexOf(cas);
				
				Barco barco =  new Barco();
				barco.setCantidad(tipo);
				List<Casilla> fragata = new ArrayList<>();
				if(cas.getEstado().equals(EstadoCasilla.VACIA)) {
					this.casillas.get(idx).setEstado(EstadoCasilla.OCUPADA);
					fragata.add(this.casillas.get(idx));
					barco.setEmbarcacion(fragata);
					this.fragatas.add(barco);
					break;
				}
				
			}
		}
 
	}
	
	class Embarcacion{
		private List<Casilla> embarcacion;

		public List<Casilla> getEmbarcacion() {
			return embarcacion;
		}

		public void setEmbarcacion(List<Casilla> embarcacion) {
			this.embarcacion = embarcacion;
		}

		
	}
	class Barco extends Embarcacion{
		private int cantidad;

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
	
	}
	
}
