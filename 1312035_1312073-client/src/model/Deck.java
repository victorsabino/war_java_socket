package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import controller.ControllerTabuleiro;

public class Deck {
	
	private static Deck deck;
	private ArrayList<Carta> lstCartas = new ArrayList<Carta>();
	ControllerTabuleiro controller = ControllerTabuleiro.getInstance();
	
	private Deck(){
		carregaCartas();
	}
	
	public static Deck getInstance(){
		if(deck == null){
			deck = new Deck();
		}
		return deck;
	}
	
	public void carregaCartas(){

		for(Continente c: controller.getLstContinentes()) {
			for(Territorio t: c.getLstTerritorios()){
				lstCartas.add(new Carta(t,t.getSimbolo(),false,"war_carta_"+t.getNomeCarta()));
			}
		}
		lstCartas.add(new Carta(null,4,true,"war_carta_coringa"));
		lstCartas.add(new Carta(null,4,true,"war_carta_coringa"));
		
		embaralhaDeck();
		
	}
	
	public void embaralhaDeck(){
		
		long seed = 1L;
		Collections.shuffle(lstCartas, new Random(seed));
		
	}
	
	public ArrayList<Carta> getLstCartas(){
		return lstCartas;
		
	}

}
