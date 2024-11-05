package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JLabel;

import entities.Vertice;

public class Circle extends JLabel {
    private Point ponto;
    private Vertice vertice;

    public Circle(Point a, Vertice v){
        this.ponto = a;
        this.vertice = v;
        
        setSize(60, 60);
        
        setMaximumSize(new Dimension(60,60));
        setLocation(a.x, a.y);
    }

    public Point getPoint() {
        return ponto;
    }

    public void setPoint(Point a) {
        this.ponto = a;
    }

    public Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Vertice v) {
        this.vertice = v;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        g2d.fillRoundRect(0,0, getWidth(), getHeight(), 100, 100);

        /*
        if (image != null) {
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
        */

        g2d.dispose();
        //g.fillOval(0,0, 50, 50);
    }
}
