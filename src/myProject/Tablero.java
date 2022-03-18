package myProject;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(41231));
        g.fillRect(0,0,100,100);
    }
}
