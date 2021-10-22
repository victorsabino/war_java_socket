package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.SerializeData;
import model.Config;
import controller.ControllerTabuleiro;

@SuppressWarnings("serial")
public class Tabuleiro extends JFrame implements Observer {

	private static Tabuleiro tabuleiro;

	private JLabel pnlCartasIcone = new JLabel();

	// Carregando singletons
	PnlMapa mapa = PnlMapa.getInstance();
	Console console = Console.getInstance();
	ControllerTabuleiro controller = ControllerTabuleiro.getInstance();
	PnlJogadas pnlJogadas = PnlJogadas.getInstance();
	PnlDados pnlDados = PnlDados.getInstance();
	LblBonusContinente lblBonusContinente = LblBonusContinente.getInstance();
	PnlTabelaTroca pnlTabelaTroca = PnlTabelaTroca.getInstance();
	PnlModalCartas pnlModalCartas = PnlModalCartas.getInstance();
	PnlJogadores pnlJogadores = PnlJogadores.getInstance();
	
	// Construtor
	private Tabuleiro() {
		controller.addObserver(this);
		configFrame();
		carregaPnlCartasIcone();
		carregaBtnProximoJogada();
		carregaLinhasMapa();
		carregaDados();
		carregaConsole();
		carregaJogadores();
		carregaPnlJogadas();
		carregaTabelaTroca();
		carregaBonusContinente();		
		carregaMapa();
		carregaBarraInferior();
		carregaBgTabuleiro();
	}

	private void carregaBonusContinente() {
		add(lblBonusContinente);		
	}
	
	private void carregaTabelaTroca() {
		add(pnlTabelaTroca);	
	}

	private void carregaPnlJogadas() {
		
		add(pnlJogadas);
	}
	
	private void carregaPnlCartasIcone() {
		pnlCartasIcone.setLocation(252, 670);
		pnlCartasIcone.setToolTipText("Exibir as cartas");

		ImageIcon imgCartasIcone = new ImageIcon(
				"src/resources/Images/war_iconeCartas.png");

		imgCartasIcone = new ImageIcon(imgCartasIcone.getImage().getScaledInstance(imgCartasIcone.getIconWidth(),imgCartasIcone.getIconHeight(),BufferedImage.SCALE_SMOOTH));
		pnlCartasIcone.setIcon(imgCartasIcone); // Imagem das cartas

		pnlCartasIcone.setSize(imgCartasIcone.getIconWidth(),imgCartasIcone.getIconHeight());

		add(pnlCartasIcone).repaint();

		pnlCartasIcone.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				// Só pode abrir o painel enquanto a jogada atual for de distribuição
				if(controller.getJogadaAtual().getNome() == "Distribuir") {
					if(pnlModalCartas.isVisible()) {
						if(controller.getJogadorDaVez().getLstCartas().size() < 5)
							pnlModalCartas.setVisible(false);
					} else {
						pnlModalCartas.setVisible(true);
					}
				}			
			}
		});
		
		add(pnlCartasIcone);
		add(pnlModalCartas);
		repaint();
	}

	private void carregaDados() {

		JButton btnJogardados = new JButton();
		ImageIcon imgBtnJogarDados = new ImageIcon("src/resources/images/war_btnJogarDados.png");
		imgBtnJogarDados = new ImageIcon(imgBtnJogarDados.getImage().getScaledInstance(imgBtnJogarDados.getIconWidth(),	imgBtnJogarDados.getIconHeight(), BufferedImage.SCALE_SMOOTH));

		btnJogardados.setLocation((1024 / 2) - 60 / 2, 600);
		btnJogardados.setSize(60, 40);
		btnJogardados.setOpaque(false);
		btnJogardados.setContentAreaFilled(false);
		btnJogardados.setBorderPainted(false);
		btnJogardados.setFocusPainted(false);
		btnJogardados.setIcon(imgBtnJogarDados);
		btnJogardados.setToolTipText("Jogar dados");
		btnJogardados.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnJogardados.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				controller.btnJogarDados_click();

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
				
			}
		});
		;

		add(btnJogardados);		
		
		add(pnlDados);

		repaint();
	}

	public void carregaJogadores() {

		add(pnlJogadores);

	}

	private void carregaBarraInferior() {
		JLabel lblBarraInferior = new JLabel();
		ImageIcon imgBarraInferior = new ImageIcon(
				"src/resources/images/war_tabuleiro_bottom.png");

		imgBarraInferior = new ImageIcon(imgBarraInferior.getImage()
				.getScaledInstance(imgBarraInferior.getIconWidth(),
						imgBarraInferior.getIconHeight(),
						BufferedImage.SCALE_SMOOTH));
		lblBarraInferior.setIcon(imgBarraInferior);
		lblBarraInferior.setToolTipText("Barra inferior");

		lblBarraInferior.setBounds(0, 768 - imgBarraInferior.getIconHeight(),
				imgBarraInferior.getIconWidth(),
				imgBarraInferior.getIconHeight());

		add(lblBarraInferior);

		repaint();

	}

	private void carregaConsole() {
		console.setBounds(625, 685, 393, 51);
		console.setOpaque(false);
		add(console);

		repaint();

	}

	private void carregaLinhasMapa() {

		JLabel linhasMapa = new JLabel();
		ImageIcon imgTabuleiro = new ImageIcon(
				"src/resources/Images/war_tabuleiro_linhas.png");

		imgTabuleiro = new ImageIcon(imgTabuleiro.getImage().getScaledInstance(
				imgTabuleiro.getIconWidth(), imgTabuleiro.getIconHeight(),
				BufferedImage.SCALE_SMOOTH));
		linhasMapa.setIcon(imgTabuleiro); // Imagem de fundo do tabuleiro

		linhasMapa.setSize(imgTabuleiro.getIconWidth(),
				imgTabuleiro.getIconHeight());

		add(linhasMapa).repaint();

		repaint();

	}

	private void carregaMapa() {
		mapa.setSize(1024,768);
		mapa.setOpaque(false);
		add(mapa);
		repaint();
	}

	private void configFrame() {

		setTitle("WAR PUC-Rio");
		setResizable(false);
		setVisible(true);

		// Seta o LayoutManager como null
		setLayout(null);

		// Posicionamento e tamanho do JFrame (janela)
		setBounds(Config.x, Config.y, Config.LARG_DEFAULT, Config.ALT_DEFAULT);

		// Ação padrão ao fechar a janela
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		repaint();

	}

	private void carregaBgTabuleiro() {

		JLabel tabuleiro = new JLabel();
		ImageIcon imgTabuleiro = new ImageIcon(
				"src/resources/Images/war_tabuleiro_fundo.png");

		imgTabuleiro = new ImageIcon(imgTabuleiro.getImage().getScaledInstance(imgTabuleiro.getIconWidth(), imgTabuleiro.getIconHeight(), BufferedImage.SCALE_SMOOTH));
		tabuleiro.setIcon(imgTabuleiro); // Imagem de fundo do tabuleiro
		tabuleiro.setToolTipText("Água");

		tabuleiro.setSize(imgTabuleiro.getIconWidth(),imgTabuleiro.getIconHeight());
		add(tabuleiro).repaint();
	}

	private void carregaBtnProximoJogada() {

		Font fonteTitulo = new Font("Stencil", Font.PLAIN, 35);
		JButton btnProxJogada = new JButton();
		
		ImageIcon imgBtnProxJogada = new ImageIcon("src/resources/Images/war_btnProxJogada.png");
		imgBtnProxJogada = new ImageIcon(imgBtnProxJogada.getImage().getScaledInstance(imgBtnProxJogada.getIconWidth(),	imgBtnProxJogada.getIconHeight(), BufferedImage.SCALE_SMOOTH));

		btnProxJogada.setIcon(imgBtnProxJogada);
		btnProxJogada.setToolTipText("Passar para a proxima jogada");
		btnProxJogada.setOpaque(false);
		btnProxJogada.setFont(fonteTitulo);
		btnProxJogada.setForeground(Color.white);
		btnProxJogada.setContentAreaFilled(false);
		btnProxJogada.setBorderPainted(false);
		btnProxJogada.setFocusPainted(false);

		btnProxJogada.setBounds(345, 683, 60, 50);

		btnProxJogada.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

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
				
				if(controller.getJogadaAtual().getNome() == "Distribuir" && controller.getJogadorDaVez().getLstCartas().size() > 4 ) {
					
				} else {
					pnlModalCartas.setVisible(false);
					controller.btnProxJogada_click();
				}
				SerializeData.getInstance().sendData("Next");
			}

		});

		add(btnProxJogada);
	}

	public static Tabuleiro getInstance() {

		if (tabuleiro == null) {
			tabuleiro = new Tabuleiro();
		}

		return tabuleiro;

	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
		if(controller.getJogadaAtual() != null && controller.getJogadorDaVez() != null && controller.getJogadorDaVez().getLstCartas().size() > 4 && controller.getJogadaAtual().getNome()=="Distribuir") {
			pnlModalCartas.setVisible(true);
		}
		
	}

}
