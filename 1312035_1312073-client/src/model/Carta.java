package model;


public class Carta {
	
	private Territorio territorio;
	private int simbolo;
	private boolean coringa;
	private String imagem;
	
	public Carta(Territorio territorio, int simbolo, boolean coringa, String imagem) {
		this.territorio = territorio;
		this.simbolo = simbolo;
		this.coringa = coringa;
		this.imagem = imagem;
	}

	public Territorio getTerritorio() {
		return territorio;
	}

	public int getSimbolo() {
		return simbolo;
	}
	
	public String getImagem() {
		return imagem ;
	}

	public boolean isCoringa() {
		return coringa;
	}
}
