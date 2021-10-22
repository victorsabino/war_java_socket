package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import controller.ControllerTabuleiro;


public class DeckObjetivos {
		
	private static DeckObjetivos deckObjetivos;
	private ArrayList<Objetivo> lstObjetivos = new ArrayList<Objetivo>();
	
	private DeckObjetivos(){
		carregaObjetivos();
	}

	public static DeckObjetivos getInstance(){
		if(deckObjetivos == null){
			deckObjetivos = new DeckObjetivos();
		}
		return deckObjetivos;
	}
	
	
	private void carregaObjetivos() {
		lstObjetivos.add(new Objetivo_1());
		lstObjetivos.add(new Objetivo_2());
		lstObjetivos.add(new Objetivo_3());
		lstObjetivos.add(new Objetivo_4());
		lstObjetivos.add(new Objetivo_5());
		lstObjetivos.add(new Objetivo_6());
		lstObjetivos.add(new Objetivo_7());
		lstObjetivos.add(new Objetivo_8());
		
		for(Exercito e: ControllerTabuleiro.getInstance().getLstJogadores()){
			lstObjetivos.add(new Objetivo_9(e));
		}
		
		embaralhaDeck();
	}
	
	private void embaralhaDeck(){
		
		long seed = 1L;
		Collections.shuffle(lstObjetivos, new Random(seed));
		
	}
	
	public ArrayList<Objetivo> getLstObjetivos(){
		return lstObjetivos;
		
	}
	
	public Objetivo getObjetivo(String s){
		for(Objetivo o: getLstObjetivos()){
			if(o.getNome() == s){
				return o;
			}
		}
		return null;
	}


}
