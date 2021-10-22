package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.ControllerTabuleiro;

@SuppressWarnings("serial")
public class PnlModalCartas extends JPanel implements Observer {
	private static PnlModalCartas pnlModalCartas;
	private ControllerTabuleiro controller = ControllerTabuleiro.getInstance();
	
	JPanel pnlCartasTroca = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
	JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	JButton btnEfetuarTroca = new JButton("Trocar");
	JButton btnCancelarTroca = new JButton("Cancelar");
	
	private ArrayList<LblCarta> lstLblCartas = new ArrayList<LblCarta>();
	private ArrayList<LblCarta> lstTroca = new ArrayList<LblCarta>();
	
	private LblCarta lblCarta1 = new LblCarta();
	private LblCarta lblCarta2 = new LblCarta();
	private LblCarta lblCarta3 = new LblCarta();
	private LblCarta lblCarta4 = new LblCarta();
	private LblCarta lblCarta5 = new LblCarta();
	private JLabel lblCartaObjetivo = new JLabel();
	
	private JLabel lblTituloCartas = new JLabel();
	private JLabel lblTituloObjetivo = new JLabel();
	
	//Bloco de inicialização das cartas e botões
	{		
		lstLblCartas.add(lblCarta1);
		lstLblCartas.add(lblCarta2);
		lstLblCartas.add(lblCarta3);
		lstLblCartas.add(lblCarta4);
		lstLblCartas.add(lblCarta5);
		
		btnCancelarTroca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(controller.getJogadorDaVez().getLstCartas().size() < 5) {
					setVisible(false);
				}
			}
		});
		btnEfetuarTroca.addActionListener(new ActionListener() {
			
//			HashSet<LblCarta> lstAuxLblcarta = new HashSet<LblCarta>(lstLblCartas);			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				lstTroca.clear(); // Limpando a lista de troca
				
				// Construindo a lista de troca para
				for(LblCarta c: lstLblCartas ){
					if(c.isTrocar()) {
						lstTroca.add(c);
					}
				}
				
				// Se a lista de troca contiver 03 e somente 03 cartas
				if(lstTroca.size() == 3) {
					
					int s1 = lstTroca.get(0).getSimbolo();
					int s2 = lstTroca.get(1).getSimbolo();
					int s3 = lstTroca.get(2).getSimbolo();
					
					if (isSimboloIgual(s1, s2, s3)) {
						preparaTroca();
					} else if(isSimboloDiferente(s1, s2, s3)) {
						preparaTroca();
					} else {
						controller.setMensagem("Os simbolos das cartas precisam ser todos iguais ou diferentes.");
					}				
					
				} else {
					controller.setMensagem("Escolha três e somente três cartas.");
				}
				
			}
		});
	
		Color corfundoBtn = new Color(48, 32, 19);
		
		btnCancelarTroca.setMargin(new Insets(5,5,5,5));
		btnEfetuarTroca.setMargin(new Insets(5,5,5,5));
		
		btnCancelarTroca.setBackground(corfundoBtn);
		btnEfetuarTroca.setBackground(corfundoBtn);
		btnCancelarTroca.setForeground(Color.white);
		btnEfetuarTroca.setForeground(Color.white);
		btnCancelarTroca.setFocusable(false);
		btnEfetuarTroca.setFocusable(false);
	}
	
	private boolean isSimboloIgual(int s1, int s2, int s3) {
		
		if(s1 == 4) {
			if(s2 != 4) {
				s1 = s2;
			} else if(s3 != 4) {
				s1 = s3;
			}
		}
		
		if(s2 == 4) {
			if(s1 != 4) {
				s2 = s1;
			} else if(s3 != 4) {
				s2= s3;
			}
		}
		
		if(s3 == 4) {
			if(s1 != 4) {
				s3 = s1;
			} else if(s2 != 4) {
				s3 = s2;
			}
		}
		
		
		if(s1 == s2 && s2 == s3) {
			return true;
		}
		
		return false;
	}
	
	private boolean isSimboloDiferente(int s1, int s2, int s3) {
		
		if(s1 != s2 && s1 != s3 && s2 != s3) {
			return true;
		}		
		
		return false;		
	}
	
	private void preparaTroca() {
		String cartasNomeTerritorio[] = new String[3];
		for(LblCarta c: lstTroca) {
			cartasNomeTerritorio[lstTroca.indexOf(c)] = c.getNomeTerritorio(); 
		}
		
		controller.executaTroca(cartasNomeTerritorio);
	}
	
	private PnlModalCartas() {
		controller.addObserver(this);
		setLayout(null);
		setSize(980, 390);
		setLocation(20,260);
		exibePnlBotoes();
		exibePnlCartasTroca();
		exibePnlCartaObjetivo();
		setBackground( new Color(255, 255, 255, 230) );
		setOpaque(true);
		setVisible(false);
	}
	
	private void exibePnlBotoes() {
		pnlBotoes.setBounds(10, 330, 715, 40);
		pnlBotoes.add(btnEfetuarTroca);
		pnlBotoes.add(btnCancelarTroca);
		add(pnlBotoes);		
	}
	
	public void exibePnlCartaObjetivo() {
		ImageIcon imagemCartaVerso = new ImageIcon("src/resources/images/war_carta_verso.png");
		
		lblTituloObjetivo.setText("Objetivo");
		lblTituloObjetivo.setLocation(810,70);
		lblTituloObjetivo.setSize(imagemCartaVerso.getIconWidth()+30,30);
		lblTituloObjetivo.setForeground(Color.BLACK);
		lblTituloObjetivo.setOpaque(false);
		lblTituloObjetivo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		add(lblTituloObjetivo);
		
		
		lblCartaObjetivo.setIcon(imagemCartaVerso);
		lblCartaObjetivo.setLocation(820, 110);
		lblCartaObjetivo.setSize(imagemCartaVerso.getIconWidth(), imagemCartaVerso.getIconHeight());
		add(lblCartaObjetivo);
	}

	public void exibePnlCartasTroca() {
		lblTituloCartas.setText("Cartas");
		lblTituloCartas.setLocation(10,70);
		lblTituloCartas.setSize(720,30);
		lblTituloCartas.setForeground(Color.BLACK);
		lblTituloCartas.setOpaque(false);
		lblTituloCartas.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		add(lblTituloCartas);
		
		pnlCartasTroca.setBounds(10,100, 720, 238);
		
		for(LblCarta c: lstLblCartas) {
			pnlCartasTroca.add(c);
		}
		
		pnlCartasTroca.setOpaque(false);
		
		add(pnlCartasTroca);
	}
	
	private void atualizaCartaObjetivo() {
		if(controller.getJogadorDaVez().getObjetivo() != null) {
			lblCartaObjetivo.setToolTipText(controller.getJogadorDaVez().getObjetivo().getDescricao());
		}
	}

	private void atualizaCartasTrocaJogador() {
		
		for(LblCarta c: lstLblCartas) {
			c.setBorder(null);
			if(c.isTrocar()) {
				c.setTroca(false);
			}
		}
		
		if(controller.getCartaJogador(0) != null) {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/" + controller.getCartaJogador(0)[0] + ".png");
			lblCarta1.setIcon(imgcarta);
			lblCarta1.setImagem(imgcarta);
			lblCarta1.setNomeTerritorio((String) controller.getCartaJogador(0)[1] != null? (String) controller.getCartaJogador(0)[1]: "Coringa");
			lblCarta1.setToolTipText(lblCarta1.getNomeTerritorio());
			lblCarta1.setSimbolo((int) controller.getCartaJogador(0)[2]);
		} else {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/war_carta_placeholder.png");
			lblCarta1.setIcon(imgcarta);
			lblCarta1.setImagem(imgcarta);
		}
		
		if(controller.getCartaJogador(1) != null) {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/" + controller.getCartaJogador(1)[0] + ".png");
			lblCarta2.setIcon(imgcarta);
			lblCarta2.setImagem(imgcarta);
			lblCarta2.setNomeTerritorio((String) controller.getCartaJogador(1)[1] != null? (String) controller.getCartaJogador(1)[1]: "Coringa");
			lblCarta2.setToolTipText(lblCarta2.getNomeTerritorio());
			lblCarta2.setSimbolo((int) controller.getCartaJogador(1)[2]);
		} else {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/war_carta_placeholder.png");
			lblCarta2.setIcon(imgcarta);
			lblCarta2.setImagem(imgcarta);
		}
		
		if(controller.getCartaJogador(2) != null) {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/" + controller.getCartaJogador(2)[0] + ".png");
			lblCarta3.setIcon(imgcarta);
			lblCarta3.setImagem(imgcarta);
			lblCarta3.setNomeTerritorio((String) controller.getCartaJogador(2)[1] != null? (String) controller.getCartaJogador(2)[1]: "Coringa");
			lblCarta3.setToolTipText(lblCarta3.getNomeTerritorio());
			lblCarta3.setSimbolo((int) controller.getCartaJogador(2)[2]);
		} else {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/war_carta_placeholder.png");
			lblCarta3.setIcon(imgcarta);
			lblCarta3.setImagem(imgcarta);
		}
		
		if(controller.getCartaJogador(3) != null) {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/" + controller.getCartaJogador(3)[0] + ".png");
			lblCarta4.setIcon(imgcarta);
			lblCarta4.setImagem(imgcarta);
			lblCarta4.setNomeTerritorio((String) controller.getCartaJogador(3)[1] != null? (String) controller.getCartaJogador(3)[1]: "Coringa");
			lblCarta4.setToolTipText(lblCarta4.getNomeTerritorio());
			lblCarta4.setSimbolo((int) controller.getCartaJogador(3)[2]);
		} else {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/war_carta_placeholder.png");
			lblCarta4.setIcon(imgcarta);
			lblCarta4.setImagem(imgcarta);
		}
		
		if(controller.getCartaJogador(4) != null) {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/" + controller.getCartaJogador(4)[0] + ".png");
			lblCarta5.setIcon(imgcarta);
			lblCarta5.setImagem(imgcarta);
			lblCarta5.setNomeTerritorio((String) controller.getCartaJogador(4)[1] != null? (String) controller.getCartaJogador(4)[1]: "Coringa" );
			lblCarta5.setToolTipText(lblCarta5.getNomeTerritorio());
			lblCarta5.setSimbolo((int) controller.getCartaJogador(4)[2]);
		} else {
			ImageIcon imgcarta = new ImageIcon("src/resources/images/war_carta_placeholder.png");
			lblCarta5.setIcon(imgcarta);
			lblCarta5.setImagem(imgcarta);
		}
	}
	
	public static PnlModalCartas getInstance() {
		if(pnlModalCartas == null) {
			pnlModalCartas = new PnlModalCartas();
		}
		return pnlModalCartas;
	}

	@Override
	public void update(Observable o, Object arg) {
		atualizaCartasTrocaJogador();
		atualizaCartaObjetivo();
		
	}

}
