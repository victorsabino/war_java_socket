package view;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LblBonusContinente extends JLabel {
	
	private static LblBonusContinente lblBonusContinente;
	
	private LblBonusContinente() {
		ImageIcon imgTabelaBonus = new ImageIcon("src/resources/Images/war_tabela_bonus_continente.png");

		imgTabelaBonus = new ImageIcon(imgTabelaBonus.getImage().getScaledInstance(imgTabelaBonus.getIconWidth(), imgTabelaBonus.getIconHeight(), BufferedImage.SCALE_SMOOTH));
		setIcon(imgTabelaBonus); // Imagem de fundo do tabuleiro
		setSize(130, 120);
		setLocation(5,380);
	}
	
	public static LblBonusContinente getInstance() {
		if(lblBonusContinente == null) {
			lblBonusContinente = new LblBonusContinente();
		}
		return lblBonusContinente;
	}
}
