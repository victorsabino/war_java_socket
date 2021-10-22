package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.ControllerTabuleiro;

@SuppressWarnings("serial")
public class LblDado extends JLabel implements Observer {
	
	private ControllerTabuleiro controller = ControllerTabuleiro.getInstance();
	private ImageIcon imagem;
	private char tipo;
	private int ordem;
	private int numero = 0;
	
	public LblDado(char tipo, int ordem) {
		
		controller.addObserver(this);
		
		this.tipo = tipo;
		this.ordem = ordem;
		
		CarregaImagem();
		
		setOpaque(false);		
		setPreferredSize(new Dimension(32,32));
	}
	
	private void getNumero() {
		numero = controller.getNumeroDado(tipo, ordem);
	}

	private void CarregaImagem() {		
		
		getNumero();
		
		// Se for do tipo ataque, seta as imagens dos dados de ataque.
		if(tipo == 'a') {
			
			if(numero == 1) {
				imagem = new ImageIcon("src/resources/images/dado_ataque_1.png");
			}
			
			if(numero == 2) {
				imagem = new ImageIcon("src/resources/images/dado_ataque_2.png");
			}

			if(numero == 3) {
				imagem = new ImageIcon("src/resources/images/dado_ataque_3.png");
			}

			if(numero == 4) {
				imagem = new ImageIcon("src/resources/images/dado_ataque_4.png");
			}

			if(numero == 5) {
				imagem = new ImageIcon("src/resources/images/dado_ataque_5.png");
			}

			if(numero == 6) {
				imagem = new ImageIcon("src/resources/images/dado_ataque_6.png");
			}
		}
		
		// Se for do tipo defesa, seta as imagens dos dados de defesa.
		else if(tipo == 'd') {
			if(numero == 1) {
				imagem = new ImageIcon("src/resources/images/dado_defesa_1.png");
			}
			
			if(numero == 2) {
				imagem = new ImageIcon("src/resources/images/dado_defesa_2.png");
			}

			if(numero == 3) {
				imagem = new ImageIcon("src/resources/images/dado_defesa_3.png");
			}

			if(numero == 4) {
				imagem = new ImageIcon("src/resources/images/dado_defesa_4.png");
			}

			if(numero == 5) {
				imagem = new ImageIcon("src/resources/images/dado_defesa_5.png");
			}

			if(numero == 6) {
				imagem = new ImageIcon("src/resources/images/dado_defesa_6.png");
			}
		} 
		
		if(numero == 0) {
			imagem = new ImageIcon("src/resources/images/dado_desativado.png");
		}
		
		imagem = new ImageIcon(imagem.getImage().getScaledInstance(imagem.getIconWidth(), imagem.getIconHeight(), BufferedImage.SCALE_SMOOTH));
		setIcon(imagem);
		
		String tipoString = null;
		
		if(tipo == 'a') {
			tipoString = "de ataque ";
		}
		
		else if(tipo == 'd') {
			tipoString = "de defesa ";
		}
		
		if(numero == 0) {
			tipoString += "não utilizado";
		}
		
		setToolTipText("Dado " + tipoString);
		
		setSize(imagem.getIconWidth(), imagem.getIconHeight());
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		CarregaImagem();
		
	}	

}
