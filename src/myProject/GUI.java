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
    private JButton empezar;


    public GUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 50,65));
        contentPane.setBounds(0, 0, 20, 20);
        this.setSize(new Dimension(780,400));
        //this.setBounds(0, 0, 850, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        inicialize();
        this.setVisible(true);
        this.setLayout(null);
    }

    private void inicialize() {

        GridBagConstraints constraints = new GridBagConstraints();
        this.jugador = new JPanel();
        this.jugador.setPreferredSize(new Dimension(200,200));
        this.jugador.setBounds(0, 0, 20, 20);
        this.jugador.setBackground(Color.red);
        this.jugador.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        empezar = new JButton("Empezar");
        empezar.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(empezar);
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
        this.oponente.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        String[]  fichasOponente = {"a","b","c","d","e","f","g","h","i","j"};
        for(int i = 0; i<10 ; i++) {
            for(int j = 1; j<=10 ; j++) {
                JButton ficha = new JButton();
                ficha.setPreferredSize(new Dimension(20,20));
                ficha.setBounds(0, 0, 10, 10);
                ficha.setBackground(new Color(75,155,214));
                ficha.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                ficha.setText(fichasOponente[i] + j);
                ficha.setForeground(new Color(75,155,214));
                fichasOponentes[i][j-1] = ficha;
                
                ficha.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null,ficha.getText());
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
