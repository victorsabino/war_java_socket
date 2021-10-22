package model;

public class Jogada {
	private String nome;
	private boolean ativo;
	
	public Jogada(String nome) {
		this.nome = nome;
		this.ativo = false;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setAtivo() {
		if(this.ativo) {
			this.ativo = false;
		} else { 
			this.ativo = true;
		}
	}
	
	public boolean isAtivo() {
		return ativo;
	}
}
