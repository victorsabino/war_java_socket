package client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
	int port = 123;
	private static boolean hasMsg = false;
	private static String msgSentByClient = "";
	
	public static boolean setMessage(String msg_in){
		
		if(msg_in.compareTo("") != 0){
			System.out.println("msg_in = " + msg_in);
			msgSentByClient = msg_in;
			hasMsg = true;
		}
		return hasMsg;
	}
	
	DeserializeData deserialize = new DeserializeData();
		@Override
		public void run() {
			try{
				System.out.println("Cliente");
				Socket cli = new Socket("127.0.0.1", port);
				ServerData msg_sv = new ServerData(cli);
				Thread t = new Thread (msg_sv);
				t.start();
				System.out.println("O cliente se conectou ao servidor!");
				Scanner teclado = new Scanner(System.in);
				PrintStream saida = new PrintStream(cli.getOutputStream());
				//String msg = teclado.nextLine();
				while (msgSentByClient.compareTo("###")!=0) {
					
					if(hasMsg == true){
						System.out.println(hasMsg);
						System.out.println("msgSentByClient " + msgSentByClient);
						saida.println(msgSentByClient);
						msgSentByClient = "";
						hasMsg = false;
						
						//deserialize.updateDate(msg);
						
					}
					Thread.sleep(1000);
					//msg = teclado.nextLine();
				}
				saida.close();
				teclado.close();
				cli.close();
				System.out.println("O cliente terminou de executar!");
			}
			catch(Exception e){
				
			}
		}
}
