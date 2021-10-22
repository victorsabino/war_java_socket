package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerTabuleiro;
import model.Config;

@SuppressWarnings("serial")
public class PnlJogadas extends JPanel implements Observer{
	
	private static PnlJogadas jogadas;

	private JLabel lblJogadaDistribuir = new JLabel();
	private JLabel lblJogadaAtacar = new JLabel();
	private JLabel lblJogadaRemanejar = new JLabel();
	
	ControllerTabuleiro controller = ControllerTabuleiro.getInstance();

	private PnlJogadas() {
		
		controller.addObserver(this);
		Font fonte = new Font("Stencil", Font.PLAIN, 20);

		// Definindo os textos dos labels
		lblJogadaDistribuir.setText(controller.getLstJogadas().get(0).getNome());
		lblJogadaAtacar.setText(controller.getLstJogadas().get(1).getNome());
		lblJogadaRemanejar.setText(controller.getLstJogadas().get(2).getNome());

		lblJogadaDistribuir.setFont(fonte);
		lblJogadaAtacar.setFont(fonte);
		lblJogadaRemanejar.setFont(fonte);
		
		lblJogadaDistribuir.setBorder(new EmptyBorder(5, 10, 5, 10));
		lblJogadaAtacar.setBorder(new EmptyBorder(5, 10, 5, 10));
		lblJogadaRemanejar.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		lblJogadaDistribuir.setBackground(new Color(48,32,19));
		lblJogadaAtacar.setBackground(new Color(48,32,19));
		lblJogadaRemanejar.setBackground(new Color(48,32,19));
		
		lblJogadaDistribuir.setForeground(Color.WHITE);
		lblJogadaAtacar.setForeground(Color.WHITE);
		lblJogadaRemanejar.setForeground(Color.WHITE);

		lblJogadaDistribuir.setToolTipText("Clique no território desejado quantas vezes forem os soldados que desejar adicionar");
		lblJogadaAtacar.setToolTipText("Clique no território atacante e em seguida no atacado. Feito isso, jogue os dados");
		lblJogadaRemanejar.setToolTipText("Clique com o botão esquerdo no territorio doador e com o botão direito no território receptor, quantas vezes forem os soldados que desejar remanejar para ele");
		
		
		setLayout(new FlowLayout());
		setOpaque(false);
		setLocation((Config.LARG_DEFAULT / 2) - (300), 20);
		setSize(600,200);

		add(lblJogadaDistribuir);
		add(lblJogadaAtacar);
		add(lblJogadaRemanejar);
		atualizaIndicadorJogada();
	}
	
	public void atualizaIndicadorJogada() {
		
		lblJogadaDistribuir.setOpaque((controller.getLstJogadas().get(0).isAtivo() ? true : false));
		lblJogadaAtacar.setOpaque((controller.getLstJogadas().get(1).isAtivo() ? true : false));
		lblJogadaRemanejar.setOpaque((controller.getLstJogadas().get(2).isAtivo() ? true : false));

	}
	
	public static PnlJogadas getInstance() {
		if(jogadas == null) {
			jogadas = new PnlJogadas();
		}
		return jogadas;
	}

	@Override
	public void update(Observable o, Object arg) {
		atualizaIndicadorJogada();
		
	}
}
