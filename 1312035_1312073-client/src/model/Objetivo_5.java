//Conquistar na totalidade a AMÉRICA DO NORTE e a OCEANIA.

package model;

import java.util.ArrayList;

public class Objetivo_5 extends Objetivo {
	
	public Objetivo_5(){
		this.nome = "America do Norte e Oceania";
		this.descricao = "Conquistar na totalidade America do Norte e Oceania";
	}

	@Override
	public boolean Check(ArrayList<Continente> lstContinentes, Exercito e) {
		
		for (Continente c : lstContinentes) {
			if (c.getNome() == "Oceania") {
				for (Territorio t : c.getLstTerritorios()) {
					if (t.getLstSoldados().get(0).getExercito() != e) {
						return false;
					}
				}
			}
			if (c.getNome() == "América do norte"){
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
