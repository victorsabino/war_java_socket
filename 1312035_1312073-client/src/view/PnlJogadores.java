package view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.ControllerTabuleiro;

@SuppressWarnings("serial")
public class PnlJogadores extends JPanel implements Observer {
	
	private static PnlJogadores pnlJogadores;
	ControllerTabuleiro controller = ControllerTabuleiro.getInstance();
	private static ArrayList<Jogador> lstJogadores = new ArrayList<Jogador>();
	
	private PnlJogadores() {
		controller.addObserver(this);
		configJPanel();
		for(view.Jogador j: getLstJogadores() ){
			add(new Jogador(j.getNome(), j.getCor()));
		}
	}
	
	public static void setJogadores(ArrayList<Exercito> lst) {
		for(Exercito e: lst) {			
				lstJogadores.add(new Jogador(e.getNome(), e.getCor()));
		}
	}
	
	private ArrayList<Jogador> getLstJogadores() {
		return lstJogadores;
	}
	
	public static PnlJogadores getInstance(){
		if(pnlJogadores == null) {
			pnlJogadores = new PnlJogadores();
		}
		return pnlJogadores;
	}

	private void configJPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		setLocation(0,690);
		setSize(240, 70);
		setOpaque(false);		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	
}
