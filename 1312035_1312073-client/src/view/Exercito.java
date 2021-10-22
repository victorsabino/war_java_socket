package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

@SuppressWarnings("serial")
public class Exercito extends PnlBoneco {
	
	private boolean selecionado = false;
	
	public Exercito(String nome, Color cor) {
		this.nome = nome;
		this.cor = cor;
	}
	
	public void setSelecionado() {
		if (!selecionado) {
			selecionado = true;
		} else {
			selecionado = false;
		}
		repaint();
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void desenhaExercito(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setPaint(getCor());
		g2d.fill(getExercitoShape());

		if (isSelecionado()) {
			g2d.setPaint(Color.white);
		} else {
			g2d.setPaint(Color.DARK_GRAY);
		}		
		
		g2d.draw(getExercitoShape());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		desenhaExercito(g);
	}
}
