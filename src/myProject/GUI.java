package myProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {

	/**
	* 
	*/
	private static final long serialVersionUID = 6411499808530678723L;

	private JPanel jugador;
	private JPanel oponente;
	private JButton empezar;
	private Tablero tJugador;
	private Tablero tOponente;
	public GUI() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 50,20));
		contentPane.setBounds(0, 0, 20, 20);
		this.setSize(new Dimension(800,800));
		//this.setBounds(0, 0, 850, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
		inicialize();
		this.setVisible(true);
		this.setLayout(null);
	}
	
	private void inicialize() {
		this.tJugador = new Tablero();
		this.tJugador.setHabilitado(false);
		this.tOponente = new Tablero();
		this.tOponente.setHabilitado(true);
		String[]  cordenadaX = {"a","b","c","d","e","f","g","h","i","j"};
		
		this.jugador = new JPanel();
		this.jugador.setPreferredSize(new Dimension(200,200));
		this.jugador.setBounds(0, 0, 20, 20);
		this.jugador.setBackground(Color.red);
		this.jugador.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
		
		List<Casilla> casillaJugador= new ArrayList<Casilla>(); 
		for(int i = 0; i<10 ; i++) {
			for(int j = 1; j<=10 ; j++) {
				Casilla casilla = new Casilla();
				casilla.setColor(new Color(75,155,214));
				casilla.setCoordenada(cordenadaX[i]+j);
				casilla.setEstado(EstadoCasilla.VACIA);
				
				
				JButton ficha = new JButton();
				ficha.setPreferredSize(new Dimension(20,20));
				ficha.setBounds(0, 0, 10, 10);
				ficha.setBackground(casilla.getColor());
				ficha.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				ficha.setEnabled(this.tJugador.isHabilitado());
				ficha.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						casilla.jugada();
					}
				});
				casilla.setCasilla(ficha);
				casillaJugador.add(casilla);		
			}
		}
		this.tJugador.setCasillas(casillaJugador);
		this.tJugador.getCasillas().forEach(casilla -> {
			this.jugador.add(casilla.getCasilla());
		});
		add(this.jugador);
		
		this.oponente = new JPanel();
		this.oponente.setPreferredSize(new Dimension(200,200));
		this.oponente.setBounds(0, 0, 20, 20);
		this.oponente.setBackground(Color.ORANGE);
		this.oponente.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
		List<Casilla> casillaOponente= new ArrayList<Casilla>();

		for(int i = 0; i<10 ; i++) {
			for(int j = 1; j<=10 ; j++) {
				Casilla casilla = new Casilla();
				casilla.setColor(new Color(75,155,214));
				casilla.setCoordenada(cordenadaX[i]+j);
				casilla.setEstado(EstadoCasilla.VACIA);
				JButton ficha = new JButton();
				ficha.setPreferredSize(new Dimension(20,20));
				ficha.setBounds(0, 0, 10, 10);
				ficha.setBackground(casilla.getColor());
				ficha.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				ficha.setEnabled(this.tOponente.isHabilitado());
				ficha.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						casilla.jugada();
					}
				});				
				casilla.setCasilla(ficha);
				casillaOponente.add(casilla);
				
			}
		}
		this.tOponente.setCasillas(casillaOponente);
		this.tOponente.getCasillas().forEach(casilla -> {
			this.oponente.add(casilla.getCasilla());
		});
		this.tOponente.crearPartida();
		add(this.oponente);
		
		empezar = new JButton("Empezar");
		add(empezar);
	}
}
