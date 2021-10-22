package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import controller.ControllerTabuleiro;

@SuppressWarnings("serial")
public class Jogador extends PnlBoneco implements Observer {
	
	private ControllerTabuleiro controller = ControllerTabuleiro.getInstance();
	private PnlIndicadorJogador indicador = PnlIndicadorJogador.getInstance();
	private JLabel lblQtdSoldadosReserva = new JLabel();
	
	public Jogador(String nome, Color cor) {
		controller.addObserver(this);
		this.nome = nome;
		this.cor = cor;
		setToolTipText("Jogador " + getNome());
	}
	
	public void desenhaExercito(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		// Define a cor do preenchimento do shape, de acordo com o número de territorios possuidos.
		// Se não possui nenhum, é cinza (está fora do jogo), se possui, é a cor do exercito.
		if(! controller.possuiTerritorio(this.nome)) {
			g2d.setPaint(Color.GRAY);
		} else {
			g2d.setPaint(getCor());
		}
		
		// Preenche o shape
		g2d.fill(getExercitoShape());		
		
		
		if(controller.possuiTerritorio(this.nome)){
			// Seta a cor comop preto
			g2d.setPaint(Color.lightGray);
		} else {
			g2d.setPaint(Color.GRAY);
		}
		
		
		// Desenha o contorno do shape do territorio
		g2d.draw(getExercitoShape());
		
		// Chama a função para inserir o indicador caso seja o jogador ativo.
		marcaPnljogadorAtivo();
		
		// Se houver soldados na reserva, exibe o label com a quantidade de soldados na reserva.
		// Caso contrário, esconde o label
		if(controller.getQtdSoldadosReserva(this.nome) > 0 && controller.possuiTerritorio(this.nome)) {
			lblQtdSoldadosReserva.setVisible(true);
		} else {
			lblQtdSoldadosReserva.setVisible(false);
		}
		
		// Configura e adicionna o label de quantidade de soldados na reserva
		lblQtdSoldadosReserva.setForeground(Color.white);
		lblQtdSoldadosReserva.setText("+ "+controller.getQtdSoldadosReserva(this.nome));
		lblQtdSoldadosReserva.setBackground(Color.BLACK);
		lblQtdSoldadosReserva.setLocation(5,4);
		lblQtdSoldadosReserva.setSize(50,30);
		lblQtdSoldadosReserva.setOpaque(false);
		
		// Adiciona o label de qtd de soldados na reserva;
		add(lblQtdSoldadosReserva);

	}
	
	private void marcaPnljogadorAtivo() {
		if(controller.getJogadorDaVez() != null)
			if(controller.getJogadorDaVez().getNome() == this.nome) {
				add(indicador);
			}		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		desenhaExercito(g);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		marcaPnljogadorAtivo();
		repaint();		
	}

}
