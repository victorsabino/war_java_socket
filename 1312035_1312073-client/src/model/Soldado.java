package model;


public class Soldado {

	private Exercito exercito;
	private Continente continente;
	boolean imigrante = false;
	
	public Soldado(Exercito e){
		
		exercito = e;
		
	}
	
	public Soldado(Exercito e, Continente c) {
		exercito = e;
		continente = c;
	}
	
	public void setImigrante() {
		if(imigrante == false) {
			imigrante = true;
		} else {
			imigrante = false;
		}
	}
	
	public boolean isImigrante() {
		return imigrante;
	}
	
	public Continente getContinente() {
		return continente;
	}
	
	public Exercito getExercito(){
		
		return exercito;
		
	}
	
	
	
}
