package client;

import java.util.ArrayList;

import model.Dado;

public class SerializeData {
	private static SerializeData instance = null;
	
	public static SerializeData getInstance(){
		if(instance == null){
			instance = new SerializeData();
		}
		return instance;
	}
	
    public void sendData(String type){
    	System.out.println("sending data to init");
		if(type.compareTo("init") == 0){
			Client.setMessage(type);
		}
		if(type.compareTo("Next") == 0){
			Client.setMessage(type);
		}
	}


	public void sendData(String type, String v1){
		String seriealizedString = null;
		System.out.println("Distribuindo");
		if(type.compareTo("selectPlayer") == 0){
			String name = v1;
			seriealizedString = "selectPlayer:" + name;
			Client.setMessage(seriealizedString);
		}
		else if (type.compareTo("Distribuir") == 0){
			String nome = v1;
			seriealizedString = "Distribuir," + nome;
			Client.setMessage(seriealizedString);
		}
		else if (type.compareTo("Seed") == 0){
			String seed = v1;
			seriealizedString = "Seed," + seed;
			Client.setMessage(seriealizedString);
		}
	}
	public void sendData(String type, String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8){
		String seriealizedString = null;
		if(type.compareTo("Atacar") == 0){
			if(v1.compareTo("Vitoria") == 0){
				System.out.println("Atacando");
				String result = v1;
				String name1 = v2;
				String cor1 = v3;
				String numeroSoldados1 = v4;
				String name2 = v5;
				String cor2 = v6;
				String numeroSoldados2 = v7;
				String dadosAtaque = v8;
				seriealizedString = "Atacar," + result + "," + name1 + "," + cor1 + "," + numeroSoldados1 + "," + name2 + "," + cor2 + "," + numeroSoldados2 + "," + dadosAtaque;
				Client.setMessage(seriealizedString);
			}
			if(v1.compareTo("Derrota") == 0){
				System.out.println("Atacando");
				String result = v1;
				String name1 = v2;
				String cor1 = v3;
				String numeroSoldados1 = v4;
				String name2 = v5;
				String cor2 = v6;
				String numeroSoldados2 = v7;
				seriealizedString = "Atacar," + result + "," + name1 + "," + cor1 + "," + numeroSoldados1 + "," + name2 + "," + cor2 + "," + numeroSoldados2;
				Client.setMessage(seriealizedString);
			}
		}
	}
	public void sendData(String type, String v1, String v2, String v3, String v4, String v5, String v6){
		String seriealizedString = null;
		
		if(type.compareTo("Remanejar") == 0){
			System.out.println("Remanejando");
			String name1 = v1;
			String cor1 = v2;
			String numeroSoldados1 = v3;
			String name2 = v4;
			String cor2 = v5;
			String numeroSoldados2 = v6;
			seriealizedString = "Remanejar," + name1 + "," + cor1 + "," + numeroSoldados1 + "," + name2 + "," + cor2 + "," + numeroSoldados2;
			Client.setMessage(seriealizedString);
		}
	}
	
	public void sendData(String type, int dadosAtaque, int dadosDefesa, ArrayList<Dado> lstDadosAtaque, ArrayList<Dado> lstDadosDefesa){
		if(type.compareTo("Dados") == 0){
			System.out.println("Dados");
			String seriealizedString = "Dados" + "," + dadosAtaque;
			
			for(int i = 0; i < dadosAtaque; i++){
				seriealizedString += ",";
				seriealizedString += lstDadosAtaque.get(i).getNumero();
			}
		
			seriealizedString += "," + dadosDefesa;
			
			for(int i = 0; i < dadosDefesa; i++){
				seriealizedString += ",";
				seriealizedString += lstDadosDefesa.get(i).getNumero();
			}
			
			Client.setMessage(seriealizedString);
		}
	}
}
