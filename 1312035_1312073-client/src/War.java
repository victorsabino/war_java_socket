import java.io.IOException;

import client.Client;
import view.Configuracao;


public class War {

	public static void main(String[] args) {
		Client cli = new Client();
		Thread t = new Thread(cli);
		t.start();
		Configuracao.getInstance();
	}

}
