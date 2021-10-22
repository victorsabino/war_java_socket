package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ControllerTabuleiro;

@SuppressWarnings("serial")
public class PnlTabelaTroca extends JPanel implements Observer{
	
	private static PnlTabelaTroca lblTabelaTroca;
	private JLabel lblQtdProxTroca = new JLabel();
	private ControllerTabuleiro controller = ControllerTabuleiro.getInstance();
	
	private PnlTabelaTroca() {
		
		controller.addObserver(this);
		
		JLabel lblTabelaTrocaTitulo = new JLabel("Troca", SwingConstants.LEFT);
		JLabel lblTabelaTroca = new JLabel();
		
		lblTabelaTrocaTitulo.setSize(100, 30);
		lblTabelaTrocaTitulo.setForeground(Color.white);
		lblTabelaTrocaTitulo.setLocation(0,0);
		
		lblQtdProxTroca.setSize(100,20);
		lblQtdProxTroca.setForeground(Color.white);
		lblQtdProxTroca.setLocation(0, 0);
		lblQtdProxTroca.setText("teste");
			
		ImageIcon imgTabelaTroca = new ImageIcon("src/resources/images/war_tabela_troca.png");
		imgTabelaTroca = new ImageIcon(imgTabelaTroca.getImage().getScaledInstance(imgTabelaTroca.getIconWidth(), imgTabelaTroca.getIconHeight(), BufferedImage.SCALE_SMOOTH));
		lblTabelaTroca.setIcon(imgTabelaTroca); // Imagem de fundo do tabuleiro
		lblTabelaTroca.setSize(imgTabelaTroca.getIconWidth(), imgTabelaTroca.getIconHeight());
		lblTabelaTroca.setLocation(0,20);
		
		add(lblTabelaTrocaTitulo);
		add(lblTabelaTroca);
		add(lblQtdProxTroca);
		
		setSize(imgTabelaTroca.getIconWidth(), lblTabelaTrocaTitulo.getHeight()+lblTabelaTroca.getHeight()+lblQtdProxTroca.getHeight());
		setLocation(5, 500);
		setOpaque(false);
	}
	
	public static PnlTabelaTroca getInstance() {
		if(lblTabelaTroca == null) {
			lblTabelaTroca = new PnlTabelaTroca();
		}
		return lblTabelaTroca;
	}
	
	private void atualizaLblQtdProximaTroca() {
		if (controller.getQtdTroca() > 0) {
			lblQtdProxTroca.setText("Proxima troca: + "+ controller.getQtdTroca());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		atualizaLblQtdProximaTroca();		
	}
}
