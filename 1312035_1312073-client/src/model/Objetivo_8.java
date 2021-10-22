//Conquistar 18 TERRITÓRIOS e ocupar cada um deles com pelo menos dois exércitos.

package model;

import java.util.ArrayList;

public class Objetivo_8 extends Objetivo {

	public Objetivo_8() {
		this.nome = "18 territorios";
		this.descricao = "Conquistar 18 territorios com pelo menos 2 exercitos em cada";
	}

	@Override
	public boolean Check(ArrayList<Continente> lstContinentes, Exercito e) {

		int i = 0;
		for (Continente c : lstContinentes) {
			for (Territorio t : c.getLstTerritorios()) {
				if (t.getLstSoldados().get(0).getExercito() == e) {
					if (t.getLstSoldados().size() >= 2) {
						i++;
					}
				}
			}

		}

		if (i < 18) {
			return false;
		}
		
		return true;
	
	}

}
