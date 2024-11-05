package view;

import javax.swing.JFrame;

import entities.Grafo;
import view.components.Canvas;

public class Screen extends JFrame {
	public boolean running;

	public Screen(Grafo grafo) {
		setResizable(true);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		Canvas canvas = new Canvas(grafo);
		
		add(canvas);

		setVisible(true);
	}

	public void run() {
		this.running = true;
		setVisible(true);
	}

	public void close() {
		this.running = false;
		dispose();
	}
}
