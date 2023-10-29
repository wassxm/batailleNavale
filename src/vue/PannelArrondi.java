package src.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class PannelArrondi extends JPanel {
	private static final long serialVersionUID = 1L;
	private Color c;
	public PannelArrondi() {
		super();
		this.c = Color.BLUE;
		
	}
	public PannelArrondi(Color c ) {
		super();
		this.c =c;
		
	}
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.setColor(c);
        g.fillOval(0, 0, getWidth(), getHeight()); // dessine un cercle rempli

        
    }
	public void changecolor(Color c ) {
		this.c = c ;
		repaint();
	}
	
}
