package myProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class GUI extends JFrame {

	/**
	 * This class is used to create the main frame of the program
	 * @author Alejandro Lasso
	 * @version v.1.0.0 date:21/03/2022
	 */
	private static final long serialVersionUID = 6411499808530678723L;

	private JPanel jugador;
	private JPanel oponente;
	private JButton empezar, instrucciones;

	private Tablero tJugador;
	private Tablero tOponente;
	private JTextArea informacion;

	/**
	 * Constructor of GUI class
	 */

	public GUI() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
		contentPane.setBounds(0, 0, 20, 20);
		this.setTitle("Battleship game");
		this.setSize(new Dimension(600,480));
		//this.setBounds(0, 0, 850, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
		inicialize();
		this.setVisible(true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);

	}

	/**
	 * This method is used to set up the default JComponent Configuration,
	 * create Listener and control Objects used for the GUI class
	 */
	
	private void inicialize() {
		this.tJugador = new Tablero();
		this.tJugador.setHabilitado(false);


		this.tOponente = new Tablero();
		this.tOponente.setHabilitado(true);

		String[]  cordenadaX = {"a","b","c","d","e","f","g","h","i","j"};
		
		this.jugador = new JPanel();
		this.jugador.setPreferredSize(new Dimension(225,225));
		this.jugador.setBounds(0, 0, 20, 20);
		this.jugador.setBorder(BorderFactory.createTitledBorder("Tu flota"));
		this.jugador.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));

		
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
				ficha.setForeground(Color.red);
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
		this.oponente.setPreferredSize(new Dimension(225,225));
		this.oponente.setBounds(0, 0, 20, 20);
		this.oponente.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
		this.oponente.setBorder(BorderFactory.createTitledBorder("Flota oponente"));
		List<Casilla> casillaOponente= new ArrayList<Casilla>();

		/**
		 *
		 * all of the 100 buttons are created in this part, their properties are also assigned
		 */

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
				ficha.setEnabled(false);
				ficha.setForeground(Color.red);
				ficha.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						casilla.jugada();
						tOponente.validarTablero();

						while(true) {
							Random aleatorio = new Random();
							int x = aleatorio.nextInt(100) + 1;
							if(tJugador.getCasillas().get(x-1).getEstado().equals(EstadoCasilla.VACIA) || tJugador.getCasillas().get(x-1).getEstado().equals(EstadoCasilla.OCUPADA)) {
								tJugador.getCasillas().get(x-1).getCasilla().setEnabled(true);
								tJugador.getCasillas().get(x-1).getCasilla().doClick();
								tJugador.getCasillas().get(x-1).getCasilla().setEnabled(false);
								tJugador.validarTablero();
								informacion.setText("Barcos que has destruído: " + tOponente.verDestruidos()+  "\n"+""+"\n"+
										"Barcos que te han destruído: " +tJugador.verDestruidos());
								if(tJugador.verDestruidos()==10){
									JOptionPane.showMessageDialog(null, "Has perdido, han destruído todos tus barcos");
									tOponente.getCasillas().forEach(cas ->{
										cas.getCasilla().setEnabled(false);
									});
									tJugador.getCasillas().forEach(cas ->{
										cas.getCasilla().setEnabled(false);
									});
								}if(tOponente.verDestruidos()==10){
									JOptionPane.showMessageDialog(null, "Has ganado! Destruiste todos los barcos de tu oponente");
									tOponente.getCasillas().forEach(cas ->{
										cas.getCasilla().setEnabled(false);
									});
									tJugador.getCasillas().forEach(cas ->{
										cas.getCasilla().setEnabled(false);
									});
								}
								break;
							}

							
						}
						
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

		/**
		 *
		 * More jComponents are added
		 */

		add(this.oponente);
		empezar = new JButton("Empezar");
		empezar.setBounds(100,0,100,0);
		empezar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Deseas mostrar la flota oponente?","Cuadro de dialogo", JOptionPane.YES_NO_OPTION);
				if (i== JOptionPane.YES_OPTION){
					tOponente.setCambiarColor(true);
				}else{
					tOponente.setCambiarColor(false);
				}

				tJugador.crearPartida();
				tOponente.crearPartida();
				empezar.setEnabled(false);
				tOponente.getCasillas().forEach(cas ->{
					cas.getCasilla().setEnabled(true);
				});
			}
		});
		add(empezar);

		informacion = new JTextArea(3,28);
		informacion.setEditable(false);
		informacion.setHighlighter(null);
		informacion.setBorder(BorderFactory.createTitledBorder("Información"));
		informacion.setText("Barcos que has destruído: 0" + "\n"+""+"\n"+
				"Barcos que te han destruído: 0");

		add(informacion);


		instrucciones = new JButton("?");

		add(instrucciones);
		instrucciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"El juego consiste en dos tableros que contienen una flota de barcos, donde el de tu oponente está oculto"+"\n"+
						"tu tarea es adivinar donde se encuentran y destruirlos todos."+"\n"+ "La máquina tambien tratará de destruir los tuyos."+"\n"+""+
						"\n"+"Los barcos se identifican con los siguientes colores:"+"\n"+
						"Verde -> Portaaviones , ocupan 4 casillas"+"\n"+
						"Gris -> Submarinos , ocupan 3 casillas"+"\n"+
						"Azul -> Destructores, ocupan 2 casillas"+"\n"+
						"Magenta -> Fragatas, ocupan 1 casilla"+"\n"+
						"Si la casilla que selecciones toma el color gris claro quiere decir que fallaste"+"\n"+
						"Si se torna de color amarillo significa que le diste a la parte de un barco"+"\n"+
						"Cuando se torna roja quiere decir que un barco entero ha sido destruído");

			}
		});

	}
}
