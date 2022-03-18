package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 6411499808530678723L;

    private JPanel jugador;
    private JPanel oponente;


    public GUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 50,20));
        contentPane.setBounds(0, 0, 20, 20);
        this.setSize(new Dimension(800,400));
        //this.setBounds(0, 0, 850, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        inicialize();
        this.setVisible(true);
        this.setLayout(null);
    }

    private void inicialize() {

        this.jugador = new JPanel();
        this.jugador.setPreferredSize(new Dimension(200,200));
        this.jugador.setBounds(0, 0, 20, 20);
        this.jugador.setBackground(Color.red);
        this.jugador.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        JButton[][]  fichasJugador = new JButton[10][10];

        for(int i = 0; i<10 ; i++) {
            for(int j = 0; j<10 ; j++) {
                JButton ficha = new JButton();
                ficha.setPreferredSize(new Dimension(20,20));
                ficha.setBounds(0, 0, 10, 10);
                ficha.setBackground(new Color(75,155,214));
                ficha.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                ficha.setBackground(new Color(75,155,214));
                ficha.setEnabled(false);
                fichasJugador[i][j] = ficha;

                this.jugador.add(ficha);
            }
        }
        add(this.jugador);

        JButton[][]  fichasOponentes = new JButton[10][10];
        this.oponente = new JPanel();
        this.oponente.setPreferredSize(new Dimension(200,200));
        this.oponente.setBounds(0, 0, 20, 20);
        this.oponente.setBackground(Color.ORANGE);
        this.oponente.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
        JPanel[][]  fichasOponente = {};
        for(int i = 0; i<10 ; i++) {
            for(int j = 0; j<10 ; j++) {
                JButton ficha = new JButton();
                ficha.setPreferredSize(new Dimension(20,20));
                ficha.setBounds(0, 0, 10, 10);
                ficha.setBackground(new Color(75,155,214));
                ficha.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                fichasOponentes[i][j] = ficha;
                ficha.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null,"a");
                    }
                });
                this.oponente.add(ficha);

            }
        }
        add(this.oponente);
    }
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }


    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    }
