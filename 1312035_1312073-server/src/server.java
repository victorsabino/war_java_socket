import java.net.*;
import java.io.*;
import java.util.*;
public class server {
	private static ArrayList<Socket> lstClient = new ArrayList<Socket>();
	private static int qtdClientes = 0;
	
	public static void main(String[] args) throws IOException {
		int port = 123;
		ServerSocket servidor = new ServerSocket(port);
		System.out.println("Porta "+ port +" aberta!");
		while(true){
			Socket cliente = servidor.accept();
			lstClient.add(cliente);
			Clients client = new Clients(cliente, lstClient);
			
			Thread t = new Thread(client);
			t.start();
		}
	}
}