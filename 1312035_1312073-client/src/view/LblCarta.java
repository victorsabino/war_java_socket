package view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LblCarta extends JLabel {
	
	private boolean trocar;
	private ImageIcon imagemCarta;
	private ImageIcon imgPlaceholder;
	private String nomeTerritorio;
	private int simbolo;

	{
		imgPlaceholder = new ImageIcon("src/resources/images/war_carta_placeholder.png");
	}
	
	public LblCarta() {
		imagemCarta = imgPlaceholder;
		trocar = false;
		setPreferredSize(new Dimension(imagemCarta.getIconWidth(), imagemCarta.getIconHeight()));
		setOpaque(true);
		setToolTipText("sem carta");
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(imagemCarta.toString() != imgPlaceholder.toString() ) {
					if(isTrocar()) {
						setTroca(false);
					} else {
						setTroca(true);
					}
				}
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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public int getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(int simbolo) {
		this.simbolo = simbolo;
	}
	
	public ImageIcon getImagemCarta(){
		return imagemCarta;
	}
	
	public void setImagem(ImageIcon imgcarta) {
		imagemCarta = imgcarta;
	}
	
	public void setNomeTerritorio(String nome) {
		this.nomeTerritorio = nome;
	}
	
	public String getNomeTerritorio() {
		return this.nomeTerritorio;
	}
	
	public void setTroca(boolean b) {
		trocar = b;
		
		setBorder(null);
	
		if(isTrocar()) {
			setBorder(BorderFactory.createLoweredBevelBorder());
		}
	}
	
	public boolean isTrocar() {
		return trocar;
	}
	
}
