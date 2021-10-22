package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerData implements Runnable{
	Socket cli;
	Scanner in_serv;
	public ServerData(Socket cli) throws IOException{
		this.cli = cli;
		this.in_serv = new Scanner(cli.getInputStream());
	}
	@Override
	public void run() {
		try {
			in_serv = new Scanner(cli.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (in_serv.hasNextLine()) {
			String dataToBeSend = in_serv.nextLine();
			System.out.println(dataToBeSend);
			DeserializeData.updateDate(dataToBeSend);
		}
	}

}
