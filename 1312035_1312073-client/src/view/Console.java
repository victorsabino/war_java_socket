package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.ControllerTabuleiro;

@SuppressWarnings("serial")
public class Console extends JPanel implements Observer {

	private static Console console;

	private JLabel lblTituloConsole = new JLabel();
	private JTextArea txtConsole = new JTextArea();

	private Font fonteTitulo = new Font("Stencil", Font.PLAIN, 15);
	private Font fonteConsole = new Font("Courier", Font.PLAIN, 12);
	private ControllerTabuleiro controller = ControllerTabuleiro.getInstance();

	private Console() {
		
		controller.addObserver(this);

		lblTituloConsole.setText("Console de mensagens");
		lblTituloConsole.setBounds(0, 0, 398, 15);
		lblTituloConsole.setForeground(Color.white);
		lblTituloConsole.setFont(fonteTitulo);

		txtConsole.setEditable(true);
		txtConsole.setForeground(Color.white);
		txtConsole.setBounds(0, 12, 393, 44);
		txtConsole.setMargin(new Insets(0, 0, 0, 0));
		txtConsole.setFont(fonteConsole);
		txtConsole.setOpaque(false);
		txtConsole.setLineWrap(true);
		txtConsole.setWrapStyleWord(true);

		setBounds(1024 / 2 + txtConsole.getWidth() / 2 - 87, 654, 393, 51);
		setLayout(null);
		add(lblTituloConsole);
		add(txtConsole);
		setOpaque(false);
	}

	// Implementando o Singleton
	public static Console getInstance() {

		if (console == null) {
			console = new Console();
		}

		return console;

	}

	private void addLog(String s) {
		txtConsole.setText(s);
		System.out.println(s);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(controller.getMensagem() != null) {
			addLog(controller.getMensagem());
		}
		
		
	}
}
