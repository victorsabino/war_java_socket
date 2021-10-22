package client;

import java.util.ArrayList;
import java.util.List;

import controller.ControllerTabuleiro;
import model.Continente;
import model.Dado;
import model.Soldado;
import model.Territorio;
import view.Configuracao;

public class DeserializeData {
	public static void updateDate(String Data){
			String[] parts = Data.split("\\,");
			System.out.println("parts[0]" + parts[0]);
		
			//setting up the first client to login in as master
			if(Data.compareTo("master") == 0){
				System.out.println("Setting master . . .");
				ControllerTabuleiro.setMaster();
			}
			else if(Data.compareTo("initf") == 0){
				 				// Abre o tabuleiro
				 				Configuracao.getInstance().initGame();
				  			}
			else if(Data.compareTo("Next") == 0){
 				// Abre o tabuleiro
				ControllerTabuleiro.getInstance().btnProxJogada_click();
				ControllerTabuleiro.getInstance().notificaMudancas();
  			}
			else if(Data.contains("selectPlayer")){
				String nome = Data.substring(Data.lastIndexOf(":") + 1);
				System.out.println("Data recieved " + nome);
				for (view.Exercito ex : view.Configuracao.getInstance().getLstexercitos()) {
					System.out.println(ex.getNome());
					if(ex.getNome().compareTo(nome) == 0 && ex.isSelecionado() != true){
						ex.setSelecionado();
						//ControllerTabuleiro.getInstance().notificaMudancas();
					}
				}
			}
			else if(parts[0].compareTo("Distribuir") == 0){
				for (Continente c : ControllerTabuleiro.getInstance().getLstContinentes()) {
					//System.out.println("Cheguei aqui primeiro");
					for (Territorio t : c.getLstTerritorios()) {
						//System.out.println("Cheguei aqui");
						if(t.getNome().compareTo(parts[1]) == 0){
							
							ControllerTabuleiro.getInstance().moveSoldadoAvulso(t);
							ControllerTabuleiro.getInstance().notificaMudancas();
							//System.out.println("Cheguei aqui 2");
							//Move soldado avulso faz o mesmo que adicionar um soldado (pelo que eu vi...).
						}
					}
				}
			}
			else if(parts[0].compareTo("Atacar") == 0){
				Territorio territorioOrigem = null;
				Territorio territorioDestino = null;
				if(parts[1].compareTo("Vitoria") == 0){
					for (Continente c : ControllerTabuleiro.getInstance().getLstContinentes()) {
						for (Territorio t : c.getLstTerritorios()) {
							if(t.getNome().compareTo(parts[2]) == 0){
								territorioOrigem = t;
								//Aquela subtracao no "j" eh o calculo dos soldados de t - o numero dos soldados recebido (atualizado).
								//Ja que quando voce ganha seu exercito eh dividido, o territorio de origem sempre ira diminuir.
							}
						}
					}
					for (Continente c : ControllerTabuleiro.getInstance().getLstContinentes()) {
						for (Territorio t : c.getLstTerritorios()) {
							if(t.getNome().compareTo(parts[5]) == 0){
								territorioDestino = t;
								System.out.println("DE PRIMEIRA");
								ControllerTabuleiro.getInstance().Vitoria(territorioOrigem, territorioDestino, Integer.parseInt(parts[7]));							}
								ControllerTabuleiro.getInstance().notificaMudancas();
						}
					}
				}
				else if(parts[1].compareTo("Derrota") == 0){
					for (Continente c : ControllerTabuleiro.getInstance().getLstContinentes()) {
						for (Territorio t : c.getLstTerritorios()) {
							if(t.getNome().compareTo(parts[2]) == 0){
								t.getLstSoldados().remove(0);
								ControllerTabuleiro.getInstance().notificaMudancas();
								//Na derrota o territorio de origem so perde um soldado, logo uso apenas um remove.
							}
						}
					}
				}
			}
			else if(parts[0].compareTo("Remanejar") == 0){
				for (Continente c : ControllerTabuleiro.getInstance().getLstContinentes()) {
					for (Territorio t : c.getLstTerritorios()) {
						if(t.getNome().compareTo(parts[1]) == 0){
							t.getLstSoldados().remove(0);
							//Na hora de remanejar o territorio de origem perde um soldado...
						}
					}
				}
				for (Continente c : ControllerTabuleiro.getInstance().getLstContinentes()) {
					for (Territorio t : c.getLstTerritorios()) {
						if(t.getNome().compareTo(parts[4]) == 0){
							Soldado soldado = t.getLstSoldados().get(0);
							t.getLstSoldados().add(soldado);
							ControllerTabuleiro.getInstance().notificaMudancas();
							//...enquanto o territorio de destino ganha um soldado
							//Notei que devido a isso o remanejar so precisaria receber o nome do territorio de origem e destino
							//Mas agora deixa hahahaha.
						}
					}
				}
			}
		
			else if(parts[0].compareTo("Dados") == 0){
				int i;
				System.out.println("Comparando dados");
				
				for(model.Dado d : ControllerTabuleiro.getInstance().lstDadosAtaque){
					System.out.println("limpando dados atk");
					d.setNumero(0);
					}
				for(i = 2; i < Integer.parseInt(parts[1]) + 2; i++){
					System.out.println(parts[1]+ "  " + i);
					ControllerTabuleiro.getInstance().lstDadosAtaque.get(i - 2).setNumero(Integer.parseInt(parts[i]));
				}
				
				for(model.Dado d : ControllerTabuleiro.getInstance().lstDadosDefesa){
					System.out.println("limpando dados def");
					d.setNumero(0);
					}
				for(int j = 0; j < Integer.parseInt(parts[i]); j++){
					System.out.println(parts[i]+ " " + i + " " + j + " " + (i+j));
					ControllerTabuleiro.getInstance().lstDadosDefesa.get(j).setNumero(Integer.parseInt(parts[i+j+1]));
				}
				ControllerTabuleiro.getInstance().notificaMudancas();
			}
			
			else if(parts[0].compareTo("Seed") == 0){
				long seed = Long.parseLong(parts[1], 10);
				ControllerTabuleiro.setSeed(seed);
			}
		}
	}
