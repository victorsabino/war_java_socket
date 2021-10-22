package model;

import java.util.ArrayList;

public class Objetivo_9 extends Objetivo{
	
	public Objetivo_9(Exercito e){
		this.exercitoAlvo = e;
		this.nome = "Destruir todos os" + exercitoAlvo.getNome();
		this.descricao = "Destruir todos os ex�rcitos " + exercitoAlvo.getNome() + "s";
	}
	
	
	public boolean Check(ArrayList<Continente> lstContinentes, Exercito e){
		
		// Para cada continente
		for(Continente c: lstContinentes) {
			
			// Para cada territ�rio
			for(Territorio t: c.getLstTerritorios()) {
				// Se o territ�rio possui soldado do ex�rcito passado como par�metro
				if(t.getLstSoldados().get(0).getExercito() == e) {
					
					// Retorna false, indicando que n�o foi atingido o objetivo.
					return false;
				}
			}
		}
		
		// Sen�o, retorna true, o objetivo foi atingido.
		return true;
	}

}
