package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle.Control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import client.Client;
import client.SerializeData;
import controller.ControllerTabuleiro;

@SuppressWarnings("serial")
public class Configuracao extends JFrame {
	private static Configuracao configuracao;
	private ArrayList<view.Exercito> lstExercitos = new ArrayList<view.Exercito>();
	private ArrayList<view.Exercito> lstJogadores = new ArrayList<Exercito>();
	private boolean selected = false;
	// Bloco de inicialização dos exercitos
		{
			lstExercitos.add(new view.Exercito("Laranja", 	new Color(209, 84, 000)));
			lstExercitos.add(new view.Exercito("Vermelho",	new Color(255, 003, 003)));
			lstExercitos.add(new view.Exercito("Azul", 		new Color(030, 067, 186)));
			lstExercitos.add(new view.Exercito("Verde", 	new Color(006, 124, 000)));
			lstExercitos.add(new view.Exercito("Amarelo", 	new Color(255, 188, 000)));
			lstExercitos.add(new view.Exercito("Preto", 	new Color(000, 000, 000)));
		}

	private Configuracao() {
		exibeExercitos();
		carregabotaoInicio();
		carregaLabel();
		carregaBgJanela();
		configFrame();
	}
	
	private void carregaLabel() {
		JTextArea lblTxtInicio = new JTextArea();
		lblTxtInicio.setBounds(20, 200, 350, 90);
		lblTxtInicio.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblTxtInicio.setForeground(Color.white);
		lblTxtInicio.setText("Selecione pelo menos 3 exércitos \ne clique em iniciar jogo");
		lblTxtInicio.setOpaque(false);
		
		add(lblTxtInicio);
		
	}

	public ArrayList<view.Exercito> getLstexercitos() {
		return lstExercitos;
	}
	
	public boolean validaJogadores() {
		
		// Zerando a lista de jogadores
		lstJogadores.clear();
		
		// Lendro a lista de exercitos, adicionando à lista de jogadores se o exército está selecionado
		for(Exercito e: getLstexercitos()) {
			if(e.isSelecionado()) {
				lstJogadores.add(e);   
				//TO BE IMPLEMENTED (OVERLOAD)
				//SerializeData.getInstance().sendData("selecionaJogador",e.nome);
			}
		}
		
		// Embaralhando a lista de jogadores
		ControllerTabuleiro.embaralhaLista(lstJogadores);
		
		// Se existem mais de 2 jogadores, cria os jogadores no tabuleiroe é o mestre
		if(lstJogadores.size() > 2 ) {			
			for(Exercito e: lstJogadores) {
				ControllerTabuleiro.setJogador(e.getNome(), e.getCor());
			}
			
			// Adiciona os jogadores no pnlJogadores no tabuleiro 
			PnlJogadores.setJogadores(lstJogadores);
			
			// Abre o tabuleiro
			Tabuleiro.getInstance();
			ControllerTabuleiro.getInstance().preparaTabuleiro();
			return true;
		}
		return false;
	}
	
	private void carregabotaoInicio() {
		JButton btnInicializar = new JButton();
		btnInicializar.setText("Iniciar Jogo");
		btnInicializar.setFont(new Font("Stencil", Font.PLAIN, 22));
		btnInicializar.setBounds(95, 470, 200, 60);
		btnInicializar.setBackground(new Color(48,32,19));
		btnInicializar.setForeground(Color.white);
		btnInicializar.setFocusPainted(false);
		btnInicializar.setBorderPainted(false);
		add(btnInicializar);
		
		btnInicializar.addMouseListener(new MouseListener() {
			
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

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(validaJogadores()){
					// Esconde a janela de configuração do jogo.
					SerializeData.getInstance().sendData("init");
					setVisible(false);
				};
							
			}	
		});
	}

	private void carregaBgJanela() {
		// TODO Auto-generated method stub
		String Imagem = "src/resources/Images/bgConfiguracao.png";
		JLabel bgJanela = new JLabel();
		ImageIcon imgJanela = new ImageIcon(Imagem);

		imgJanela = new ImageIcon(imgJanela.getImage().getScaledInstance(imgJanela.getIconWidth(), imgJanela.getIconHeight(),BufferedImage.SCALE_SMOOTH));
		bgJanela.setIcon(imgJanela); // Imagem de fundo da janela

		bgJanela.setSize(imgJanela.getIconWidth(), imgJanela.getIconHeight());

		getContentPane().add(bgJanela);

		repaint();
	}

	public static Configuracao getInstance() {
		if (configuracao == null) {
			configuracao = new Configuracao();
		}
		return configuracao;
	}

	private void configFrame() {

		int LARG_DEFAULT = 400;
		int ALT_DEFAULT = 680;

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();

		double sl = screenSize.getWidth(); // Largura da tela
		double sa = screenSize.getHeight(); // Altura da tela

		int x = (int) sl / 2 - LARG_DEFAULT / 2; // posicionamento centralizado
													// em x da tela
		int y = (int) sa / 2 - ALT_DEFAULT / 2; // posicionamentro centralizado
												// em y da tela

		setLayout(null);
		setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setResizable(false);
		setVisible(true);
	}
	public void initGame(){
		 		if(validaJogadores()){
		 			// Esconde a janela de configuração do jogo.
		 			setVisible(false);
		 		};
		 	}
	public void exibeExercitos() {
		
		JPanel pnlExercitos = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
		pnlExercitos.setOpaque(false);
		pnlExercitos.setSize(360,90);
		pnlExercitos.setLocation(20, 113);
		
		for (view.Exercito ex : getLstexercitos()) {

			ex.setLocation(0,0);
			ex.setPreferredSize(new Dimension(60,90));
			ex.setOpaque(false);
			ex.setToolTipText("Exercito " + ex.getNome());			
			ex.addMouseListener(new MouseListener() {
				
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
					if(selected == false){
						ex.setSelecionado();
						System.out.println("jogador 0");
						repaint();
						//comment this line to play just in one window
						//selected = true;
						//ControllerTabuleiro.setClientPlayer(ex);
						selected = true;
						ControllerTabuleiro.setMyself(ex);
						SerializeData.getInstance().sendData("selectPlayer", ex.getNome());
						//selecionar jogadores
					}
					
				}
			});
			
			pnlExercitos.add(ex);
		}
		
		add(pnlExercitos);

	}
		
}

