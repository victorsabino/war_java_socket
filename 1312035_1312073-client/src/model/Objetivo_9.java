package model;

import java.util.ArrayList;

public class Objetivo_9 extends Objetivo{
	
	public Objetivo_9(Exercito e){
		this.exercitoAlvo = e;
		this.nome = "Destruir todos os" + exercitoAlvo.getNome();
		this.descricao = "Destruir todos os exércitos " + exercitoAlvo.getNome() + "s";
	}
	
	
	public boolean Check(ArrayList<Continente> lstContinentes, Exercito e){
		
		// Para cada continente
		for(Continente c: lstContinentes) {
			
			// Para cada território
			for(Territorio t: c.getLstTerritorios()) {
				// Se o território possui soldado do exército passado como parâmetro
				if(t.getLstSoldados().get(0).getExercito() == e) {
					
					// Retorna false, indicando que não foi atingido o objetivo.
					return false;
				}
			}
		}
		
		// Senão, retorna true, o objetivo foi atingido.
		return true;
	}

}
