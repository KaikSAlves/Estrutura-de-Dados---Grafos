package view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import entities.Aresta;
import entities.Grafo;
import entities.Vertice;

public class Canvas extends JPanel{
	private Grafo grafo;
	
	public Canvas(Grafo grafo) {
		this.grafo = grafo;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int maxWidthToGerenate = 1100;
		int maxHeightToGenerate = 550;
		int n = 1;
		List<Circle> circles = new ArrayList<Circle>();
		List<Vertice> vertices = grafo.getVertices();
		List<Aresta> arestas = grafo.getArestas();
		
		Random rand = new Random();
		Point pontoA;
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		pontoA = new Point(rand.nextInt(150, maxWidthToGerenate), rand.nextInt(50, maxHeightToGenerate));
		
		Circle circleOne = new Circle(pontoA, vertices.get(0));
		circles.add(new Circle(pontoA, vertices.get(0)));
		g2.setColor(Color.black);
		g2.fillOval(circles.get(0).getPoint().x, circles.get(0).getPoint().y, 50,50);
		g2.setColor(Color.white);
		g2.drawString((String) circleOne.getVertice().getDado().getNome(), circleOne.getPoint().x + 15, circleOne.getPoint().y + 30);
		
		while (n < vertices.size()) {
			pontoA = new Point(rand.nextInt(150, maxWidthToGerenate), rand.nextInt(50, maxHeightToGenerate));
			Circle circle = new Circle(pontoA, vertices.get(n));
			if (positionIsValid(circles, circle)) {
				circles.add(circle);
				g2.setColor(Color.black);
				g2.fillOval(circle.getPoint().x, circle.getPoint().y, 50,50);
				g2.setColor(Color.white);
				g2.drawString((String) circle.getVertice().getDado().getNome(), circle.getPoint().x + 15, circle.getPoint().y + 30);
				n++;
			}
		}
		
		for (int i = 0; i < arestas.size(); i++) {
			Vertice inicio = arestas.get(i).getInicio();
			Vertice fim = arestas.get(i).getFim();
			Circle circleInicio = null;
			Circle circleFim = null;

			for (Circle c : circles) {
				if (c.getVertice().equals(inicio)) {
					circleInicio = c;
				}

				if (c.getVertice().equals(fim)) {
					circleFim = c;
				}
			}
			
			Point startPoint = circleInicio.getPoint();
			Point endPoint = circleFim.getPoint();

			g2.setColor(Color.black);
			g2.drawLine(startPoint.x + 10, startPoint.y + 10, endPoint.x + 10, endPoint.y + 10);
		}
		
	}
	
	private boolean positionIsValid(List<Circle> circles, Circle circle) {
		int x = circle.getX();
		int y = circle.getY();
		boolean valid = false;

		for (int i = 0; i < circles.size(); i++) {
			System.out.println(circles.get(i).getPoint().distance(circle.getPoint()));
			if (circles.get(i).getPoint().distance(circle.getPoint()) > 150) {
				valid = true;
			} else {
				valid = false;
				break;
			}
		}
		System.out.println(valid);
		return valid;
	}
}
