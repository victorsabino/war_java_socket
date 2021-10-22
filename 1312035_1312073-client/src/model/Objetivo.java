package model;

import java.util.ArrayList;

public abstract class Objetivo {
	protected String nome;
	protected String descricao;
	protected Exercito exercitoAlvo;
	
	public Exercito getExercitoAlvo(){
		return exercitoAlvo;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public abstract boolean Check(ArrayList<Continente> lstContinentes, Exercito e);
	

}