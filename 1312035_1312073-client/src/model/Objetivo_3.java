//Conquistar na totalidade a ASIA e a AMÉRICA DO SUL.

package model;

import java.util.ArrayList;

public class Objetivo_3 extends Objetivo {
	
	public Objetivo_3(){
		this.nome = "Asia e America do Sul";
		this.descricao = "Conquistar na totalidade Asia e America do Sul";
	}

	@Override
	public boolean Check(ArrayList<Continente> lstContinentes, Exercito e) {
	
		for (Continente c : lstContinentes) {
			if (c.getNome() == "Ásia") {
				for (Territorio t : c.getLstTerritorios()) {
					if (t.getLstSoldados().get(0).getExercito() != e) {
						return false;
					}
				}
			}
			if (c.getNome() == "América do sul"){
				for(Territorio t: c.getLstTerritorios()){
					if(t.getLstSoldados().get(0).getExercito() != e){
						return false;
					}
				}
			}

		}
		
	return true;
		
	}

}
