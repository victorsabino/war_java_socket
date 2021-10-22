package view;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.Continente;
import model.Territorio;
import controller.ControllerTabuleiro;


@SuppressWarnings("serial")
public class PnlMapa extends JPanel implements Observer {

	private static PnlMapa mapa;
	ControllerTabuleiro controller = ControllerTabuleiro.getInstance();

	private PnlMapa() {
		controller.addObserver(this);
		
		setLayout(null);
		
		for(Continente c: controller.getLstContinentes()) {
			for(Territorio t: c.getLstTerritorios()) {
				add(t.getLblNumSoldados());
			}
		}
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				controller.pnlMapa_click(e.getX(), e.getY(), e.getButton());
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
			}
		});
	}
	
	public static PnlMapa getInstance() {
		if(mapa == null) {
			mapa = new PnlMapa();
		}
		return mapa;
	}

	private void desenhaMapa(Graphics g) {
		
		for(Continente c: controller.getLstContinentes()) {
			for(Territorio t: c.getLstTerritorios()){
				t.atualizaLblnumsoldados();
			}
		}
		
		Color corBg = null;
		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		for(Continente c : controller.getLstContinentes()) {			
			
			corBg = c.getCor();
			
			// Guarda a lista de territorios do continente em lstTerritorios.
			List<Territorio> lstTerritorios = c.getLstTerritorios();
			
			for(Territorio t : lstTerritorios) {
				
				// Pintando os territorios marcados como ativo
				if (controller.getTerritorioOrigem() == t || controller.getTerritorioDestino() == t) {
					 corBg = c.getCor().brighter().brighter();
				} else {
					corBg = c.getCor();
				}
				
				if(controller.getJogadaAtual() != null){
					// Pintando os territorios marcados como fronteira, se jogada for de ataque
					if(controller.getJogadaAtual().getNome() == "Atacar") {
						if(
								controller.getTerritorioOrigem() != null // Se houver territorio de origem
								&& controller.getTerritorioOrigem().getLstFronteiras().contains(t) // & o territorio clicado estiver na lista de territorios de fronteiras do territorio de origem
								&& controller.getTerritorioDestino() != t // & o territorio clicado não for o territorio de destino 
								&& controller.getTerritorioOrigem().getLstSoldados().get(0).getExercito() != t.getLstSoldados().get(0).getExercito() // & O territorio clicado nao pertencer ao jogador atual
						) {
							corBg = c.getCor().brighter();	
						}
					} else if(controller.getJogadaAtual().getNome() == "Remanejar") {
						if(
								controller.getTerritorioOrigem() != null // Se houver territorio de origem
								&& controller.getTerritorioOrigem().getLstFronteiras().contains(t) // & o territorio clicado estiver na lista de territorios de fronteiras do territorio de origem
								&& controller.getTerritorioDestino() != t // & o territorio clicado não for o territorio de destino 
								&& controller.getTerritorioOrigem().getLstSoldados().get(0).getExercito() == t.getLstSoldados().get(0).getExercito() // & O territorio clicado nao pertencer ao jogador atual
						) {
							corBg = c.getCor().brighter();	
						}
					}
				
						g2d.setPaint(corBg);
						g2d.fill(t.getShape());
						g2d.setPaint(Color.black);
						g2d.draw(t.getShape());
						
						t.desenhaPnlSoldados(g);
					
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		desenhaMapa(g);
	}

	
	@Override
	public void update(Observable o, Object arg) {
		repaint();		
	}


}
