//CONQUISTAR 24 TERRITORIOS A SUA ESCOLHA

package model;

import java.util.ArrayList;

public class Objetivo_1 extends Objetivo {
	
	public Objetivo_1(){
		this.nome = "24 territorios";
		this.descricao = "Conquistar 24 territorios a sua escolha";
	}

	@Override
	public boolean Check(ArrayList<Continente> lstContinentes, Exercito e) {

		int i = 0;
		for (Continente c : lstContinentes) {
			for (Territorio t : c.getLstTerritorios()) {
				if (t.getLstSoldados().get(0).getExercito() == e) {
					i++;
				}
			}
		}

		if (i < 24) {
			return false;
		} else {
			return true;
		}
	}

}
